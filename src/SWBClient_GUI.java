import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.swing.text.Document;

import org.json.*;

import java.util.HashMap;

//import sun.rmi.runtime.Log;

/**
 * Created by ES on 2017/9/14.
 */
public class SWBClient_GUI extends JFrame {
    private JPanel mainWindow;
    private JTabbedPane tabbedPane1;
    private JButton bt_new;
    private JButton bt_save;
    private JButton bt_open;
    private JButton bt_saveas;
    private JButton bt_pencil;
    private JComboBox cb_pencil;
    private JButton bt_color;
    private JPanel board;
    private JTextField text_info;
    private JButton sendButton; // chatwindow
    private JTextArea textArea1; // chatwindow
    private JPanel cl000;
    private JPanel cl111;
    private JPanel cl666;
    private JPanel clccc;
    private JPanel cl100;
    private JPanel cl150;
    private JPanel cl110;
    private JPanel cl510;
    private JPanel cl010;
    private JPanel cl015;
    private JPanel cl011;
    private JPanel cl051;
    private JPanel cl001;
    private JPanel cl501;
    private JPanel cl101;
    private JPanel cl105;
    private JButton bt_eraser;
    private JComboBox cb_eraser;
    public JTextPane textPane1; //chatwindow
    private JButton bt_line;
    private JButton bt_rect;
    private JButton bt_circle;
    private JButton bt_oval;
    private JButton bt_polygon;
    private JComboBox cb_shape;
    private JButton bt_text;
    //private JTextField textField1;
    private JPanel panel;
    private JCheckBox check_fill;
    private JButton bt_square;
    private JCheckBox checkBox1;
    private JButton bt_exit;
    private JTextArea textArea2;
    private JButton bt_undo;
    public JTextField textField1; //used for kick out
    public JTextField textField2; // used for kick out
    public JTextField textField3; // used for kick out
    public JTextField textField4; // used for kick out
    public JButton kick1;
    public JButton kick2;
    public JButton kick3;
    public JLabel manager; //used for kick out
    public JLabel player1; //used for kick out
    public JLabel player2; //used for kick out
    public JLabel player3; //used for kick out
    public int numberOfCurrentPlayer = 0; // used for kick out
    public int stateOfManager = 0;// used for kick out
    public int stateOfPlayer1 = 0;// used for kick out
    public int stateOfPlayer2 = 0;// used for kick out
    public int stateOfPlayer3 = 0;// used for kick out
    private int startx;
    private int starty;
    private int x;
    private int y;
    Graphics g;
    private Color color = Color.black;
    private Color eraser_color = Color.white;
    private String state = "";
    LinkedList<Shape> shapeList = new LinkedList<Shape>();
    Shape shape;
    int isFill = 2;
    int tempx1, tempy1, tempx2, tempy2;
    int i = 2;
    private DataInputStream dis;
    private DataOutputStream dos;


