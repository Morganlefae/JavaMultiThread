package src.com.syn;
//死锁，多个线程互相抱着对方的资源然后形成僵持
public class DeadLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "First one");
        Makeup g2 = new Makeup(1, "Second one");
        g1.start();
        g2.start();
    }
}


class LipStick{

}

class Mirror{

}

class Makeup extends Thread{

    static LipStick lipStick = new LipStick();
    static Mirror mirror = new Mirror();

    int choice;
    String name;
    Makeup (int choice, String name){
        this.choice = choice;
        this.name = name;
    }
    @Override
    public void run() {
        //
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeup() throws InterruptedException {
        if (choice == 0){
            synchronized (lipStick){
                System.out.println(this.name + "获得口红的锁");
                Thread.sleep(1000);

            }
            synchronized (mirror){
                System.out.println(this.name + "获得镜子的锁");
            }
        } else {
            synchronized (mirror){
                System.out.println(this.name + "获得镜子的锁");
                Thread.sleep(2000);

            }
            synchronized (lipStick){
                System.out.println(this.name + "获得口红的锁");
            }
        }
    }
}