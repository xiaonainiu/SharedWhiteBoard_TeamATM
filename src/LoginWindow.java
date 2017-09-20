import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.json.*;

public class LoginWindow {
    private JTextField textField1;
    private JButton button1;
    private JLabel label1;
    private JPanel mainWindow;
    static boolean flag = false;
    static JFrame frame;
    static String loginName;

    public LoginWindow() {
        button1.setText("Login");
        textField1.setText("player");
        label1.setText("Please input your UserName");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] s = null;
                SWBClient_GUI.main(s);
                frame.dispose();
                // Need judgement and database
                loginName = textField1.getText();
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