    public SWBClient_GUI() {

        JFrame frame = new JFrame("SWBClient_GUI");
        frame.setContentPane(this.mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false); // edit by LZH
        frame.setVisible(true);

//        set color
        cl000.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 0, 0);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl111.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 255, 255);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_other.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl666.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(102, 102, 102);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        clccc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(204, 204, 204);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_other.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl100.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 0, 0);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_red.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl150.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 128, 0);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_other.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl110.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 255, 0);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_other.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl510.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(128, 255, 0);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_green.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl010.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 255, 0);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_green.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl015.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 255, 128);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_green.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl011.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 255, 255);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_blue.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl051.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 128, 255);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_blue.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl001.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 0, 255);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_blue.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl501.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(128, 0, 255);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_purple.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl101.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 0, 255);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_pink.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });
        cl105.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 0, 128);
                if (state == "pencil") {
                    java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit_pink.png").getImage(), new Point(2, 28), "pencil");
                    board.setCursor(cursor);
                }
            }
        });

        /**
         * This part is written by LZH
         * Chat Window
         * */
        //Button Setting
        sendButton.setText("SEND");
        Font fb = new Font("TimesRoman", Font.ITALIC, 50);
        sendButton.setFont(fb);

        //TextArea1 setting
        Font ft = new Font("TimesRoman", Font.ITALIC, 20);
        textArea1.setText("Click SEND/Press Control+Enter");
        textArea1.setFont(ft);

        //"Enter+Control" keyboard monitor
        textArea1.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
                    /*This part is used to change font format
                     * */
                    SimpleAttributeSet attrset = new SimpleAttributeSet();
                    SimpleAttributeSet attrset_Time = new SimpleAttributeSet();
                    SimpleAttributeSet attrset_selfusername = new SimpleAttributeSet();
                    StyleConstants.setFontSize(attrset, 24);
                    StyleConstants.setBold(attrset_Time, true);
                    StyleConstants.setBackground(attrset, Color.PINK);
                    StyleConstants.setFontSize(attrset_Time, 15);
                    StyleConstants.setBackground(attrset_selfusername, Color.BLACK);
                    StyleConstants.setForeground(attrset_selfusername, Color.white);
                    StyleConstants.setFontSize(attrset_selfusername, 30);
                    Date date = new Date();
                    /*This part is insert content
                     * PROBLEM:1) Label need to change the font style!!!
					 * PROBLEM:2) Different messages need to be placed in right/left side
					 */
                    Document docs = textPane1.getDocument();
                    /*
                     * Both comments are used to input different formats for JTextPane
					 * */
                    try {
                        if (textArea1.getText().length() != 0) {
                            docs.insertString(docs.getLength(), date.toString() + "\n", attrset_Time);
                            docs.insertString(docs.getLength(), LoginWindow.loginName.trim() + ":", attrset_selfusername);
                            docs.insertString(docs.getLength(), "  ", null);
                            docs.insertString(docs.getLength(), textArea1.getText().trim() + "\n", attrset);
                            System.out.println(textArea1.getText());
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("State", "ChatWindow");
                            map.put("UserName", LoginWindow.loginName.trim());
                            map.put("Content", textArea1.getText());
                            /*
                            *This "message" is JSON OBJECT, if need transmit String,
                            * you need use "message.toString();"
                             */
                            JSONObject message = new JSONObject(map);
                            textArea1.setText("");
                        } else {
                            textArea1.setText("Input can not be EMPTY");
                        }
                    } catch (BadLocationException e1) {
                        e1.printStackTrace();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyTyped(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

        });

        //"Enter_Button" keyboard monitor
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Output Format
                SimpleAttributeSet setWord = new SimpleAttributeSet();
                SimpleAttributeSet setTime = new SimpleAttributeSet();
                SimpleAttributeSet attrset_selfusername = new SimpleAttributeSet();
                StyleConstants.setBackground(attrset_selfusername, Color.BLACK);
                StyleConstants.setForeground(attrset_selfusername, Color.white);
                StyleConstants.setFontSize(attrset_selfusername, 30);
                StyleConstants.setFontSize(setWord, 24);
                StyleConstants.setBold(setTime, true);
                StyleConstants.setBackground(setWord, Color.PINK);
                StyleConstants.setFontSize(setTime, 15);
                Date date = new Date();

                //JTextPane input setting
                Document docs = textPane1.getDocument();
                try {
                    if (textArea1.getText().length() != 0) {
                        docs.insertString(docs.getLength(), date.toString() + "\n", setTime);
                        docs.insertString(docs.getLength(), LoginWindow.loginName.trim() + ":", attrset_selfusername);
                        docs.insertString(docs.getLength(), "  ", null);
                        docs.insertString(docs.getLength(), textArea1.getText().trim() + "\n", setWord);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("type", "chatWindow");
                        map.put("user", LoginWindow.loginName.trim());
                        map.put("content", textArea1.getText());
                        map.put("ip", LoginWindow.loginIp);
                        map.put("port", LoginWindow.loginPort);
                            /*
                            *This "message" is JSON OBJECT, if need transmit String,
                            * you need use "message.toString();"
                             */
                        JSONObject message = new JSONObject(map);
                        String messageStr = message.toString();
//                    System.out.println(messageStr);
                        try {
                            SWBClient.dos.writeUTF(messageStr);
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                        textArea1.setText("");
                    } else {
                        textArea1.setText("Input can not be EMPTY");
                    }
                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }
            }
        });
        /**
         * This part is written by LZH
         * Chat Window
         * */

        /**
         * This part is written by LZH
         * kick out
         * */
        /*
        WARNING!
        variable: receivedMesg should be message received from server, need be edited
         */
        JSONObject receivedMesg = new JSONObject();
        /*
        WARNING!
        variable: receivedMesg should be message received from server, need be edited
         */
        manager.setText("Manager");
        player1.setText("Player_1");
        player2.setText("Player_2");
        player3.setText("Player_3");
        kick1.setText("Kick");
        kick2.setText("Kick");
        kick3.setText("Kick");
        kick1.setEnabled(false);
        kick2.setEnabled(false);
        kick3.setEnabled(false);
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);

        /*
        WARNING!
        This two is used to test, should be deleted
         */
