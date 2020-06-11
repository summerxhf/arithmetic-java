package thread.productConsumer;

import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: XINGHAIFANG
 * Date: 2020/6/4
 * Time: 13:13
 * 写一个固定容量同步容器, 拥有put和get方法,以及getCount方法,能够支持2个生产者线程以及10个消费者线程的阻塞调用;
 */
public class Container1<T> {
    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;//最多10个元素;
    private int count = 0;

    public synchronized void put(T t){
        //使用while 可能满了之后刚好有个线程get后变为不满,, 又要继续add
        while (lists.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        this.notifyAll();//通过消费者进行消费;
    }

    public synchronized T  get(){
        T t = null;
        while (lists.size()==0){
           try{
               this.wait();
           }catch (InterruptedException e){
               e.printStackTrace();
           }
        }
        t = lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者进行生产
        return t;
    }

    public static void main(String[] args) {
        Container1<String> c = new Container1();
        //启动消费者线程. 10个消费者线程;
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"consumer" +i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者线程;
        //两个生产者线程;
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put("线程名称: "+Thread.currentThread().getName() + " 当前资源: " + j);
                }
            },"product " + i).start();
        }
    }
}