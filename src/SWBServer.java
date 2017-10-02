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

    private static class RequestThread implements Runnable{
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
                    HashMap<String,String> map = new HashMap<String,String>();
                    String reply_type;
                    String result;
                    JSONObject reply_massage;
                    String reply_massageStr;
                    JSONObject massage = new JSONObject(inputStr);
                    System.out.println("From IP: "+socket.getInetAddress()+","+socket.getPort()+": "+inputStr);
                    String type = massage.getString("type");
                    try{
                        switch (type){
                            case "create":
                                String ip = massage.getString("ip");
                                String port = massage.getString("port");
                                String manager = massage.getString("manager");
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
                                int playerNumber;

                                String j_player = massage.getString("player");
                                String j_ip = massage.getString("ip");
                                String j_port = massage.getString("port");
                                String[] j_info = {j_ip,j_port};
                                int index = checkGameExist(j_info);
                                System.out.println("game index is: "+index);
                                if (index==-1){
                                    result = "false";
                                    playerNumber = -1;
                                }else {
                                    playerNumber = gameList.get(index).addPlayer(j_player,j_ip,j_port);
                                    if (playerNumber==-1){
                                        result = "false";
                                    }else {
                                        socketList.put(j_player,socket);
                                        result = "true";
                                    }
                                }
                                System.out.println("player number is: "+playerNumber);

//                                gameList.add(game);

                                reply_type = "joinFeedback";
                                map.put("type",reply_type);
                                map.put("result",result);
                                map.put("playernumber",String.valueOf(playerNumber));
                                reply_massage = new JSONObject(map);
                                reply_massageStr = reply_massage.toString();
                                System.out.println(reply_massageStr);
                                dos.writeUTF(reply_massageStr);
                                break;
                            case "chat":
                                String chat_player = massage.getString("player");
                                String chat_ip = massage.getString("ip");
                                String chat_port = massage.getString("port");
                                for (int i = 0; i < gameList.size(); i++){
                                    if (gameList.get(i).checkGame(chat_ip,chat_port)){
                                        String[] user = gameList.get(i).getUser();
                                        for (int j = 0; j < 4; j++){
                                            if (user[j] != null && !user[j].equals(chat_player)){
                                                DataOutputStream chatStream = new DataOutputStream(socketList.get(user[j]).getOutputStream() );
                                                chatStream.writeUTF(inputStr);
                                            }
                                        }
                                    }
                                }
                            case "draw":
                                String d_pic = massage.getString("pic");
                                break;
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
