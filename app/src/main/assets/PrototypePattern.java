
import java.util.ArrayList;

/**
 * 原型模式（Prototype Pattern）:用原型实例指定创建对象的种类，并且通过拷贝这个原型创建新的对象。
 * 其核心是一个 clone方法，通过该方法进行对象拷贝，java提供一个Cloneable接口来标识这个对象可拷贝。
 * 优点：
 * 1.性能优良，原型模式是在内存二进制流中拷贝，要比直接new一个对象性能好很多；
 * 2.逃避构造函数的约束，直接在内存中拷贝，构造函数是不回执行的
 * 注意：使用原型模式时，引用的成员变量必须满足两个条件才不会拷贝：
 * 1.类的成员变量，而不是方法内的变量；
 * 2.必须是一个可变的引用对象，而不是一个原始类型或不可变对象。
 * 要使用clone方法，类的成员变量上不要增加final关键字
 * Created by hyz84 on 16/12/19.
 */

public class PrototypePattern {
    /**
     * 基本用法
     */
    public class PrototypeClass implements Cloneable{
        @Override
        public PrototypeClass clone() throws CloneNotSupportedException {
            PrototypeClass prototypeClass =null;
            try{
                prototypeClass =(PrototypeClass)  super.clone();
            }catch (Exception e){
                e.printStackTrace();
            }
            return prototypeClass;
        }
    }
/****************浅拷贝**********************/
    /**
     * 浅拷贝
     */
    public class PrototypeDemo implements Cloneable {
        private ArrayList<String> mArrayList = new ArrayList<>();
        @Override
        public com.sh.lynn.hz.deginpattern.prototypepattern.PrototypeDemo clone() throws CloneNotSupportedException {
            com.sh.lynn.hz.deginpattern.prototypepattern.PrototypeDemo paper =null;
            try{
                paper =(com.sh.lynn.hz.deginpattern.prototypepattern.PrototypeDemo)  super.clone();
            }catch (Exception e){
                e.printStackTrace();
            }
            return paper;
        }
        public void setValue(String text){
            mArrayList.add(text);
        }
        public ArrayList<String> getValue(){
            return   mArrayList;
        }
    }
    /**
     *
     * @return
     */
    public String getCloneText (){
        PrototypeDemo prototypeDemo = new PrototypeDemo();
        prototypeDemo.setValue("Hello!");
        try {
            PrototypeDemo prototypeDemoClone = prototypeDemo.clone();
            prototypeDemoClone.setValue("World!");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        StringBuffer stringBuffer = new StringBuffer();

        ArrayList<String>  list= prototypeDemo.getValue();
        for (String text:list){
            stringBuffer.append(text+"  ");
        }
        return stringBuffer.toString();
    }
/****************深拷贝**********************/
public class PrototypeDemoDeep implements Cloneable {

    private ArrayList<String> mArrayList = new ArrayList<>();
    @Override
    public com.sh.lynn.hz.deginpattern.prototypepattern.PrototypeDemoDeep clone() throws CloneNotSupportedException {
        com.sh.lynn.hz.deginpattern.prototypepattern.PrototypeDemoDeep paper =null;
        try{
            paper =(com.sh.lynn.hz.deginpattern.prototypepattern.PrototypeDemoDeep)  super.clone();
            paper.mArrayList =(ArrayList<String>)this.mArrayList.clone();
        }catch (Exception e){
            e.printStackTrace();
        }
        return paper;
    }
    public void setValue(String text){
        mArrayList.add(text);
    }
    public ArrayList<String> getValue(){
        return   mArrayList;
    }
}
    /**
     * 深拷贝
     * @return
     */
    public String getCloneTextDeep (){
        PrototypeDemoDeep prototypeDemo = new PrototypeDemoDeep();
        prototypeDemo.setValue("Hello!");
        try {
            PrototypeDemoDeep prototypeDemoClone = prototypeDemo.clone();
            prototypeDemoClone.setValue("World!");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        StringBuffer stringBuffer = new StringBuffer();

        ArrayList<String>  list= prototypeDemo.getValue();
        for (String text:list){
            stringBuffer.append(text+"  ");
        }
        return stringBuffer.toString();
    }
}
