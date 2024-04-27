package src.com.syn;

import java.util.concurrent.CopyOnWriteArrayList;

public class TestJUC {
    public static void main(String[] args) {
        CopyOnWriteArrayList <String> list = new CopyOnWriteArrayList<String>();
        for (int i = 0; i < 10000; i++) {
            new Thread(
                    ()->{
                        list.add(Thread.currentThread().getName());
                    }
            ).start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
