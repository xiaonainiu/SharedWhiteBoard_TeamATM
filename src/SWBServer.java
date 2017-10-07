import org.json.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
/**
 * Created by ES on 2017/9/14.
 */
public class SWBServer {
    ServerSocket serverSocket;
    static List<Game> gameList = new ArrayList<>();
    static HashMap<String,Socket> socketList = new HashMap<>();


    public static void main(String[] args){
        System.out.println("**Server Start**");
        SWBServer server = new SWBServer();

        try{
            server.serverSocket = new ServerSocket(1234);
            boolean flag = true;
            while (flag){
                try{
                    Socket socket = server.serverSocket.accept();

                    Thread t = new Thread(new RequestThread(socket));
                    t.start();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }


//        demo of json
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("operation","chawWindow");
//        JSONObject message = new JSONObject(map);
//        String a = message.toString();
//        JSONObject received_Message = new JSONObject(a);
//        System.out.print(received_Message.get("operation"));
    }

    static class RequestThread implements Runnable{
        private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;

        RequestThread(Socket socket){
            this.socket = socket;
        }

        public void run(){
            try{
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                String inputStr = null;
                String outputStr = null;

                if ((inputStr = dis.readUTF())!=null){
//                    initial variable which may useful
//                    for reply
                    HashMap<String,String> map = new HashMap<String,String>();
                    String reply_type;
                    String result;
                    JSONObject reply_massage;
                    String reply_massageStr;

//                    for operation
                    int gameIndex;
                    String ip;
                    String port;
                    String manager;
                    String[] info = new String[2];
                    String[] userList;
                    int userNumber;
                    String pic;

                    System.out.println("From IP: "+socket.getInetAddress()+","+socket.getPort()+": "+inputStr);

//                    check massage type
                    JSONObject massage = new JSONObject(inputStr);
                    String type = massage.getString("type");
                    try{
                        switch (type){
                            case "create":
                                ip = massage.getString("ip");
                                port = massage.getString("port");
                                manager = massage.getString("manager");
                                String[] c_info = {ip,port};
                                if (checkGameExist(c_info) == -1){
                                    Game game = new Game(manager, ip, port);
                                    gameList.add(game);
                                    socketList.put(manager,socket);
                                    System.out.println("size: "+gameList.size());
                                    result = "true";
                                }else {
                                    result = "false";
                                }
                                reply_type = "createFeedback";
                                map.put("type",reply_type);
                                map.put("result",result);
                                map.put("ip",ip);
                                map.put("port",port);
                                map.put("manager",manager);
                                reply_massage = new JSONObject(map);
                                reply_massageStr = reply_massage.toString();
                                System.out.println(reply_massageStr);
                                dos.writeUTF(reply_massageStr);
                                break;
                            case "join":
                                System.out.println("join case is active");
                                reply_type = "joinFeedback";
                                map.put("type",reply_type);
                                map.put("result","false");
                                reply_massage = new JSONObject(map);
                                reply_massageStr = reply_massage.toString();
                                System.out.println(reply_massageStr);

                                String join_user = massage.getString("player");
                                ip = massage.getString("ip");
                                port = massage.getString("port");
                                info[0]=ip;
                                info[1]=port;

                                socketList.put(join_user,socket);

                                gameIndex = checkGameExist(info);
                                System.out.println("game index is: "+gameIndex);
                                if (gameIndex==-1){
                                    dos.writeUTF(reply_massageStr);
                                    socketList.remove(join_user);
                                    System.out.println("game is not exist");
                                }else {
                                    if (gameList.get(gameIndex).checkEmptySit()){
                                        DataOutputStream joinStream = new DataOutputStream(socketList.get(gameList.get(gameIndex).manager).getOutputStream());
                                        joinStream.writeUTF(inputStr);
                                    }else {
                                        dos.writeUTF(reply_massageStr);
                                        socketList.remove((join_user));
                                        System.out.println("game is full");
                                    }
                                }
                                break;
                            case "joinFeedback":
                                System.out.println("joinFeedback case is active");
                                ip = massage.getString("ip");
                                port = massage.getString("port");
                                String join_fb_user = massage.getString("user");
                                info[0]=ip;
                                info[1]=port;
                                gameIndex = checkGameExist(info);
                                if (gameIndex!=-1){
                                    userNumber = gameList.get(gameIndex).addPlayer(join_fb_user,ip,port);
                                    if (userNumber==-1){
                                        result = "false";
                                        map.put("type","joinFeedback");
                                        map.put("result",result);
                                        reply_massage = new JSONObject(map);
                                        reply_massageStr = reply_massage.toString();
                                        DataOutputStream joinStream = new DataOutputStream(socketList.get(gameList.get(gameIndex).manager).getOutputStream());
                                        joinStream.writeUTF(reply_massageStr);
                                        socketList.remove(join_fb_user);
                                    }else {
                                        pic = gameList.get(gameIndex).picture;
                                        result = "true";
                                        map.put("type","joinFeedback");
                                        map.put("result",result);
                                        map.put("pic",pic);
                                        map.put("userNumber",String.valueOf(userNumber));
                                        userList= gameList.get(gameIndex).getUser();
                                        for (int j = 0; j < 4; j++){
                                            if (userList[j] != null){
                                                DataOutputStream chatStream = new DataOutputStream(socketList.get(userList[j]).getOutputStream() );
                                                chatStream.writeUTF(inputStr);
                                            }
                                        }
                                    }
                                }else {
                                    //if the game is close
                                }
                            case "chatWindow":
                                String chat_player = massage.getString("player");
                                String chat_ip = massage.getString("ip");
                                String chat_port = massage.getString("port");
                                for (int i = 0; i < gameList.size(); i++){
                                    if (gameList.get(i).checkGame(chat_ip,chat_port)){
                                        userList = gameList.get(i).getUser();
                                        for (int j = 0; j < 4; j++){
                                            if (userList[j] != null && !userList[j].equals(chat_player)){
                                                DataOutputStream chatStream = new DataOutputStream(socketList.get(userList[j]).getOutputStream() );
                                                chatStream.writeUTF(inputStr);
                                            }
                                        }
                                    }
                                }
                            case "draw":
                                String draw_ip = massage.getString("ip");
                                String draw_port = massage.getString("port");
                                String draw_user = massage.getString("manager");
                                String draw_pic = massage.getString("pic");
                                String[] draw_info = {draw_ip,draw_port};
                                gameIndex = checkGameExist(draw_info);
                                if (gameIndex!=-1){
                                    gameList.get(gameIndex).picture = draw_pic;
                                    String[] user = gameList.get(gameIndex).getUser();
                                    for (int j = 0; j < 4; j++){
                                        if (user[j] != null && !user[j].equals(draw_user)){
                                            DataOutputStream chatStream = new DataOutputStream(socketList.get(user[j]).getOutputStream() );
                                            chatStream.writeUTF(inputStr);
                                        }
                                    }
                                }else {
                                    //if the game is close
                                }
                            case "kick":
                                break;

                            default:
                                System.out.println("Invalid input");
//                                return false;
                        }
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Invalid output");
                        e.printStackTrace();
                    }
//                    System.out.println(massage.get("operation"));
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static boolean checkValid(String[] inputStrSplit){
        try{
            switch (inputStrSplit[0]){
                case "create":
                    return inputStrSplit.length==4;
                case "join":
                    return inputStrSplit.length==4;
                case "draw":
                    return inputStrSplit.length==2;
                case "kick":
                    return inputStrSplit.length==2;
                default:
                    return false;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }
    private static int checkGameExist(String[] info){
        try{
            for (int i = 0; i < gameList.size(); i++){
                String[] game_info = gameList.get(i).getGameInfo();
//                System.out.println(game_info.equals(info));
                if (game_info[0].equals(info[0]) && game_info[1].equals(info[1])){
                    return i;
                }
            }
        }catch (NullPointerException e){
            return -1;
        }
        return -1;
    }
}
