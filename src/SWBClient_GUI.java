import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Created by ES on 2017/9/14.
 */
public class SWBClient_GUI {
    private JPanel mainWindow;
    private JTabbedPane tabbedPane1;
    private JButton bt_new;
    private JButton bt_save;
    private JButton bt_open;
    private JButton bt_saveas;
    private JButton bt_pen;
    private JComboBox cb_degree;
    private JButton bt_color;
    private JButton bt_shape;
    private JPanel board;
    private JButton bt_enter;
    private JTextPane text_chat;
    private JTextPane text_input;
    private int startx;
    private int starty;
    private int x;
    private int y;
    private String state = "pen";
//        private JColorChooser chooser;

    public SWBClient_GUI() {

        Graphics g;
        g = board.getGraphics();

        bt_color.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Color color = new Color(255,255,255);
                JColorChooser.showDialog(null,"choose color" ,color);
            }
        });
//        mainWindow.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                super.mouseMoved(e);
//
//
//                Graphics g;
//                g=mainWindow.getGraphics();
//                g.setColor(Color.BLACK);
//                x = e.getX();
//                y = e.getY();
//                g.drawLine(startx,starty,x,y);
//                System.out.println(x+","+y);
//                startx = e.getX();
//                starty = e.getY();
//
//
//            }
//        });
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                startx = e.getX();
                starty = e.getY();
            }
        });
        board.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                g.setColor(Color.black);
                x = e.getX();
                y = e.getY();
                g.drawLine(startx,starty,x,y);
                System.out.println(x+","+y);
                startx = e.getX();
                starty = e.getY();
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("SWBClient_GUI");
        frame.setContentPane(new SWBClient_GUI().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