//        receivedMesg.put("type", "create");
//        receivedMesg.put("username", "Andrew");
        /*
        WARNING1
        This two is used to test, should be deleted
         */

//        if (receivedMesg.getString("type").toString().equalsIgnoreCase("create")) {
//            if (numberOfCurrentPlayer == 0 && stateOfManager == 0) {
//                textField1.setText(receivedMesg.getString("username").toString());
//                numberOfCurrentPlayer = numberOfCurrentPlayer + 1;
//                stateOfManager = stateOfManager + 1;
//            } else {
//                System.out.println("ERROR: MANAGER ALREADY EXISTED");
//            }
//        }
//        if (receivedMesg.getString("type").toString().equalsIgnoreCase("join")) {
//            if (stateOfPlayer1 == 0) {
//                textField2.setText(receivedMesg.getString("username").toString());
//                numberOfCurrentPlayer = numberOfCurrentPlayer + 1;
//                stateOfPlayer1 = stateOfPlayer1 + 1;
//            } else if (stateOfPlayer1 != 0 && stateOfPlayer2 == 0) {
//                textField3.setText(receivedMesg.getString("username").toString());
//                numberOfCurrentPlayer = numberOfCurrentPlayer + 1;
//                stateOfPlayer2 = stateOfPlayer2 + 1;
//            } else if (stateOfPlayer2 != 0 && stateOfPlayer3 == 0) {
//                textField4.setText(receivedMesg.getString("username").toString());
//                numberOfCurrentPlayer = numberOfCurrentPlayer + 1;
//                stateOfPlayer3 = stateOfPlayer3 + 1;
//            } else {
//                System.out.println("ERROR: PLAYER FULL");
//            }
//        }
        kick1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                if (!textField2.getText().toString().equalsIgnoreCase("Waiting for joining")) {
//                    stateOfPlayer1 = 0;
//                    numberOfCurrentPlayer = numberOfCurrentPlayer - 1;
                    JSONObject kickplayer1 = new JSONObject();
                    kickplayer1.put("type", "kick");
                    kickplayer1.put("username", textField2.getText().toString());
                    kickplayer1.put("manager",LoginWindow.loginName);
                    kickplayer1.put("ip", LoginWindow.loginIp);
                    kickplayer1.put("port", LoginWindow.loginPort);
                    String kick1_str = kickplayer1.toString();
                    /*
                                This part used to update the name of exited player
                                The position can not be changed!!!
                                 */
//                    if (stateOfPlayer1 == 0) {
//                        textField2.setText("Waiting for join");
//                    }
//                    if (stateOfPlayer2 == 0) {
//                        textField3.setText("Waiting for join");
//                    }
//                    if (stateOfPlayer3 == 0) {
//                        textField4.setText("Waiting for join");
//                    }
                                /*
                                This part used to update the name of exited player
                                The position can not be changed!!!
                                 */
                    try {
                        SWBClient.dos.writeUTF(kick1_str);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
//                } else {
//                    System.out.println("NO PLAYER ERROR");
//                }
            }
        });
        kick2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                if (!textField3.getText().toString().equalsIgnoreCase("Waiting for join")) {
//                    stateOfPlayer2 = 0;
//                    numberOfCurrentPlayer = numberOfCurrentPlayer - 1;
                    JSONObject kickplayer2 = new JSONObject();
                    kickplayer2.put("type", "kick");
                    kickplayer2.put("username", textField3.getText().toString());
                    kickplayer2.put("manager",LoginWindow.loginName);
                    kickplayer2.put("ip", LoginWindow.loginIp);
                    kickplayer2.put("port", LoginWindow.loginPort);
                    String kick2_str = kickplayer2.toString();
                    /*
                                This part used to update the name of exited player
                                The position can not be changed!!!
                                 */
//                    if (stateOfPlayer1 == 0) {
//                        textField2.setText("Waiting for join");
//                    }
//                    if (stateOfPlayer2 == 0) {
//                        textField3.setText("Waiting for join");
//                    }
//                    if (stateOfPlayer3 == 0) {
//                        textField4.setText("Waiting for join");
//                    }
                                /*
                                This part used to update the name of exited player
                                The position can not be changed!!!
                                 */
                    try {
                        SWBClient.dos.writeUTF(kick2_str);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
//                } else {
//                    System.out.println("NO PLAYER ERROR");
//                }
            }
        });
        kick3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                if (!textField4.getText().toString().equalsIgnoreCase("Waiting for join")) {
//                    stateOfPlayer3 = 0;
//                    numberOfCurrentPlayer = numberOfCurrentPlayer - 1;
                    JSONObject kickplayer3 = new JSONObject();
                    kickplayer3.put("type", "kick");
                    kickplayer3.put("username", textField4.getText().toString());
                    kickplayer3.put("manager",LoginWindow.loginName);
                    kickplayer3.put("ip", LoginWindow.loginIp);
                    kickplayer3.put("port", LoginWindow.loginPort);
                    String kick3_str = kickplayer3.toString();
                    /*
                                This part used to update the name of exited player
                                The position can not be changed!!!
                                 */
//                    if (stateOfPlayer1 == 0) {
//                        textField2.setText("Waiting for join");
//                    }
//                    if (stateOfPlayer2 == 0) {
//                        textField3.setText("Waiting for join");
//                    }
//                    if (stateOfPlayer3 == 0) {
//                        textField4.setText("Waiting for join");
//                    }
                                /*
                                This part used to update the name of exited player
                                The position can not be changed!!!
                                 */
                    try {
                        SWBClient.dos.writeUTF(kick3_str);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
//                } else {
//                    System.out.println("NO PLAYER ERROR");
//                }
            }
        });



        /**
         * This part is written by LZH
         * kick out
         * */


