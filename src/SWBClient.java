import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.HashMap;

/**
 * Created by ES on 2017/9/14.
 */
public class SWBClient {
//    InputStream is = new InputStream();
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    public static void main(String args[]){
        /**
         * by ES
         * Client
         * start
         */
//        initialize GUI
        SWBClient client = new SWBClient();
//        SWBClient_GUI gui = new SWBClient_GUI();

//        demo of json

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("operation","chatWindow");
//        JSONObject message = new JSONObject(map);
//        String a = message.toString();
//        JSONObject received_Message = new JSONObject(a);
//        System.out.print(received_Message.get("operation"));

//        test code
        Scanner keyboard = new Scanner(System.in);

        try{
            //                socket = this.socket;
            client.socket = new Socket("127.0.0.1", 1234);
            client.dis = new DataInputStream(client.socket.getInputStream());
            client.dos = new DataOutputStream(client.socket.getOutputStream());
            boolean flag = true;
            while (flag){

                String inputStr = null;
                String outputStr = null;
                String socket_inputStr = null;
                /**
                 * by ES
                 * input
                 * start
                 */

                if ((inputStr = keyboard.nextLine()) != null){
                    if (inputStr == "exit"){
                        flag = false;
                    }
                    String[] inputStrSplit = inputStr.split(" ");
                    try{
                        System.out.println(inputStrSplit);
                        String type = inputStrSplit[0];
                        HashMap<String,String> map = new HashMap<String,String>();
                        switch (type){
                            case "create":
                                map.put("type",type);
                                map.put("ip",inputStrSplit[1]);
                                map.put("port",inputStrSplit[2]);
                                map.put("manager",inputStrSplit[3]);
                                break;
//                                return inputStrSplit.length==4;
                            case "join":
                                map.put("type",type);
                                map.put("ip",inputStrSplit[1]);
                                map.put("port",inputStrSplit[2]);
                                map.put("player",inputStrSplit[3]);
                                break;
//                                return inputStrSplit.length==4;
                            case "draw":
                                map.put("type",type);
                                map.put("pic",inputStrSplit[1]);
                                break;
//                                return inputStrSplit.length==2;
                            case "kick":
                                map.put("type",type);
                                map.put("player",inputStrSplit[1]);
                                break;
//                                return inputStrSplit.length==2;
                            default:
                                System.out.println("Invalid input");
//                                return false;
                        }
                        JSONObject massage = new JSONObject(map);
                        String massageStr = massage.toString();
                        System.out.println(massageStr);
                        client.dos.writeUTF(massageStr);
                    }catch (IndexOutOfBoundsException e){
                        System.out.println("Invalid input");
                    }
//                    if (checkValid(inputStrSplit)){



//                    }
                }

                /**
                 * by ES
                 * input
                 * end
                 */

                if ((socket_inputStr = client.dis.readUTF()) != null){
                    //get the command and make operation
                    HashMap<String,String> map = new HashMap<String,String>();
                    JSONObject massage = new JSONObject(socket_inputStr);
                    System.out.println("From IP: "+client.socket.getInetAddress()+","+client.socket.getPort()+": "+inputStr);
                }

            }
            client.dis.close();
            client.socket.close();
        }catch (UnknownHostException e){
            e.printStackTrace();

        }catch (SocketException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();

        }
    }

//    private static boolean checkValid(String[] inputStrSplit){
//        try{
//            switch (inputStrSplit[0]){
//                case "create":
//                    return inputStrSplit.length==4;
//                case "join":
//                    return inputStrSplit.length==4;
//                case "draw":
//                    return inputStrSplit.length==2;
//                case "kick":
//                    return inputStrSplit.length==2;
//                default:
//                    return false;
//            }
//        }catch (ArrayIndexOutOfBoundsException e){
//            return false;
//        }
//    }
}
