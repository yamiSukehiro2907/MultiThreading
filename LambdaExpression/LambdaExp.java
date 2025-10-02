package LambdaExpression;

public class LambdaExp {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> System.out.println("Hello"));
        t1.start();


        // Functional Interface
        // If an interface have only abstract function then it can be created with the help of lambda expression
    }
}
