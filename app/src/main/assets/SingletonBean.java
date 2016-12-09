/**
 * Created by hyz84 on 16/12/9.
 * 懒汉式
 */
public class SingletonBean1 {
    private String name;
    private static SingletonBean1 bean=null;
    private SingletonBean1(){}
    public static SingletonBean1 getInstance(){
        if(bean ==null){
            bean = new SingletonBean1();
        }
        return  bean;
    }
}

/**
 * Created by hyz84 on 16/12/9.
 * 饿汉式
 */
public class SingletonBean2 {
    private String name;
    private static SingletonBean2 bean=new SingletonBean2();
    private SingletonBean2(){}
    public static SingletonBean2 getInstance(){
        return  bean;
    }
}
