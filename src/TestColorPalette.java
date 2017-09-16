/**
 * Created by ES on 2017/9/16.
 */
import java.awt.*;
import java.awt.event.*;
public class TestColorPalette implements AdjustmentListener{
    private int r=255, g=255, b=255;
    private TextField tfr;
    private TextField tfg;
    private TextField tfb;
    private Scrollbar sbr;
    private Scrollbar sbg;
    private Scrollbar sbb;
    private Panel display;

    public static void main( String args[]) {
        new TestColorPalette().init();
    }

    public void init(){
        Frame frame = new Frame("调色板");
        display = new Panel();
        display.setBackground(Color.WHITE);

        Panel pcolor = new Panel();
        pcolor.setLayout(new BorderLayout());
        Panel pw = new Panel();
        pw.setLayout(new GridLayout(3,2,0,0));
        tfr = new TextField("255");
        tfg = new TextField("255");
        tfb = new TextField("255");
        tfr.setEditable(false);
        tfg.setEditable(false);
        tfb.setEditable(false);
        sbr = new Scrollbar(Scrollbar.HORIZONTAL,255,0,0,255);
        sbg = new Scrollbar(Scrollbar.HORIZONTAL,255,0,0,255);
        sbb = new Scrollbar(Scrollbar.HORIZONTAL,255,0,0,255);
        sbr.setName("SBR");
        sbg.setName("SBG");
        sbb.setName("SBB");
        sbr.setBackground(Color.RED);
        sbg.setBackground(Color.GREEN);
        sbb.setBackground(Color.BLUE);
        sbr.addAdjustmentListener(this);
        sbg.addAdjustmentListener(this);
        sbb.addAdjustmentListener(this);
        pw.add(new Label("红色:"));
        pw.add(tfr);
        pw.add(new Label("绿色:"));
        pw.add(tfg);
        pw.add(new Label("蓝色:"));
        pw.add(tfb);

        Panel pc = new Panel();
        pc.setLayout(new GridLayout(3,1,0,0));
        pc.add(sbr);
        pc.add(sbg);
        pc.add(sbb);
        pcolor.add(pw,"West");
        pcolor.add(pc,"Center");
        frame.add(display,"Center");
        frame.add(pcolor,"South");

        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        frame.setSize(250,180);
        frame.setLocation(450,200);
        frame.setVisible( true);
    }

    public void adjustmentValueChanged(AdjustmentEvent e){
        String sb_name = ((Scrollbar)e.getSource()).getName();
        int value = e.getValue();
        if(sb_name.equals("SBR")){
            r = value;
            tfr.setText(Integer.toString(r));
        }else if(sb_name.equals("SBG")){
            g = value;
            tfg.setText(Integer.toString(g));
        }else if(sb_name.equals("SBB")){
            b = value;
            tfb.setText(Integer.toString(b));
        }
        this.refresh();
    }
    public void refresh(){
        Color c = new Color(r,g,b);
        display.setBackground(c);
    }
}