//        click color bt
        bt_color.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = JColorChooser.showDialog(null, "choose color", color);
            }
        });
//        update position information
        board.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                x = e.getX();
                y = e.getY();
                text_info.setText("Position: (" + x + "," + y + ")");
            }
        });
//        drawing
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                startx = e.getX();
                starty = e.getY();
                if (shape != null && shape.contains(e.getX(), e.getY()) && i == 1) {
                    tempx1 = shape.x1;
                    tempy1 = shape.y1;
                    tempx2 = shape.x2;
                    tempy2 = shape.y2;
                    shapeList.remove(shape);
                } else {
                    //shape = null;
                }
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("type", "draw");
                map.put("operation", "mousePressed");
                map.put("startx", "" + startx);
                map.put("starty", "" + starty);
                map.put("ip", LoginWindow.loginIp);
                map.put("port", LoginWindow.loginPort);
                JSONObject startPosition = new JSONObject(map);
                String startPositionStr = startPosition.toString();
                try {
                    SWBClient.dos.writeUTF(startPositionStr);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        board.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                try {
                    super.mouseDragged(e);
                    if (shape != null && shape.contains(e.getX(), e.getY()) && i == 1 && state == "rectangle") {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin")
                            shape = new Rectangle(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 1, isFill);
                        else if (cb_shape.getSelectedItem() == "normal")
                            shape = new Rectangle(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 2, isFill);
                        else
                            shape = new Rectangle(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 5, isFill);
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (shape != null && shape.contains(e.getX(), e.getY()) && i == 1 && state == "line") {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin")
                            shape = new Line(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 1);
                        else if (cb_shape.getSelectedItem() == "normal")
                            shape = new Line(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 2);
                        else
                            shape = new Line(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 5);
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (shape != null && shape.contains(e.getX(), e.getY()) && i == 1 && state == "circle") {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin")
                            shape = new Circle(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 1, isFill);
                        else if (cb_shape.getSelectedItem() == "normal")
                            shape = new Circle(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 2, isFill);
                        else
                            shape = new Circle(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 5, isFill);
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (shape != null && shape.contains(e.getX(), e.getY()) && i == 1 && state == "oval") {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin")
                            shape = new Oval(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 1, isFill);
                        else if (cb_shape.getSelectedItem() == "normal")
                            shape = new Oval(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 2, isFill);
                        else
                            shape = new Oval(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 5, isFill);
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (shape != null && shape.contains(e.getX(), e.getY()) && i == 1 && state == "polygon") {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin")
                            shape = new Polygon(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 1, isFill);
                        else if (cb_shape.getSelectedItem() == "normal")
                            shape = new Polygon(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 2, isFill);
                        else
                            shape = new Polygon(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 5, isFill);
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (shape != null && shape.contains(e.getX(), e.getY()) && i == 1 && state == "square") {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin")
                            shape = new Square(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 1, isFill);
                        else if (cb_shape.getSelectedItem() == "normal")
                            shape = new Square(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 2, isFill);
                        else
                            shape = new Square(tempx1 - (startx - x), tempy1 - (starty - y), tempx2 - (startx - x), tempy2 - (starty - y), g.getColor(), 5, isFill);
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    }
                    if (state == "line" && i == 2) {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin") {
                            shape = new Line(startx, starty, x, y, g.getColor(), 1);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "line");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_shape.getSelectedItem() == "normal") {
                            shape = new Line(startx, starty, x, y, g.getColor(), 2);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "line");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 2);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Line(startx, starty, x, y, g.getColor(), 5);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "line");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (state == "pencil") {
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_pencil.getSelectedItem() == "thin") {
                            shape = new Line(startx, starty, x, y, g.getColor(), 1);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "pencil");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_pencil.getSelectedItem() == "normal") {
                            shape = new Line(startx, starty, x, y, g.getColor(), 2);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "pencil");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 2);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Line(startx, starty, x, y, g.getColor(), 5);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "pencil");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (shape != null) shapeList.add(shape);
                        shape = null;
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                        startx = e.getX();
                        starty = e.getY();
                    } else if (state == "eraser") {
                        g = board.getGraphics();
                        g.setColor(eraser_color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_eraser.getSelectedItem() == "small") {
                            shape = new Line(startx, starty, x, y, g.getColor(), 1);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "eraser");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_eraser.getSelectedItem() == "medium") {
                            shape = new Line(startx, starty, x, y, g.getColor(), 5);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "eraser");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Line(startx, starty, x, y, g.getColor(), 10);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "eraser");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 10);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (shape != null) shapeList.add(shape);
                        shape = null;
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                        startx = e.getX();
                        starty = e.getY();
                    } else if (state == "rectangle" && i == 2) {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin") {
                            shape = new Rectangle(startx, starty, x, y, g.getColor(), 1, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "rectangle");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_shape.getSelectedItem() == "normal") {
                            shape = new Rectangle(startx, starty, x, y, g.getColor(), 2, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "rectangle");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 2);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Rectangle(startx, starty, x, y, g.getColor(), 5, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "rectangle");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (state == "circle" && i == 2) {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin") {
                            shape = new Circle(startx, starty, x, y, g.getColor(), 1, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "circle");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_shape.getSelectedItem() == "normal") {
                            shape = new Circle(startx, starty, x, y, g.getColor(), 2, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "circle");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 2);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Circle(startx, starty, x, y, g.getColor(), 5, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "circle");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (state == "oval" && i == 2) {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin") {
                            shape = new Oval(startx, starty, x, y, g.getColor(), 1, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "oval");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_shape.getSelectedItem() == "normal") {
                            shape = new Oval(startx, starty, x, y, g.getColor(), 2, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "oval");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 2);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Oval(startx, starty, x, y, g.getColor(), 5, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "oval");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (state == "polygon" && i == 2) {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin") {
                            shape = new Polygon(startx, starty, x, y, g.getColor(), 1, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "polygon");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_shape.getSelectedItem() == "normal") {
                            shape = new Polygon(startx, starty, x, y, g.getColor(), 2, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "polygon");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 2);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Polygon(startx, starty, x, y, g.getColor(), 5, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "polygon");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    } else if (state == "square" && i == 2) {
                        board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        g = board.getGraphics();
                        g.setColor(color);
                        x = e.getX();
                        y = e.getY();
                        if (cb_shape.getSelectedItem() == "thin") {
                            shape = new Square(startx, starty, x, y, g.getColor(), 1, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "square");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 1);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (cb_shape.getSelectedItem() == "normal") {
                            shape = new Square(startx, starty, x, y, g.getColor(), 2, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "square");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 2);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            shape = new Square(startx, starty, x, y, g.getColor(), 5, isFill);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("type", "draw");
                            map.put("state", "square");
                            map.put("operation", "mouseDragged");
                            map.put("x", "" + x);
                            map.put("y", "" + y);
                            map.put("size", "" + 5);
                            map.put("isfill", "" + isFill);
                            map.put("color", ColorConverter.Color2String(g.getColor()));
                            map.put("ip", LoginWindow.loginIp);
                            map.put("port", LoginWindow.loginPort);
                            JSONObject startPosition = new JSONObject(map);
                            String startPositionStr = startPosition.toString();
                            try {
                                SWBClient.dos.writeUTF(startPositionStr);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                        repaint();
                        text_info.setText("Position: (" + x + "," + y + ")");
                    }
                } catch (Exception e1) {

                }
            }
        });

        bt_pencil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state = "pencil";
                java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/edit.png").getImage(), new Point(2, 28), "pencil");
                board.setCursor(cursor);
            }
        });
        bt_eraser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state = "eraser";
                java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("ym/eraser.png").getImage(), new Point(2, 28), "eraser");
                board.setCursor(cursor);
            }
        });
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    super.mouseReleased(e);
                    g = board.getGraphics();
                    if (shape != null) shapeList.add(shape);
                    //shape = null;
                    repaint();
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("type", "draw");
                    map.put("operation", "mouseReleased");
                    map.put("ip", LoginWindow.loginIp);
                    map.put("port", LoginWindow.loginPort);
                    JSONObject startPosition = new JSONObject(map);
                    String startPositionStr = startPosition.toString();

                    SWBClient.dos.writeUTF(startPositionStr);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        bt_line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "line";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        bt_rect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "rectangle";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        bt_circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "circle";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        bt_oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "oval";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        bt_text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "text";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (state == "text") {
                    g = board.getGraphics();
                    g.setColor(color);
                    x = e.getX();
                    y = e.getY();
                    if (cb_shape.getSelectedItem() == "thin") {
                        shape = new Text(startx, starty, g.getColor(), 1, textArea2.getText());
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("type", "draw");
                        map.put("state", "text");
                        map.put("operation", "mouseClicked");
                        map.put("x", "" + x);
                        map.put("y", "" + y);
                        map.put("size", "" + 1);
                        map.put("content", textArea2.getText());
                        map.put("color", ColorConverter.Color2String(g.getColor()));
                        map.put("ip", LoginWindow.loginIp);
                        map.put("port", LoginWindow.loginPort);
                        JSONObject startPosition = new JSONObject(map);
                        String startPositionStr = startPosition.toString();
                        try {
                            SWBClient.dos.writeUTF(startPositionStr);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else if (cb_shape.getSelectedItem() == "normal") {
                        shape = new Text(startx, starty, g.getColor(), 2, textArea2.getText());
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("type", "draw");
                        map.put("state", "text");
                        map.put("operation", "mouseClicked");
                        map.put("x", "" + x);
                        map.put("y", "" + y);
                        map.put("size", "" + 2);
                        map.put("content", textArea2.getText());
                        map.put("color", ColorConverter.Color2String(g.getColor()));
                        map.put("ip", LoginWindow.loginIp);
                        map.put("port", LoginWindow.loginPort);
                        JSONObject startPosition = new JSONObject(map);
                        String startPositionStr = startPosition.toString();
                        try {
                            SWBClient.dos.writeUTF(startPositionStr);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        shape = new Text(startx, starty, g.getColor(), 5, textArea2.getText());
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("type", "draw");
                        map.put("state", "text");
                        map.put("operation", "mouseClicked");
                        map.put("x", "" + x);
                        map.put("y", "" + y);
                        map.put("size", "" + 5);
                        map.put("content", textArea2.getText());
                        map.put("color", ColorConverter.Color2String(g.getColor()));
                        map.put("ip", LoginWindow.loginIp);
                        map.put("port", LoginWindow.loginPort);
                        JSONObject startPosition = new JSONObject(map);
                        String startPositionStr = startPosition.toString();
                        try {
                            SWBClient.dos.writeUTF(startPositionStr);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    repaint();
                    text_info.setText("Position: (" + x + "," + y + ")");
                }
            }
        });
        bt_polygon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "polygon";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        bt_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bt_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = JOptionPane.showConfirmDialog(null, "Save current painting?", "Confirm", 0);
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                if (value == 0) {
                    saveFile();
                    shapeList.removeAll(shapeList);
                    board.repaint();
                }
                if (value == 1) {
                    shapeList.removeAll(shapeList);
                    board.repaint();
                }
            }
        });
        bt_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = JOptionPane.showConfirmDialog(null, "Save current painting?", "Confirm", 0);
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                if (value == 0) {
                    saveFile();
                    shapeList.removeAll(shapeList);
                    board.repaint();
                    try {
                        JFileChooser chooser = new JFileChooser();
                        chooser.showOpenDialog(null);
                        File file = chooser.getSelectedFile();
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        shapeList = (LinkedList<Shape>) ois.readObject();
                        repaint();
                        ois.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                if (value == 1) {
                    shapeList.removeAll(shapeList);
                    board.repaint();
                    try {
                        JFileChooser chooser = new JFileChooser();
                        chooser.showOpenDialog(null);
                        File file = chooser.getSelectedFile();
                        FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        shapeList = (LinkedList<Shape>) ois.readObject();
                        repaint();
                        ois.close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        bt_saveas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFileAs();
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        check_fill.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                isFill = e.getStateChange();
            }
        });
        bt_square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = "square";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        checkBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                i = e.getStateChange();
            }
        });
        bt_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = JOptionPane.showConfirmDialog(null, "Save current painting?", "Confirm", 0);
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                if (value == 0) {
                    saveFile();
                    System.exit(0);
                }
                if (value == 1) {
                    System.exit(0);
                }
            }
        });
        bt_undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (shapeList.size() > 0) {
                    shapeList.removeLast();
                    shape = null;
                    repaint();
                }
            }
        });
    }

    public void repaint() {
        update(g);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        Graphics temp_g;
        BufferedImage image = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_RGB);
        temp_g = image.createGraphics();
        temp_g.setColor(new Color(255, 255, 255));
        temp_g.fillRect(0, 0, board.getWidth(), board.getHeight());
        Graphics2D temp_g2d = (Graphics2D) temp_g;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(board.getX(), board.getY(), board.getWidth(), board.getHeight());
