import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by zhiyuan on 9/3/17.
 */
public class QuizCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> quizCardArrayList;
    private JFrame frame;

    public static void main(String[] args) {
        QuizCardBuilder quizCardBuilder = new QuizCardBuilder();
        quizCardBuilder.go();
    }


    public void go(){
        //create gui
        frame = new JFrame("Quiz Card Builder");
        JPanel panel = new JPanel();
        Font bigFont = new Font("sanserif",Font.BOLD,24);
        question = new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane qscrollPane = new JScrollPane(question);
        qscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qscrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JScrollPane ascrollPane = new JScrollPane(answer);
        ascrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ascrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Next Card");

        quizCardArrayList = new ArrayList<>();

        JLabel qlabel = new JLabel("Question:");
        JLabel alabel = new JLabel("Answer:");

        panel.add(qlabel);
        panel.add(qscrollPane);
        panel.add(alabel);
        panel.add(ascrollPane);
        panel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setSize(500,600);
        frame.setVisible(true);




    }

    private class NextCardListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard quizCard = new QuizCard(question.getText(),answer.getText());
            quizCardArrayList.add(quizCard);
            clearCard();
        }
    }

    private class SaveMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            QuizCard quizCard = new QuizCard(question.getText(),answer.getText());
            quizCardArrayList.add(quizCard);

            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    private class NewMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            quizCardArrayList.clear();
            clearCard();
        }
    }

    public void saveFile(File file){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (QuizCard card:quizCardArrayList){
                bufferedWriter.write(card.getQuestion()+"\n");
                bufferedWriter.write(card.getAnswer()+"\n");
            }
            bufferedWriter.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clearCard(){
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }


}
