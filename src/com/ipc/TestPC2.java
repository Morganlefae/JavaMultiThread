package src.com.ipc;

public class TestPC2 {
    public static void main(String[] args) {
        show s = new show();
        new Player(s).start();
        new Watcher(s).start();
    }
}

class Player extends Thread{
    show s;
    public Player(show s){
        this.s = s;
    }
    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0){
                this.s.play("第一个节目");
            } else {
                this.s.play("第二个节目");
            }
        }
    }
}

class Watcher extends Thread{
    show s;
    public Watcher(show s){
        this.s = s;
    }
    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            this.s.watch();
        }
    }
}

class show{
    String voice;
    boolean signal = true;

    public synchronized void play(String voice){
        if (!signal){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了" + voice);

        this.notifyAll();
        this.voice = voice;
        this.signal = !this.signal;
    }

    public synchronized void watch(){
        if (signal){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了" + voice);
        this.notifyAll();
        this.signal = !this.signal;
    }
}