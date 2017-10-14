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
    static HashMap<String, Socket> socketList = new HashMap<>();


    public static void main(String[] args) {
        System.out.println("**Server Start**");
        SWBServer server = new SWBServer();

        try {
            server.serverSocket = new ServerSocket(1234);
            boolean flag = true;
            while (flag) {
                try {
                    Socket socket = server.serverSocket.accept();

                    Thread t = new Thread(new RequestThread(socket));
                    t.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        demo of json
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("operation","chatWindow");
//        JSONObject message = new JSONObject(map);
//        String a = message.toString();
//        JSONObject received_Message = new JSONObject(a);
//        System.out.print(received_Message.get("operation"));
    }

    static class RequestThread implements Runnable {
        private Socket socket;
        private DataInputStream dis;
        private DataOutputStream dos;

        RequestThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                String inputStr = null;
                String outputStr = null;

                while (true) {
                    System.out.println("......");
                    if ((inputStr = dis.readUTF()) != null) {
//                    initial variable which may useful
                        HashMap<String, String> map = new HashMap<String, String>();//map to store reply information
                        String reply_type;//type of reply
                        String result;//result could be "true" or "false",if the reply is a feedback
                        JSONObject reply_message;//JSONObject
                        String reply_messageStr;//String
                        int gameIndex;//the game index of this message
                        String ip;//game ip of this message
                        String port;//game port of this message
                        String manager;//game manager of this message
                        String user;//user who sent this message
                        String targetUser;//the target user of this message
                        String[] info = new String[2];//store ip and port of this game
                        String[] userList;//user list of this game
                        int userNumber;//user number in this game
                        List<String> picture;//picture of this game

                        System.out.println("From IP: " + socket.getInetAddress() + "," + socket.getPort() + ": " + inputStr);

//                    check message type
                        JSONObject message = new JSONObject(inputStr);
                        String type = message.getString("type");
                        ip = message.getString("ip");
                        port = message.getString("port");
                        info[0] = ip;
                        info[1] = port;
                        gameIndex = checkGameExist(info);
                        try {
                            switch (type) {
                                case "create":
                                    manager = message.getString("manager");
                                    String[] c_info = {ip, port};
                                    if (checkGameExist(c_info) == -1) {
                                        Game game = new Game(manager, ip, port);
                                        gameList.add(game);
                                        socketList.put(manager, socket);
                                        System.out.println("size: " + gameList.size());
                                        result = "true";
                                    } else {
                                        result = "false";
                                    }
                                    reply_type = "createFeedback";
                                    map.put("type", reply_type);
                                    map.put("result", result);
                                    map.put("ip", ip);
                                    map.put("port", port);
                                    map.put("manager", manager);
                                    reply_message = new JSONObject(map);
                                    reply_messageStr = reply_message.toString();
                                    System.out.println(reply_messageStr);
                                    dos.writeUTF(reply_messageStr);
                                    break;
                                case "join":
                                    System.out.println("join case is active");
                                    reply_type = "joinFeedback";
                                    map.put("type", reply_type);
                                    map.put("result", "false");
                                    reply_message = new JSONObject(map);
                                    reply_messageStr = reply_message.toString();
                                    System.out.println(reply_messageStr);

                                    user = message.getString("user");

                                    socketList.put(user, socket);
                                    System.out.println("game index is: " + gameIndex);
                                    if (gameIndex == -1) {
                                        // game not exist, return false
                                        dos.writeUTF(reply_messageStr);
                                        socketList.remove(user);
                                        dos.close();
                                        socket.close();
                                        System.out.println("game is not exist");
                                    } else {
                                        if (gameList.get(gameIndex).checkEmptySit()) {
                                            DataOutputStream joinStream = new DataOutputStream(socketList.get(gameList.get(gameIndex).manager).getOutputStream());
                                            joinStream.writeUTF(inputStr);
                                            System.out.println(inputStr);
                                        } else {
                                            // game is full, return false+
                                            dos.writeUTF(reply_messageStr);
                                            socketList.remove((user));
                                            dos.close();
                                            socket.close();
                                            System.out.println("game is full");
                                        }
                                    }
                                    break;
                                case "joinFeedback":
                                    System.out.println("joinFeedback case is active");
                                    targetUser = message.getString("targetUser");
                                    DataOutputStream joinStream = new DataOutputStream(socketList.get(targetUser).getOutputStream());
                                    if (gameIndex != -1) {
                                        userNumber = gameList.get(gameIndex).addPlayer(targetUser, ip, port);
                                        if (userNumber == -1) {
                                            result = "false";
                                            map.put("type", "joinFeedback");
                                            map.put("result", result);
                                            reply_message = new JSONObject(map);
                                            reply_messageStr = reply_message.toString();
                                            dos.writeUTF(reply_messageStr);
                                            joinStream.writeUTF(reply_messageStr);
                                            joinStream.close();
                                            socketList.get(targetUser).close();
                                            socketList.remove(targetUser);
                                        } else {
                                            picture = gameList.get(gameIndex).getPicture();
                                            manager = gameList.get(gameIndex).getManager();
                                            result = "true";
                                            map.put("type", "joinFeedback");
                                            map.put("result", result);
                                            map.put("ip", ip);
                                            map.put("port", port);
                                            map.put("targetUser", targetUser);
                                            map.put("manager", manager);
//                                            map.put("pic", pic);
                                            map.put("userNumber", String.valueOf(userNumber));
                                            reply_message = new JSONObject(map);
                                            reply_messageStr = reply_message.toString();
                                            joinStream.writeUTF(reply_messageStr);
                                            for (int i=0;i<picture.size();i++){
                                                reply_messageStr = picture.get(i);
                                                joinStream.writeUTF(reply_messageStr);
                                            }
//                                        **for send to all user**
//                                        userList= gameList.get(gameIndex).getUser();
//                                        for (int j = 0; j < 4; j++){
//                                            if (userList[j] != null){
//                                                DataOutputStream chatStream = new DataOutputStream(socketList.get(userList[j]).getOutputStream() );
//                                                chatStream.writeUTF(inputStr);
//                                            }
//                                        }
                                        }
                                    } else {
                                        //if the game is close
                                    }
                                    break;
                                case "chatWindow":
                                    //String player = message.getString("player");
                                    if (gameIndex != -1) {
                                        user = message.getString("user");
                                        userList = gameList.get(gameIndex).getUser();
                                        for (int j = 0; j < 4; j++) {
                                            if (userList[j] != null && !userList[j].equals(user)) {
                                                DataOutputStream chatStream = new DataOutputStream(socketList.get(userList[j]).getOutputStream());
                                                chatStream.writeUTF(inputStr);
                                            }
                                        }
                                    }
                                    break;
                                case "draw":
                                    //String draw_user = message.getString("manager");
                                    //pic = message.getString("pic");
                                    System.out.println(gameIndex);
                                    if (gameIndex != -1) {
                                        gameList.get(gameIndex).updatePicture(inputStr);
                                        userList = gameList.get(gameIndex).getUser();
                                        for (int j = 0; j < 4; j++) {
                                            if (userList[j] != null /*&& !userList[j].equals(draw_user)*/) {
                                                DataOutputStream chatStream = new DataOutputStream(socketList.get(userList[j]).getOutputStream());
                                                chatStream.writeUTF(inputStr);
                                            }
                                        }
                                    } else {
                                        //if the game is close
                                    }
                                    break;
                                case "kick":
                                    user = message.getString("user");
                                    manager = message.getString("manager");
                                    reply_type = "kickFeedback";
                                    map.put("type", reply_type);
                                    map.put("result", "false");
                                    reply_message = new JSONObject(map);
                                    reply_messageStr = reply_message.toString();
                                    System.out.println(reply_messageStr);
                                    if (gameIndex != -1 && gameList.get(gameIndex).manager.equals(manager)) {
                                        if (gameList.get(gameIndex).checkUser(user)) {
                                            DataOutputStream kickStream = new DataOutputStream(socketList.get(user).getOutputStream());
                                            kickStream.writeUTF(inputStr);
//                                        **for send to all user**
//                                        userList = gameList.get(gameIndex).getUser();
//                                        for (int j = 0; j < 4; j++){
//                                            if (userList[j] != null){
//                                                DataOutputStream chatStream = new DataOutputStream(socketList.get(userList[j]).getOutputStream() );
//                                                chatStream.writeUTF(inputStr);
//                                            }else {
//                                                System.out.println("user is not exist");
//                                            }
//                                        }
                                            kickStream.close();
                                            socketList.get(user).close();
                                            socketList.remove(user);
                                        }
                                    } else {
                                        dos.writeUTF(reply_messageStr);
                                        System.out.println("game is not exist");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid input");
//                                return false;
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid output");
                            e.printStackTrace();
                        }
//                    System.out.println(message.get("operation"));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean checkValid(String[] inputStrSplit) {
        try {
            switch (inputStrSplit[0]) {
                case "create":
                    return inputStrSplit.length == 4;
                case "join":
                    return inputStrSplit.length == 4;
                case "draw":
                    return inputStrSplit.length == 2;
                case "kick":
                    return inputStrSplit.length == 2;
                default:
                    return false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private static int checkGameExist(String[] info) {
        try {
            for (int i = 0; i < gameList.size(); i++) {
                String[] game_info = gameList.get(i).getGameInfo();
                if (game_info[0].equals(info[0]) && game_info[1].equals(info[1])) {
                    return i;
                }
            }
        } catch (NullPointerException e) {
            return -1;
        }
        return -1;
    }
}
