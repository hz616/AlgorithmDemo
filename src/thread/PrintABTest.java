package thread;

public class PrintABTest {

    //这是一个flag ，记录上一次是否打印的是字符A
    private volatile boolean flag = false;


    private synchronized void printA() {
        try {
            while (flag) {
                wait();
            }

            System.out.println("print A");
            flag = true;
            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private synchronized void printB() {
        try {
            while (!flag) {
                wait();
            }
            System.out.println("print B");
            flag = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        PrintABTest printABTest = new PrintABTest();
        for (int i = 0; i < 300; i++) {
            new Thread(printABTest::printA).start();
            new Thread(printABTest::printB).start();
        }
    }


}
