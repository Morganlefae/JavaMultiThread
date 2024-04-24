package src.com.lambda;

public class TestLambda2 {

    public static void main(String[] args) {
        Ilove ilove = new Love();
        ilove.love(2);
        Ilove love2 = new Love(){
            @Override
            public void love(int a){
                System.out.println("I love you ---> " + a);
            }
        };
        love2.love(3);
        //简化1： 参数类型
        //简化2： 花括号
        //简化3： 括号
        Ilove ilove3 = a -> System.out.println("I love you ---> " + a);
        ilove3.love(4);
    }
}
interface Ilove{
    void love(int a);
}
class Love implements Ilove{
    @Override
    public void love(int a){
        System.out.println("I love you ---> " + a);
    }
}