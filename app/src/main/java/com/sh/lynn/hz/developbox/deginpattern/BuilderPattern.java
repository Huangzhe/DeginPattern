package com.sh.lynn.hz.developbox.deginpattern;

/**
 * 建造者模式(Build Pattern):将一个复杂对象的构建与它的表示分离，
 * 使得同样的构建过程可以创建不同的表示。
 * 建造者有以下几个角色：
 * Product 产品类
 * Builder抽象建造者 规范产品的组建，一般是由其子类实现
 * ConcreteBuilder具体建造者 实现抽象类定义所有方法，并且返回一个组建好的对象。
 * Director导演类 负责安排已有模块的顺序，然后告诉Builder开始建造
 * 使用场景：
 * 1.相同的方法，不同的执行顺序，产生不同的事件结果时，可以采用建造者模式；
 * 2.多个部件或零件，都可以装配到同一个对象中，但产生的结果又不同；
 * 3.产品类非常复杂，或者产品类中的调用顺序不同产生了不同的效能；
 * 4.在对象创建过程中会使用到系统中的一些其他对象，这些对象在产品对象的创建过程中不易得到。
 * 与工厂模式区别：
 * 建造者模式最主要功能是基本方法的调用顺序安排，也就是这些基本方法已经实现了，通俗的说就是零件的装配，
 * 顺序不同产生的对象也不同；
 * 工厂模式则重点是创建，创建零件是它的主要职责，组装顺序则不是它关心的。
 * Created by hyz84 on 16/12/14.
 */

public class BuilderPattern {
    /**
     * 产品类
     */
    public class Product{
        public void doSomething(){
        }
    }

    /**
     * 抽象建造者
     */
    public abstract class Builder{
        public abstract void setPart();
        public abstract Product buildProduct();

    }

    /**
     * 具体建造者
     */
    public class ConcreteProduct extends  Builder{
        private Product product = new Product();
        @Override
        public void setPart() {
            //产品类的内部逻辑
        }

        @Override
        public Product buildProduct() {
            return product;
        }
    }

    /**
     * 导演类
     */
    public class Director{
        private Builder mBuilder = new ConcreteProduct();
        public Product getAProduct(){
            mBuilder.setPart();
            //设置不同的零件，产生不同的产品
            return mBuilder.buildProduct();
        }
    }
}
