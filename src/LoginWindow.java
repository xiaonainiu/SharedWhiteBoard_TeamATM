import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.regex.*;

import org.json.*;
//import ShareWhiteBoard_TeamATM.src.*;

public class LoginWindow {

    public LoginWindow() {

        frame = new JFrame("LoginWindow");
        frame.setContentPane(this.mainWindow);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        button1.setText("Join");
        button2.setText("Creat");
        textField1.setText("player");
        label1.setText("Please input your UserName");
        IP.setText("IP Address");
        Port.setText("Port Number");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pattern p_ip = Pattern.compile(ip_regex);
                Pattern p_number = Pattern.compile(number);
                Matcher m_ip = p_ip.matcher(textField_ip.getText());
                Matcher m_number = p_number.matcher(textField_port.getText());
                if (m_ip.find() && m_number.find() && Integer.parseInt(textField_port.getText()) < 65536 && Integer.parseInt(textField_port.getText()) > 1024) {
                    String[] s = null;
//                    SWBClient_GUI.main(s);
//                    frame.dispose();
                    // Need judgement and database
                    JSONObject message = new JSONObject();
                    message.put("type", "join");
                    message.put("user", textField1.getText());
                    message.put("ip", textField_ip.getText());
                    message.put("port", textField_port.getText());
                    loginIp = textField_ip.getText();
                    loginPort = textField_port.getText();
                    loginName = textField1.getText();
                    String messageStr = message.toString();
//                    System.out.println(messageStr);
                    try {
                        SWBClient.dos.writeUTF(messageStr);
//                        frame.dispose();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }

                    //System.out.print(message);
                } else {
                    textField_port.setText("Wrong Format");
                    textField_ip.setText("Wrong Format");
                }
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pattern p_ip = Pattern.compile(ip_regex);
                Pattern p_number = Pattern.compile(number);
                Matcher m_ip = p_ip.matcher(textField_ip.getText());
                Matcher m_number = p_number.matcher(textField_port.getText());
                if (m_ip.find() && m_number.find() && Integer.parseInt(textField_port.getText()) < 65536 && Integer.parseInt(textField_port.getText()) > 1024) {
                    String[] s = null;
//                    SWBClient_GUI.main(s);
//                    frame.dispose();
                    // Need judgement and database
                    JSONObject message = new JSONObject();
                    message.put("type", "create");
                    message.put("manager", textField1.getText());
                    message.put("ip", textField_ip.getText());
                    message.put("port", textField_port.getText());
                    loginIp = textField_ip.getText();
                    loginPort = textField_port.getText();
                    loginName = textField1.getText();
//                    System.out.println(message);
                    String messageStr = message.toString();
//                    System.out.println(messageStr);
                    try {
                        SWBClient.dos.writeUTF(messageStr);
//                        frame.dispose();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                } else {
                    textField_port.setText("Wrong Format");
                    textField_ip.setText("Wrong Format");
                }
            }
        });
//        textField1.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
//                    String[] s = null;
//                    SWBClient_GUI.main(s);
//                    frame.dispose();
//                    // Need judgement and database
//                    loginName = textField1.getText();
//                }
//            }
//        });
    }

//    public static void start() {
//        frame = new JFrame("LoginWindow");
//        frame.setContentPane(new LoginWindow().mainWindow);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.pack();
//        frame.setResizable(false);
//        frame.setVisible(true);
//
//    }
//    public void close(){
//
//    }

    private JTextField textField1;
    private JButton button1;
    private JLabel label1;
    private JPanel mainWindow;
    private JTextField textField_ip;
    private JTextField textField_port;
    private JLabel IP; //edit by lzh
    private JLabel Port; //edit by lzh
    private JButton button2;
    static boolean flag = false;
    static JFrame frame;
    static String loginName;
    static String loginIp;
    static String loginPort;
    String ip_regex = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
    String number = "^-?[0-9]+$";
}
