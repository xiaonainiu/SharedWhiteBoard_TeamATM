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
    private JButton bt_pencil;
    private JComboBox cb_pencil;
    private JButton bt_color;
    private JButton bt_shape;
    private JPanel board;
    private JTextField text_info;
    private JButton button1;
    private JTextArea textArea1;
    private JTextArea textArea2;
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
    private int startx;
    private int starty;
    private int x;
    private int y;
    Graphics g;
    private Color color = Color.black;
    private Color eraser_color = Color.white;
    private String state = "pencil";

    public SWBClient_GUI() {
//        set color
        cl000.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0,0,0);
            }
        });
        cl111.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255,255,255);
            }
        });
        cl666.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(102,102,102);
            }
        });
        clccc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(204,204,204);
            }
        });
        cl100.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255,0,0);
            }
        });
        cl150.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255,128,0);
            }
        });
        cl110.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255,255,0);
            }
        });
        cl510.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(128,255,0);
            }
        });
        cl010.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0,255,0);
            }
        });
        cl015.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0,255,128);
            }
        });
        cl011.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0,255,255);
            }
        });
        cl051.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0,128,255);
            }
        });
        cl001.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0,0,255);
            }
        });
        cl501.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(128,0,255);
            }
        });
        cl101.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255,0,255);
            }
        });
        cl105.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255,0,128);
            }
        });



//click color bt
        bt_color.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                Color color = new Color(255,255,255);
                color = JColorChooser.showDialog(null,"choose color" ,color);
            }
        });
//        update position information
        board.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                x = e.getX();
                y = e.getY();
                text_info.setText("Position: ("+x+","+y+")");
                System.out.println(x+","+y);
            }
        });
//        pen drawing
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
                if (state == "pencil"){
                    g = board.getGraphics();
                    g.setColor(color);
                    x = e.getX();
                    y = e.getY();
                    g.drawLine(startx,starty,x,y);
                    text_info.setText("Position: ("+x+","+y+")");
                    System.out.println(x+","+y);
                    startx = e.getX();
                    starty = e.getY();
                }else if(state == "eraser"){
                    g = board.getGraphics();
                    g.setColor(eraser_color);
                    x = e.getX();
                    y = e.getY();
                    g.drawLine(startx,starty,x,y);
                    text_info.setText("Position: ("+x+","+y+")");
                    System.out.println(x+","+y);
                    startx = e.getX();
                    starty = e.getY();
                }
            }
        });

        bt_pencil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state = "pencil";
                System.out.println("state pencil");
            }
        });
        bt_eraser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state = "eraser";
                System.out.println("state eraser");
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
