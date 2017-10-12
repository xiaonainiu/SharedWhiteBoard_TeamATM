import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.*;

public class LoginWindow {
    private JTextField textField1;
    private JButton bt_create;
    private JLabel label1;
    private JPanel mainWindow;
    public JTextField textField_ip;
    public JTextField textField_port;
    private JLabel IP; //edit by lzh
    private JLabel Port; //edit by lzh
    private JButton joinButton;
    static boolean flag = false;
    static JFrame frame;
    static String loginName;
    String ip_regex ="((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";
    String number = "^-?[0-9]+$";
    public LoginWindow() {
        bt_create.setText("Login");
        textField1.setText("player");
        label1.setText("Please input your UserName");
        IP.setText("IP Address");
        Port.setText("Port Number");
        bt_create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Pattern p_ip = Pattern.compile(ip_regex);
                Pattern p_number = Pattern.compile(number);
                Matcher m_ip = p_ip.matcher(textField_ip.getText());
                Matcher m_number = p_number.matcher(textField_port.getText());
                if(m_ip.find() && m_number.find() && Integer.parseInt(textField_port.getText())<65536 && Integer.parseInt(textField_port.getText())>1024) {
                    String[] s = null;
                    SWBClient_GUI.main(s);
                    frame.dispose();
                    // Need judgement and database
                    loginName = textField1.getText();
                }
                else{
                    textField_port.setText("Wrong Format");
                    textField_ip.setText("Wrong Format");
                }
            }
        });
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    String[] s = null;
                    SWBClient_GUI.main(s);
                    frame.dispose();
                    // Need judgement and database
                    loginName = textField1.getText();
                }
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("LoginWindow");
        frame.setContentPane(new LoginWindow().mainWindow);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

}