//        for (Shape s : shapeList) s.draw(g2d);
//        if (shape != null) {
//            shape.draw(g2d);
//        }
        for (Shape s : shapeList)
            s.draw(temp_g2d);
        if (shape != null) {
            shape.draw(temp_g2d);
        }
        g2d.drawImage(image, board.getLocation().x, board.getLocation().y - 180, board.getWidth(), board.getHeight(), null);
    }

//    public static void start() {
//        JFrame frame = new JFrame("SWBClient_GUI");
//        frame.setContentPane(new SWBClient_GUI().mainWindow);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setResizable(false); // edit by LZH
//        frame.setVisible(true);
//    }

    public void saveFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(shapeList);
            JOptionPane.showMessageDialog(null, "Save successfully!");
            oos.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void saveFileAs() {
        JFileChooser chooser = new JFileChooser();
        chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        try {
            Graphics temp_g;
            BufferedImage image = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_RGB);
            temp_g = image.createGraphics();
            temp_g.setColor(new Color(255, 255, 255));
            temp_g.fillRect(0, 0, board.getWidth(), board.getHeight());
            Graphics2D temp_g2d = (Graphics2D) temp_g;
            for (Shape s : shapeList)
                s.draw(temp_g2d);
            ImageIO.write(image, "jpg", file);
            JOptionPane.showMessageDialog(null, "Save successfully!");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void drawLine(int startx, int starty, int x, int y, Color color, int size) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Line(startx, starty, x, y, color, size);
        repaint();
    }

    public void drawText(int startx, int starty, Color color, int size, String str) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Text(startx, starty, color, size, str);
        repaint();
    }

    public void drawPencil(int startx, int starty, int x, int y, Color color, int size) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Line(startx, starty, x, y, color, size);
        if (shape != null) shapeList.add(shape);
        shape = null;
        repaint();
    }

    public void drawEraser(int startx, int starty, int x, int y, Color color, int size) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Line(startx, starty, x, y, color, size);
        if (shape != null) shapeList.add(shape);
        shape = null;
        repaint();
    }

    public void drawRect(int startx, int starty, int x, int y, Color color, int size, int isfill) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Rectangle(startx, starty, x, y, color, size, isfill);
        repaint();
    }

    public void drawCircle(int startx, int starty, int x, int y, Color color, int size, int isfill) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Circle(startx, starty, x, y, color, size, isfill);
        repaint();
    }

    public void drawOval(int startx, int starty, int x, int y, Color color, int size, int isfill) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Oval(startx, starty, x, y, color, size, isfill);
        repaint();
    }

    public void drawPolygon(int startx, int starty, int x, int y, Color color, int size, int isfill) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Polygon(startx, starty, x, y, color, size, isfill);
        repaint();
    }

    public void drawSquare(int startx, int starty, int x, int y, Color color, int size, int isfill) {
        g = board.getGraphics();
        //g.setColor(color);
        shape = new Square(startx, starty, x, y, color, size, isfill);
        repaint();
    }

    public void caching() {
        g = board.getGraphics();
        if (shape != null) shapeList.add(shape);
        //shape = null;
        repaint();
    }
}

