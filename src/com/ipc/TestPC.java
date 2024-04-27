package src.com.ipc;
//测试：生产者消费者模型----》利用缓冲区解决
public class TestPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();
        new Producer(synContainer).start();
        new Consumer(synContainer).start();
    }
}
class Producer extends Thread {
    SynContainer container;
    public Producer(SynContainer container){
        this.container = container;
    }
    //生产

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("生产了" + i + "只鸡");
            container.push(new Chicken(i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer extends Thread {
    SynContainer container;
    public Consumer(SynContainer container){
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消耗了" + container.pop().id+ "只鸡");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Chicken{
    int id;

    public Chicken(int n) {
        this.id = n;
    }
}
class SynContainer {
    //需要一个容器大小
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    public synchronized void push(Chicken chicken){
        //如果容器满了就要等待消费者消费
        if (count == chickens.length){
            //生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //没有满就丢入产品
        chickens[count] = chicken;
        count++;
        //通知消费者
        this.notifyAll();
    }

    public synchronized Chicken pop(){
        //判断能否消费
        if (count == 0){
            //等待生产者生产
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //消费
        count--;
        Chicken chicken = chickens[count];
        //通知生产者
        this.notifyAll();

        return chicken;
    }
}