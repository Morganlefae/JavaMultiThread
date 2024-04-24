package src.com.state;
//守护线程
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread  = new Thread(god);
        thread.setDaemon(true);//默认是false线程，正常线程都是用户线程
        thread.start();

        new Thread(you).start(); //用户线程启动。。。。


    }
}

class God implements Runnable{
    @Override
    public void run() {
        while (true) {
            System.out.println("God Bless You");
        }
    }
}
class You implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("开心");
        }
        System.out.println("-========= Good Bye World ===========");
    }
}