abstract class Shape implements Serializable {
    public int x1, y1, x2, y2;
    public Color color;
    public int width;

    public abstract void draw(Graphics2D g2d);

    public abstract boolean contains(int x, int y);
}

class Pencil extends Shape {
    public Pencil(int x1, int y1, int x2, int y2, Color color, int width) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.drawLine(x1, y1, x2, y2);
    }

    public boolean contains(int x, int y) {
        return false;
    }
}

class Eraser extends Shape {
    public Eraser(int x1, int y1, int x2, int y2, Color color, int width) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.drawLine(x1, y1, x2, y2);
    }

    public boolean contains(int x, int y) {
        return false;
    }
}

class Line extends Shape {
    public Line(int x1, int y1, int x2, int y2, Color color, int width) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.drawLine(x1, y1, x2, y2);
    }

    public boolean contains(int x, int y) {
        if (x <= x2 && x >= x1 && y >= y1 && y <= y2)
            return true;
        else
            return false;
    }
}

class Rectangle extends Shape {
    int isFill;

    public Rectangle(int x1, int y1, int x2, int y2, Color color, int width, int isFill) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.isFill = isFill;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.drawRect(x1, y1, x2 - x1, y2 - y1);
        if (isFill == 1)
            g2d.fillRect(x1, y1, x2 - x1, y2 - y1);
    }

    public boolean contains(int x, int y) {
        if (x <= x2 && x >= x1 && y >= y1 && y <= y2)
            return true;
        else
            return false;
    }
}

