/**
 * Created by zhiyuan on 9/4/17.
 */
public class ThreadTester {
    public static void main(String[] args) {
        MyRunnable threadJob = new MyRunnable();
        Thread thread = new Thread(threadJob);
        thread.start();
        System.out.println("back in main");
    }
}
