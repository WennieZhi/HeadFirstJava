import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by zhiyuan on 9/2/17.
 */
public class BeatBox {
    JFrame frame;
    JPanel mainPanel;
    ArrayList<JCheckBox> checkboxArrayList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;

    String[] instrumentNames = {"Bass Drum","Closed Hi-Hat","Open Hi-Hat","Acoustic Snare",
            "Crash Cymbal","Hand Clap","High Tom","Hi Bongo","Maracas","Whistle","Low conga","Cowbell",
            "Vibraslap","Low-mid Tom","High Agogo","Open Hi Conga"};
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

    public static void main(String[] args) {
        BeatBox bb = new BeatBox();
        bb.buildGUI();

    }

    public void buildGUI(){
        frame = new JFrame("Cyber Beatbox");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel backgound = new JPanel(layout);
        backgound.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        checkboxArrayList = new ArrayList<>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        start.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton TempoUp = new JButton("Tempo Up");
        start.addActionListener(new MyTempoUpListener());
        buttonBox.add(TempoUp);

        JButton TempoDown = new JButton("Tempo Down");
        start.addActionListener(new MyTempoDownListener());
        buttonBox.add(TempoDown);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (int i=0;i<16;i++){
            nameBox.add(new Label(instrumentNames[i]));
        }

        backgound.add(BorderLayout.EAST,buttonBox);
        backgound.add(BorderLayout.WEST,nameBox);

        frame.getContentPane().add(backgound);

        GridLayout grid = new GridLayout(16,16);
        grid.setHgap(2);
        grid.setVgap(1);
        mainPanel = new JPanel(grid);
        backgound.add(BorderLayout.CENTER,mainPanel);

        for(int i=0;i<256;i++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkboxArrayList.add(c);
            mainPanel.add(c);
        }

        setUpMidi();
        frame.setBounds(50,50,300,300);
        frame.pack();
        frame.setVisible(true);
    }

    public void setUpMidi(){
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);


        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void buildTrackAndStart(){
        int[] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i=0;i<16;i++){
            trackList = new int[16];
            int key = instruments[i];
            for (int j=0;j<16;j++){
                JCheckBox jc = (JCheckBox)checkboxArrayList.get(j+16*i);
                if (jc.isSelected()==true){
                    trackList[j]=key;
                }else {
                    trackList[j]=0;
                }
            }
            makeTracks(trackList);
            track.add(makeEvent(176,1,127,0,16));
        }

        track.add(makeEvent(192,9,1,0,15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick ){
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

    public void makeTracks(int[] list){
        for (int i=0;i<16;i++){
            int key = list[i];
            if (key!=0){
                track.add(makeEvent(144,9,key,100,i));
                track.add(makeEvent(128,9,key,100,i+1));
            }
        }
    }



    class MyStartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            buildTrackAndStart();

        }
    }
    class MyStopListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("stop");
            sequencer.stop();

        }
    }
    class MyTempoUpListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor*1.03));

        }
    }

    class MyTempoDownListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float)(tempoFactor*0.97));
        }
    }

}
