package src.com.syn;
//不安全的买票
//线程不安全有负数
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        new Thread(buyTicket, "小明").start();
        new Thread(buyTicket, "我").start();
        new Thread(buyTicket, "黄牛").start();
    }
}
class BuyTicket implements Runnable{

    int ticketNums = 10;
    boolean flag = true;
    @Override
    public void run() {
        //买票
        while (flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void buy() throws InterruptedException {
        if (ticketNums <= 0){
            flag = false;
            return;
        }
        //模拟延时
        Thread.sleep(200);
        //买票
        System.out.println(Thread.currentThread().getName() + "get ticket " + ticketNums--);
    }
}