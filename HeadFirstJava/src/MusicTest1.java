import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

/**
 * Created by zhiyuan on 9/1/17.
 */
public class MusicTest1{
    JFrame frame;
    Sequencer player;
    MyDrawPanel panel;

    public void setGUI(){
        frame = new JFrame();
        panel = new MyDrawPanel();
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

    public void play(){
        try {
            setGUI();
            player = MidiSystem.getSequencer();
            player.open();
            int[] eventIwant = {127};



            player.addControllerEventListener(panel,eventIwant);



            Sequence seq = new Sequence(Sequence.PPQ,4);
            Track t = seq.createTrack();

            for (int i=5;i<61;i+=4){
                t.add(makeEvent(144,1,i,100,i));
                t.add(makeEvent(176,1,127,0,i));
                t.add(makeEvent(128,1,i,100,i+2));
            }


            player.setSequence(seq);
            player.setTempoInBPM(220);
            player.start();


        }catch (MidiUnavailableException e){
            System.out.println("Bummer");
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }//close



    public static void main(String[] args) throws MidiUnavailableException {
        MusicTest1 musicTest1 = new MusicTest1();
        musicTest1.play();

    }

    public static MidiEvent makeEvent(int comd,int chan,int one,int two, int tick ){
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd,chan,one,two);
            event = new MidiEvent(a,tick);
        }catch (Exception e){
            e.printStackTrace();
        }
        return event;
    }



    class MyDrawPanel extends JPanel implements ControllerEventListener{
        boolean msg = false;
        @Override
        public void controlChange(ShortMessage event) {
            System.out.println("lb");
            msg = true;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (msg){
                Graphics2D g2d = (Graphics2D) g;

                int r = (int)(Math.random()*255);
                int gr = (int)(Math.random()*255);
                int b = (int)(Math.random()*255);

                g.setColor(new Color(r,gr,b));

                int width = (int) (Math.random()*120+10);
                int height = (int) (Math.random()*120+10);
                int x = (int) (Math.random()*40+10);
                int y = (int) (Math.random()*40+10);
                g.fillRect(x,y,width,height);
                msg = false;


            }
        }
    }
}
