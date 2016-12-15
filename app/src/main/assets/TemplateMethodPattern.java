/**
 * 模板方法模式： 定义一个操作中的算法的框架，而将一些步骤延迟到子类中。
 * 使得子类可以不改变一个算法结构即可重定义改算法的某些特定步骤。
 * Created by hyz84 on 16/12/12.
 */

public class TemplateMethodPattern {
    /**
     * 抽象模板类
     */
    public abstract class AbstactClass {
        protected abstract void doSomthing();
        protected abstract void doOtherThing();
        public void templateMethod() {
            this.doSomthing();
            this.doOtherThing();
        }
    }

    /**
     * 具体模板类
     */
    public class ConcreteClass1 extends AbstactClass{
        @Override
        protected void doSomthing() {
        }

        @Override
        protected void doOtherThing() {
        }
    }
    /**
     * 具体模板类
     */
    public class ConcreteClass2 extends AbstactClass{
        @Override
        protected void doSomthing() {
        }
        @Override
        protected void doOtherThing() {
        }
    }
    public class Client {
        AbstactClass mClass1 = new ConcreteClass1();
        AbstactClass mClass2 = new ConcreteClass2();
        mClass1.templateMethod();
        mClass2.templateMethod();

    }

    /*************************************************************************************/
    /************模板模式拓展****************/
    /**
     * 抽象模板类
     */
    public abstract class AbstactClassPro {
        protected abstract void doSomthing();
        protected abstract void doOtherThing();
        protected abstract boolean isDoS();
        public void templateMethod() {
            if(this.isDoS()){
                this.doSomthing();
            }
            this.doOtherThing();
        }
    }

    /**
     * 具体模板类
     */
    public class ConcreteClassPro1 extends AbstactClassPro{
        @Override
        protected void doSomthing() {
        }

        @Override
        protected void doOtherThing() {
        }

        @Override
        protected boolean isDoS() {
            return false;
        }
    }
    /**
     * 具体模板类
     */
    public class ConcreteClassPro2 extends AbstactClassPro{
        @Override
        protected void doSomthing() {
        }
        @Override
        protected void doOtherThing() {
        }

        @Override
        protected boolean isDoS() {
            return true;
        }
    }

    public class Client1 {
        AbstactClassPro mClass1 = new ConcreteClassPro1();
        AbstactClassPro mClass2 = new ConcreteClassPro2();
        mClass1.templateMethod();
        mClass2.templateMethod();

    }
}
