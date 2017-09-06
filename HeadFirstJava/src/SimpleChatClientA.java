import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhiyuan on 9/4/17.
 */
public class SimpleChatClientA {
    JTextField outgoing;
    JTextArea incoming;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public static void main(String[] args) {
        SimpleChatClientA clientA = new SimpleChatClientA();
        clientA.go();
    }

    public void go(){
        //注册按钮的监听者
        //调用setUpNetworking()
        JFrame frame = new JFrame("ClientA");
        JPanel panel = new JPanel();
        outgoing = new JTextField(20);
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(incoming);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton send = new JButton("send");
        send.addActionListener(new SendButtonListener());
        panel.add(scrollPane);
        panel.add(outgoing);
        panel.add(send);
        frame.getContentPane().add(BorderLayout.CENTER,panel);

        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.setSize(400,500);
        frame.setVisible(true);
    }
    public void setUpNetworking(){
        //建立socket PrintWriter
        try {
            socket = new Socket("127.0.0.1",5000);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("netowrk is fine");
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public class SendButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //取得文字 传送到服务器上
            try {
                String text = outgoing.getText();
                writer.println(text);
                writer.flush();
                System.out.println(text);
            }catch (Exception ex){
                ex.printStackTrace();
            }

            outgoing.setText("");
            outgoing.requestFocus();

        }
    }

    public class IncomingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try {
                if ((message=reader.readLine())!=null){
                    System.out.println(message);
                    incoming.append(message+"\n");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

