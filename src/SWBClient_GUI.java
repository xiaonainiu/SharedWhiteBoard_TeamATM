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
    private JTextPane textPane1; //chatwindow
    private JButton bt_line;
    private JButton bt_rect;
    private JButton bt_circle;
    private JButton bt_oval;
    private JButton bt_polygon;
    private JComboBox cb_shape;
    private JButton bt_text;
    private JTextField textField1;
    private JPanel panel;
    private JCheckBox check_fill;
    private JButton bt_square;
    private JCheckBox checkBox1;
    private int startx;
    private int starty;
    private int x;
    private int y;
    Graphics g;
    private Color color = Color.black;
    private Color eraser_color = Color.white;
    private String state = "pencil";
    LoginWindow logname = new LoginWindow();
    LinkedList<Shape> shapeList = new LinkedList<Shape>();
    Shape shape;
    int isFill = 2;
    int tempx1, tempy1, tempx2, tempy2;
    int i = 2;


    public SWBClient_GUI() {
//        set color
        cl000.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 0, 0);
            }
        });
        cl111.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 255, 255);
            }
        });
        cl666.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(102, 102, 102);
            }
        });
        clccc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(204, 204, 204);
            }
        });
        cl100.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 0, 0);
            }
        });
        cl150.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 128, 0);
            }
        });
        cl110.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 255, 0);
            }
        });
        cl510.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(128, 255, 0);
            }
        });
        cl010.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 255, 0);
            }
        });
        cl015.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 255, 128);
            }
        });
        cl011.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 255, 255);
            }
        });
        cl051.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 128, 255);
            }
        });
        cl001.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(0, 0, 255);
            }
        });
        cl501.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(128, 0, 255);
            }
        });
        cl101.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 0, 255);
            }
        });
        cl105.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                color = new Color(255, 0, 128);
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
                            docs.insertString(docs.getLength(), logname.loginName.trim() + ":", attrset_selfusername);
                            docs.insertString(docs.getLength(), "  ", null);
                            docs.insertString(docs.getLength(), textArea1.getText().trim() + "\n", attrset);
                            HashMap<String, String> map = new HashMap<String, String>();
                            map.put("State", "ChatWindow");
                            map.put("UserName", logname.loginName.trim());
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
                        docs.insertString(docs.getLength(), logname.loginName.trim() + ":", attrset_selfusername);
                        docs.insertString(docs.getLength(), "  ", null);
                        docs.insertString(docs.getLength(), textArea1.getText().trim() + "\n", setWord);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("State", "ChatWindow");
                        map.put("UserName", logname.loginName.trim());
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
        });
        /**
         * This part is written by LZH
         * Chat Window
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
                    shape = null;
                }
            }
        });
        board.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
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
                    if (cb_shape.getSelectedItem() == "thin")
                        shape = new Line(startx, starty, x, y, g.getColor(), 1);
                    else if (cb_shape.getSelectedItem() == "normal")
                        shape = new Line(startx, starty, x, y, g.getColor(), 2);
                    else
                        shape = new Line(startx, starty, x, y, g.getColor(), 5);
                    repaint();
                    text_info.setText("Position: (" + x + "," + y + ")");
                } else if (state == "pencil") {
                    g = board.getGraphics();
                    g.setColor(color);
                    x = e.getX();
                    y = e.getY();
                    if (cb_pencil.getSelectedItem() == "thin")
                        shape = new Line(startx, starty, x, y, g.getColor(), 1);
                    else if (cb_pencil.getSelectedItem() == "normal")
                        shape = new Line(startx, starty, x, y, g.getColor(), 2);
                    else
                        shape = new Line(startx, starty, x, y, g.getColor(), 5);
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
                    if (cb_eraser.getSelectedItem() == "small")
                        shape = new Line(startx, starty, x, y, g.getColor(), 1);
                    else if (cb_eraser.getSelectedItem() == "medium")
                        shape = new Line(startx, starty, x, y, g.getColor(), 5);
                    else
                        shape = new Line(startx, starty, x, y, g.getColor(), 10);
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
                    if (cb_shape.getSelectedItem() == "thin")
                        shape = new Rectangle(startx, starty, x, y, g.getColor(), 1, isFill);
                    else if (cb_shape.getSelectedItem() == "normal")
                        shape = new Rectangle(startx, starty, x, y, g.getColor(), 2, isFill);
                    else
                        shape = new Rectangle(startx, starty, x, y, g.getColor(), 5, isFill);
                    repaint();
                    text_info.setText("Position: (" + x + "," + y + ")");
                } else if (state == "circle" && i == 2) {
                    board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    g = board.getGraphics();
                    g.setColor(color);
                    x = e.getX();
                    y = e.getY();
                    if (cb_shape.getSelectedItem() == "thin")
                        shape = new Circle(startx, starty, x, y, g.getColor(), 1, isFill);
                    else if (cb_shape.getSelectedItem() == "normal")
                        shape = new Circle(startx, starty, x, y, g.getColor(), 2, isFill);
                    else
                        shape = new Circle(startx, starty, x, y, g.getColor(), 5, isFill);
                    repaint();
                    text_info.setText("Position: (" + x + "," + y + ")");
                } else if (state == "oval" && i == 2) {
                    board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    g = board.getGraphics();
                    g.setColor(color);
                    x = e.getX();
                    y = e.getY();
                    if (cb_shape.getSelectedItem() == "thin")
                        shape = new Oval(startx, starty, x, y, g.getColor(), 1, isFill);
                    else if (cb_shape.getSelectedItem() == "normal")
                        shape = new Oval(startx, starty, x, y, g.getColor(), 2, isFill);
                    else
                        shape = new Oval(startx, starty, x, y, g.getColor(), 5, isFill);
                    repaint();
                    text_info.setText("Position: (" + x + "," + y + ")");
                } else if (state == "polygon" && i == 2) {
                    board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    g = board.getGraphics();
                    g.setColor(color);
                    x = e.getX();
                    y = e.getY();
                    if (cb_shape.getSelectedItem() == "thin")
                        shape = new Polygon(startx, starty, x, y, g.getColor(), 1, isFill);
                    else if (cb_shape.getSelectedItem() == "normal")
                        shape = new Polygon(startx, starty, x, y, g.getColor(), 2, isFill);
                    else
                        shape = new Polygon(startx, starty, x, y, g.getColor(), 5, isFill);
                    repaint();
                    text_info.setText("Position: (" + x + "," + y + ")");
                } else if (state == "square" && i == 2) {
                    board.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                    g = board.getGraphics();
                    g.setColor(color);
                    x = e.getX();
                    y = e.getY();
                    if (cb_shape.getSelectedItem() == "thin")
                        shape = new Square(startx, starty, x, y, g.getColor(), 1, isFill);
                    else if (cb_shape.getSelectedItem() == "normal")
                        shape = new Square(startx, starty, x, y, g.getColor(), 2, isFill);
                    else
                        shape = new Square(startx, starty, x, y, g.getColor(), 5, isFill);
                    repaint();
                    text_info.setText("Position: (" + x + "," + y + ")");
                }
            }
        });

        bt_pencil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state = "pencil";
                board.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        bt_eraser.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                state = "eraser";
                java.awt.Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("lzh/hippo.png").getImage(), new Point(10, 20), "eraser");
                board.setCursor(cursor);
            }
        });
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                g = board.getGraphics();
                if (shape != null) shapeList.add(shape);
                //shape = null;
                repaint();
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
                    if (cb_shape.getSelectedItem() == "thin")
                        shape = new Text(startx, starty, g.getColor(), 1, textField1.getText());
                    else if (cb_shape.getSelectedItem() == "normal")
                        shape = new Text(startx, starty, g.getColor(), 2, textField1.getText());
                    else
                        shape = new Text(startx, starty, g.getColor(), 5, textField1.getText());
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
        g2d.fillRect(board.getX() - 1, board.getY() - 115, board.getWidth(), board.getHeight());
//        for (Shape s : shapeList) s.draw(g2d);
//        if (shape != null) {
//            shape.draw(g2d);
//        }
        for (Shape s : shapeList)
            s.draw(temp_g2d);
        if (shape != null) {
            shape.draw(temp_g2d);
        }
        g2d.drawImage(image, board.getLocation().x - 1, board.getLocation().y - 115, board.getWidth(), board.getHeight(), null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SWBClient_GUI");
        frame.setContentPane(new SWBClient_GUI().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false); // edit by LZH
        frame.setVisible(true);
    }

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


