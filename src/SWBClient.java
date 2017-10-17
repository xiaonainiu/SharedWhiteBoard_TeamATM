import org.json.JSONObject;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
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
    static DataOutputStream dos;
    private DataInputStream dis;
    private String ip;
    private String port;
    private String manager;
    private String user;
    static SWBClient_GUI gui;
    static int startx, starty;

    public static void main(String args[]) {

        /**
         * by ES
         * Client
         * start
         */
//        initialize GUI
        SWBClient client = new SWBClient();
        LoginWindow logWin = new LoginWindow();
//        SWBClient_GUI gui1 = new SWBClient_GUI();
//        gui1.start();
//        SWBClient_GUI gui1 = new SWBClient_GUI();

//        demo of json

//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("operation","chatWindow");
//        JSONObject message = new JSONObject(map);
//        String a = message.toString();
//        JSONObject received_Message = new JSONObject(a);
//        System.out.print(received_Message.get("operation"));

//        test code
        Scanner keyboard = new Scanner(System.in);

        try {
            //                socket = this.socket;
            System.out.println("**1**");
            client.socket = new Socket("127.0.0.1", 1234);
            client.dis = new DataInputStream(client.socket.getInputStream());
            client.dos = new DataOutputStream(client.socket.getOutputStream());
//            LoginWindow.main();
            boolean flag = true;
            while (flag) {
                System.out.println("**2**");
                String inputStr = null;
                String outputStr = null;
                String socket_inputStr = null;
                /**
                 * by ES
                 * input
                 * start
                 */
//                    System.out.println("please input:");
//                if ((inputStr = keyboard.nextLine()) != null){
//                    System.out.println("**3**");
//                    if (inputStr == "exit"){
//                        flag = false;
//                    }
//                    try{
//                        System.out.println(inputStr);
//                        client.dos.writeUTF(inputStr);
//                    }catch (IndexOutOfBoundsException e){
//                        System.out.println("Invalid input");
//                    }
//                }

                /**
                 * by ES
                 * input
                 * end
                 */
                if ((socket_inputStr = client.dis.readUTF()) != null) {
                    System.out.println("From IP: " + client.socket.getInetAddress() + "," + client.socket.getPort() + ": " + socket_inputStr);
                    //get the command and make operation
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject message = new JSONObject(socket_inputStr);
                    String type = message.getString("type");
                    String ip = message.getString("ip");
                    String port = message.getString("port");
                    String result;
                    String manager;
                    String user;
                    String targetUser;
                    String userNumber;
                    /*
                    * Edited by LZH
                    * */
                    /*
                    * Change by ES
                    */
                    switch (type) {
                        //manager could receive this message:
                        case "createFeedback":
                            System.out.println("**case: createFeedback**");
                            result = message.getString("result");
                            if (result.equals("true")) {
                                logWin.frame.dispose();
                                client.ip = ip;
                                client.port = port;
                                client.manager = message.getString("manager");
                                client.user = message.getString("manager");
                                System.out.println("**going to start**");
                                gui = new SWBClient_GUI();
                                /* Manager*/
                                if (gui.numberOfCurrentPlayer == 0 && gui.stateOfManager == 0) {
                                    gui.textField1.setText(message.getString("manager").toString());
                                    gui.numberOfCurrentPlayer = gui.numberOfCurrentPlayer + 1;
                                    gui.stateOfManager = gui.stateOfManager + 1;
                                } else {
                                    System.out.println("ERROR: MANAGER ALREADY EXISTED");
                                }
                                /* Manager*/

                                System.out.println("**already start**");
                                //open GUI
                            } else if (result.equals("false")) {
                                //alert : create fail
                                JOptionPane.showMessageDialog(null, "Manager"+message.getString("targetUser")+"Create Fail");
                            } else {
                                //alert : connection fail
                                JOptionPane.showMessageDialog(null, "Connection Fail");
                            }
                            break;
                        case "join":
                            user = message.getString("user");
                            int value = JOptionPane.showConfirmDialog(null, user + " wants to join the game", "Confirm", 0);
                            if (value == 0) {
                                map.put("result", "true");
                            } else {
                                map.put("result", "false");
                            }
//                            System.out.println(user+" want to join in the game(true/false):");
//                            result = keyboard.nextLine();

                            map.put("type", "joinFeedback");
                            map.put("ip", ip);
                            map.put("port", port);
                            map.put("manager", client.manager);
                            map.put("targetUser", user);
                            JSONObject replay_message = new JSONObject(map);
                            String replay_messageStr = replay_message.toString();
                            client.dos.writeUTF(replay_messageStr);
                            System.out.println(replay_messageStr);
                            break;
                        case "joinFeedback":
                            System.out.println("**case: joinFeedback**");
                            result = message.getString("result");
                            if (result.equals("true")) {
                                logWin.frame.dispose();
                                client.ip = message.getString("ip");
                                client.port = message.getString("port");
                                client.manager = message.getString("manager");
                                client.user = message.getString("targetUser");
                                gui = new SWBClient_GUI();
                                /* Manager*/
                                if (gui.numberOfCurrentPlayer == 0 && gui.stateOfManager == 0) {
                                    gui.textField1.setText(message.getString("manager").toString());
                                    gui.numberOfCurrentPlayer = gui.numberOfCurrentPlayer + 1;
                                    gui.stateOfManager = gui.stateOfManager + 1;
                                } else {
                                    System.out.println("ERROR: MANAGER ALREADY EXISTED");
                                }
                                if (gui.stateOfPlayer1 == 0) {
                                    gui.textField2.setText("Waiting for join");
                                }
                                if (gui.stateOfPlayer2 == 0) {
                                    gui.textField3.setText("Waiting for join");
                                }
                                if (gui.stateOfPlayer3 == 0) {
                                    gui.textField4.setText("Waiting for join");
                                }
                                /* Manager*/
                                //open GUI
                            } else if (result.equals("false")) {
                                JOptionPane.showMessageDialog(null, "User "+message.getString("targetUser")+" Join Fail");

                                //alert : create fail
                            } else {
                                JOptionPane.showMessageDialog(null, "Connection Fail");
                                //alert : connection fail
                            }
                            break;
                        case "draw":
                            //draw picture with "draw"
                            try {
                                int x, y, size, isfill;
                                Color color;
                                String str;
                                JSONObject receivedOpe = new JSONObject(socket_inputStr);
                                if (receivedOpe.getString("operation").equals("mousePressed")) {
                                    startx = Integer.parseInt(receivedOpe.getString("startx"));
                                    starty = Integer.parseInt(receivedOpe.getString("starty"));
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("line")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    gui.drawLine(startx, starty, x, y, color, size);
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("pencil")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    gui.drawPencil(startx, starty, x, y, color, size);
                                    startx = x;
                                    starty = y;
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("eraser")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    gui.drawEraser(startx, starty, x, y, color, size);
                                    startx = x;
                                    starty = y;
                                }
                                if (receivedOpe.getString("operation").equals("mouseReleased")) {
                                    gui.caching();
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("rectangle")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    isfill = Integer.parseInt(receivedOpe.getString("isfill"));
                                    gui.drawRect(startx, starty, x, y, color, size, isfill);
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("circle")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    isfill = Integer.parseInt(receivedOpe.getString("isfill"));
                                    gui.drawCircle(startx, starty, x, y, color, size, isfill);
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("oval")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    isfill = Integer.parseInt(receivedOpe.getString("isfill"));
                                    gui.drawOval(startx, starty, x, y, color, size, isfill);
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("polygon")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    isfill = Integer.parseInt(receivedOpe.getString("isfill"));
                                    gui.drawPolygon(startx, starty, x, y, color, size, isfill);
                                }
                                if (receivedOpe.getString("operation").equals("mouseDragged") && receivedOpe.getString("state").equals("square")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    isfill = Integer.parseInt(receivedOpe.getString("isfill"));
                                    gui.drawSquare(startx, starty, x, y, color, size, isfill);
                                }
                                if (receivedOpe.getString("operation").equals("mouseClicked") && receivedOpe.getString("state").equals("text")) {
                                    x = Integer.parseInt(receivedOpe.getString("x"));
                                    y = Integer.parseInt(receivedOpe.getString("y"));
                                    color = ColorConverter.String2Color(receivedOpe.getString("color"));
                                    size = Integer.parseInt(receivedOpe.getString("size"));
                                    str = receivedOpe.getString("content");
                                    gui.drawText(startx, starty, color, size, str);
                                }
                            } catch (Exception e) {

                            }
                            break;
                        case "kickFeedback":
                            result = message.getString("result");
                            if (result.equals("true")){
                                targetUser = message.getString("username");
                                if (targetUser.equals(client.user)){
                                    System.out.println("**going to close**");

                                    //you have been kicked
                                    //close GUI
                                    JOptionPane.showMessageDialog(null, "You have been kicked");
                                    System.exit(0);
                                }else {
                                    // targetUser have been kicked
                                    JOptionPane.showMessageDialog(null, targetUser+" have been kicked");
                                    userNumber = message.getString("userNumber");
                                    if (client.user.equals(client.manager)){
                                        switch (userNumber){
                                            case "1":
                                                gui.player1.setText("");
                                                gui.kick1.setEnabled(false);
                                            case "2":
                                                gui.player2.setText("");
                                                gui.kick2.setEnabled(false);
                                            case "3":
                                                gui.player3.setText("");
                                                gui.kick3.setEnabled(false);
                                        }
                                    }else {
                                        switch (userNumber){
                                            case "1":
                                                gui.player1.setText("");
                                            case "2":
                                                gui.player2.setText("");
                                            case "3":
                                                gui.player3.setText("");
                                        }
                                    }

                                }
                            }else {

                            }
                        case "setUser":
                            if (client.user.equals(client.manager)){
                                System.out.println(message.has("user2"));
                                if (message.has("user1")){
                                    System.out.println(message.getString("user1"));
                                    gui.kick1.setEnabled(true);
                                    gui.textField2.setText(message.getString("user1").toString());
                                }else {
                                    gui.kick1.setEnabled(false);
                                    gui.textField2.setText("Waiting for join");
                                }
                                if (message.has("user2")){
                                    System.out.println(message.getString("user2"));
                                    gui.kick2.setEnabled(true);
                                    gui.textField3.setText(message.getString("user2").toString());
                                }else {
                                    gui.kick2.setEnabled(false);
                                    gui.textField3.setText("Waiting for join");
                                }
                                if (message.has("user3")){
                                    gui.kick3.setEnabled(true);
                                    gui.textField4.setText(message.getString("user3").toString());
                                }else {
                                    gui.kick3.setEnabled(false);
                                    gui.textField4.setText("Waiting for join");
                                }
                            }else {
                                System.out.println(message.has("user2"));
                                if (message.has("user1")){
                                    System.out.println(message.getString("user1"));
                                    gui.textField2.setText(message.getString("user1").toString());
                                }else {
                                    gui.textField2.setText("Waiting for join");
                                }
                                if (message.has("user2")){
                                    System.out.println(message.getString("user2"));
                                    gui.textField3.setText(message.getString("user2").toString());
                                }else {
                                    gui.textField3.setText("Waiting for join");
                                }
                                if (message.has("user3")){
                                    gui.textField4.setText(message.getString("user3").toString());
                                }else {
                                    gui.textField4.setText("Waiting for join");
                                }
                            }
                            //close GUI
                            break;
                        //alert : you have been kicked by manager
                        case "chatWindow":
                            //SWBClient_GUI client_ui = new SWBClient_GUI();
                            SimpleAttributeSet attrset_receiver = new SimpleAttributeSet();
                            SimpleAttributeSet attrset_time = new SimpleAttributeSet();
                            SimpleAttributeSet attrset_selfusername = new SimpleAttributeSet();

                            StyleConstants.setFontSize(attrset_receiver, 24);
                            StyleConstants.setBackground(attrset_receiver, Color.BLUE);

                            StyleConstants.setBold(attrset_time, true);
                            StyleConstants.setFontSize(attrset_time, 15);

                            StyleConstants.setFontSize(attrset_selfusername, 30);
                            StyleConstants.setBackground(attrset_selfusername, Color.BLACK);
                            StyleConstants.setForeground(attrset_selfusername, Color.white);
                            Date date = new Date();
                            //Document docs = client_ui.textPane1.getDocument();
                            Document docs = gui.textPane1.getDocument();
                            try {
                                docs.insertString(docs.getLength(), date.toString() + "\n", attrset_time);
                                docs.insertString(docs.getLength(), message.getString("user").trim() + ":", attrset_selfusername);
                                docs.insertString(docs.getLength(), "  ", null);
                                docs.insertString(docs.getLength(), message.getString("content").trim() + "\n", attrset_receiver);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            System.out.println("Input other operations");
                    }
                    /*
                    * Edited by LZH
                    * */
//                    System.out.println("From IP: "+client.socket.getInetAddress()+","+client.socket.getPort()+": "+inputStr);
                }

            }
            client.dis.close();
            client.socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (SocketException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