class Circle extends Shape {
    int isFill;

    public Circle(int x1, int y1, int x2, int y2, Color color, int width, int isFill) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.isFill = isFill;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        if ((y2 - y1) >= (x2 - x1))
            g2d.drawOval(x1, y1, x2 - x1, x2 - x1);
        else
            g2d.drawOval(x1, y1, y2 - y1, y2 - y1);
        if (isFill == 1) {
            if ((y2 - y1) >= (x2 - x1))
                g2d.fillOval(x1, y1, x2 - x1, x2 - x1);
            else
                g2d.fillOval(x1, y1, y2 - y1, y2 - y1);
        }
    }

    public boolean contains(int x, int y) {
        if (x <= x2 && x >= x1 && y >= y1 && y <= y2)
            return true;
        else
            return false;
    }
}

class Oval extends Shape {
    int isFill;

    public Oval(int x1, int y1, int x2, int y2, Color color, int width, int isFill) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.isFill = isFill;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.drawOval(x1, y1, x2 - x1, y2 - y1);
        if (isFill == 1) {
            g2d.fillOval(x1, y1, x2 - x1, y2 - y1);
        }
    }

    public boolean contains(int x, int y) {
        if (x <= x2 && x >= x1 && y >= y1 && y <= y2)
            return true;
        else
            return false;
    }
}

