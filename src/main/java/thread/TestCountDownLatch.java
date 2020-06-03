package thread;

import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: XINGHAIFANG
 * Date: 2020/6/3
 * Time: 11:04
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
//        usingJoin();
        usingCountDownLatch();
    }
    private static  void usingCountDownLatch(){
        Thread[] threads =new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
               int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result +=j;
                    latch.countDown();
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
//        try{
//            latch.wait();
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        System.out.println(latch.getCount() +"end latch");
        System.out.println(1);
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) result += j;
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");
    }
}