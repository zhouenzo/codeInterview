/**
 * @author enzo
 * 面试单例模式，要适应多线程，并明确锁的范围，做到最好的单例
 * 关键：单例是在整个运行过程中，只存在该类的实例仅有一个，因此外部不能随意使用构造器产生对象
 * 使用private 修饰该类的构造器，使外部无法访问构造器，但是要留出接口，将实例暴露出去
 * 于是需要有一个 public getInstance()方法将实例暴露给外部
 *
 */
public class Singleton {
    /**
     * 使用static修饰应用的原因：
     * 因为外部通过静态方法getInstance（）得到实例，这里静态方法不能访问非静态修饰的变量。
     * 所以这里必须要用static修饰
     * 使用volatile修饰的原因是防止指令重排导致的错误，
     * new一个对象会被jvm分为3步执行
     * 1、在内存中分配对象空间
     * 2、初始化singleton
     * 3、将singleton指向分配的内存地址
     * 如果发生指令重排，可能执行的顺序是1->3->2
     * 如果线程1执行了1->3,当线程2进行访问时，发现singleton已经有了，但是此时的singleton是空的。
     * 使用volatile可以保证对象的可见性，同时防止指令重排。
     *
     * 为什么getInstance（）中有双重判断singleton是否为null？
     * 因为可能在第二个判断是为了防止出现在有一个线程正在新建一个实例时另外一个线程通过了第一个判断，如果没有第
     * 二个判断，当第一个线程离开同步块时，第二个线程又会进入新建对象了。
     *
     * 还有一种是直接在变量声明的时候就实例化
     * private static Singleton singleton = new Singleton();
     * 这样在类加载完成是就会完成初始化，而且对象只属于类，只会有一个实例存在。
     * 这种在要求之前就已经准备好资源的模式成为饿汉模式。
     */
    private static volatile Singleton singleton;
    private Singleton(){

    }

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
