import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zhiyuan on 9/2/17.
 */
public class TextArea1 implements ActionListener{
    JTextArea textArea;
    public static void main(String[] args) {
        TextArea1 textArea1 = new TextArea1();
        textArea1.go();

    }
    public void go(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        textArea = new JTextArea(10,20);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JButton button = new JButton("click me");
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane);
        frame.getContentPane().add(BorderLayout.EAST,panel);
        frame.getContentPane().add(BorderLayout.SOUTH,button);

        button.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.append("clicked button\n");
    }
}
