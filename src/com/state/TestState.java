package src.com.state;

public class TestState {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("////////");
        });
        //观察状态
        Thread.State state = thread.getState();
        System.out.println(state);
        //观察启动后
        thread.start();
        state = thread.getState();//run
        while (state != Thread.State.TERMINATED){
            try {
                Thread.sleep(100);
                state = thread.getState();
                System.out.println(state);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread.start();//死亡之后不能start

    }


}
