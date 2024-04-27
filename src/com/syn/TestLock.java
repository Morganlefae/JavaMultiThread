package src.com.syn;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }
}
class TestLock2 implements Runnable{
    int ticketNums = 10;
    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (ticketNums <= 0){
                    System.out.println("没票了，买不了");
                    break;
                } else{
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "买到了" + ticketNums--);
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}