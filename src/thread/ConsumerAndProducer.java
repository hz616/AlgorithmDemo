package thread;


import java.util.LinkedList;

public class ConsumerAndProducer {


    private final int size = 20;
    private LinkedList<Object> list = new LinkedList();


    public void produce() {
        synchronized (list) {
            while (list.size() >= size) {
                System.out.println("生产者: " + Thread.currentThread().getName() + " 仓库满了");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            System.out.println("生产者: " + Thread.currentThread().getName() + " 生产了衣服 现在仓库的数量为: " + list.size());
            list.notifyAll();
        }
    }

    public void consumer() {
        synchronized (list) {
            while (list.isEmpty()) {
                System.out.println("消费者: " + Thread.currentThread().getName() + " 发现没有库存了，需要唤醒生产者生产");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println("消费者: " + Thread.currentThread().getName() + " 消费了一件商品，现在商品还剩： " + list.size());
            list.notifyAll();
        }
    }


    static class Producer implements Runnable {

        private ConsumerAndProducer factory;

        public Producer(ConsumerAndProducer factory) {
            this.factory = factory;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    factory.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        private ConsumerAndProducer factory;

        public Consumer(ConsumerAndProducer factory) {
            this.factory = factory;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(120);
                    factory.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        ConsumerAndProducer consumerAndProducer = new ConsumerAndProducer();
        Thread p1 = new Thread(new Producer(consumerAndProducer), "生产者1");
        Thread p2 = new Thread(new Producer(consumerAndProducer), "生产者2");
        Thread p3 = new Thread(new Producer(consumerAndProducer), "生产者3");

        Thread c1 = new Thread(new Consumer(consumerAndProducer), "消费者1");
        Thread c2 = new Thread(new Consumer(consumerAndProducer), "消费者2");
        Thread c3 = new Thread(new Consumer(consumerAndProducer), "消费者3");

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }


}
