package src.com.syn;
//不安全的取钱
//两个人去取钱
public class UnsafeBank {
    public static void main(String[] args) {
        Account accout = new Account(1000, "银行账户");
        Drawing you = new Drawing(accout, 50, "你");
        Drawing girlfriend = new Drawing(accout, 100, "你对象");
        you.start();
        girlfriend.start();
    }
}
//账户
class Account {
    int money;//余额
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}
//银行
class Drawing extends Thread{
    Account account;
    int drawingMoney;
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name){
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    //synchronized 默认锁this
    @Override
    public void run() {
        synchronized (account) {
            //判断有没有钱
            if(account.money < drawingMoney){
                System.out.println(Thread.currentThread().getName() + "钱不够，取不到");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.money-= drawingMoney;
            nowMoney += drawingMoney;
            System.out.println(account.name + "余额为： "+ account.money);
            System.out.println(this.getName() + "手里的钱：" + nowMoney);
        }
    }
}