class Polygon extends Shape {
    int isFill;

    public Polygon(int x1, int y1, int x2, int y2, Color color, int width, int isFill) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.isFill = isFill;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        int[] x = {x1, (x2 + x1) / 2, x2, (int) (x2 - 0.31 * (x2 - x1) / 2), (int) (x1 + 0.31 * (x2 - x1) / 2)};
        int[] y = {(int) (y1 + 0.72 * (x2 - x1) / 2), y1, (int) (y1 + 0.72 * (x2 - x1) / 2), y2, y2};
        g2d.drawPolygon(x, y, 5);
        if (isFill == 1) {
            g2d.fillPolygon(x, y, 5);
        }
    }

    public boolean contains(int x, int y) {
        if (x <= x2 && x >= x1 && y >= y1 && y <= y2)
            return true;
        else
            return false;
    }
}

class Text extends Shape {
    String str = new String();

    public Text(int x1, int y1, Color color, int width, String str) {
        this.x1 = x1;
        this.y1 = y1;
        this.color = color;
        this.width = width;
        this.str = str;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.drawString(str, x1, y1);
    }

    public boolean contains(int x, int y) {
        return false;
    }
}

class Square extends Shape {
    int isFill;

    public Square(int x1, int y1, int x2, int y2, Color color, int width, int isFill) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.width = width;
        this.isFill = isFill;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        if ((y2 - y1) >= (x2 - x1))
            g2d.drawRect(x1, y1, x2 - x1, x2 - x1);
        else
            g2d.drawRect(x1, y1, y2 - y1, y2 - y1);
        if (isFill == 1) {
            if ((y2 - y1) >= (x2 - x1))
                g2d.fillRect(x1, y1, x2 - x1, x2 - x1);
            else
                g2d.fillRect(x1, y1, y2 - y1, y2 - y1);
        }
    }

    public boolean contains(int x, int y) {
        if (x <= x2 && x >= x1 && y >= y1 && y <= y2)
            return true;
        else
            return false;
    }
}

class ColorConverter {
    public static Color String2Color(String str) {
        int i = Integer.parseInt(str.substring(1), 16);
        return new Color(i);
    }

    public static String Color2String(Color color) {
        String R = Integer.toHexString(color.getRed());
        R = R.length() < 2 ? ('0' + R) : R;
        String B = Integer.toHexString(color.getBlue());
        B = B.length() < 2 ? ('0' + B) : B;
        String G = Integer.toHexString(color.getGreen());
        G = G.length() < 2 ? ('0' + G) : G;
        return '#' + R + G + B;
    }
}


