package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Constant
 * @Date 2021/8/1 09:40
 * @Description 手写生产者和消费者,通过lock方式
 * 使用reentrantLock
 **/
public class ProducerAndConsumerByLock {
    private static int count = 0;
    private int maxNum = 3;
    ReentrantLock lock = new ReentrantLock();
    Condition producerCondition = lock.newCondition();
    Condition consumerCondition = lock.newCondition();


    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产完毕，目前总共有" + count);
//                    consumerCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费完毕，目前总共有" + count);
//                    producerCondition.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerAndConsumerByLock test = new ProducerAndConsumerByLock();

        new Thread(test.new Producer(), "生产者1").start();
        new Thread(test.new Producer(), "生产者2").start();

        new Thread(test.new Consumer(), "消费者1").start();
        new Thread(test.new Consumer(), "消费者2").start();
    }
}
