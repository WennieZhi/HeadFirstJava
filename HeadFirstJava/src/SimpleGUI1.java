import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zhiyuan on 9/1/17.
 */
public class SimpleGUI1 {
    JFrame frame;
//    JButton button1;
//    JButton button2;
//    Label label;
    int x = 70;
    int y = 70;

    public static void main(String[] args) {
        SimpleGUI1 gui1 = new SimpleGUI1();
        gui1.go();

    }

    public void go()  {
        frame = new JFrame();
        DrawPanel panel = new DrawPanel();
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setVisible(true);


//        button1 = new JButton("click me to draw oval");
//        button2 = new JButton("click me to change text");
//        label = new Label("change me");
//
//        button1.addActionListener(new Button1Listener());
//        button2.addActionListener(new Button2Listener());
//
//        frame.getContentPane().add(BorderLayout.SOUTH,button1);
//        frame.getContentPane().add(BorderLayout.EAST,button2);
//        frame.getContentPane().add(BorderLayout.WEST,label);



        for (int i=0;i<30;i++){
            x+=5;
            y+=5;
            //重新绘制画板
            panel.repaint();

            try {
                Thread.sleep(50);
            }catch (Exception ex){

            }


        }
    }

//    class Button1Listener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            MyDrawPanel panel = new MyDrawPanel();
//            frame.getContentPane().add(BorderLayout.CENTER,panel);
//            frame.repaint();
//            frame.setVisible(true);
//        }
//    }
//
//    class Button2Listener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            label.setText("changed");
//        }
//    }

    class DrawPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(),this.getHeight());

            g.setColor(Color.blue);
            g.fillOval(x,y,100,100);
        }
    }



}
