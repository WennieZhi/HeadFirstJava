import javax.swing.*;
import java.awt.*;

/**
 * Created by zhiyuan on 9/2/17.
 */
public class MyDrawPanel extends JPanel {
    @Override
    //所有的绘图程序代码都在paintComponent里面，当你的panel所处的frame显示的时候，系统就会自动调用piantComponent方法，注意，你自己是不能直接调用这个方法的
    protected void paintComponent(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval(10,10,100,100);
    }
}




