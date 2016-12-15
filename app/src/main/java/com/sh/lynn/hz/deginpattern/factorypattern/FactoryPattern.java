package com.sh.lynn.hz.deginpattern.factorypattern;

/**
 * 工厂模式
 * Created by hyz84 on 16/12/12.
 */

public class FactoryPattern {
    //抽象产品类
    public abstract class Product {
        //公共方法
        public void method1() {
        }

        //产品抽象方法
        public abstract void method2();
    }

    // 具体产品1
    public class Product1 extends Product {

        @Override
        public void method2() {

        }
    }

    // 具体产品2
    public class Product2 extends Product {

        @Override
        public void method2() {

        }
    }

    //抽象工厂类
    public abstract class Creator {
        public abstract <T extends Product> T createProduct(Class<T> c);

    }

    //具体工厂
    public class ConcreteCreator extends Creator {

        @Override
        public <T extends Product> T createProduct(Class<T> c) {
            Product product = null;

            try {
                product = (Product) Class.forName(c.getName()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return (T) product;
        }
    }

    //场景类
    public class Client {
        Creator mCreator = new ConcreteCreator();

        Product mProduct1 = mCreator.createProduct(Product1.class);
        Product mProduct2 = mCreator.createProduct(Product2.class);
    }
    /**********************************************************************************/
    /**
     * 简单工厂类
     */
    public static class ProductFactory {
        public static <T extends Product> T createProduct(Class<T> c) {
            Product product = null;
            try {
                product = (Product) Class.forName(c.getName()).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return (T) product;
        }
    }

    //场景类
    public class Client2 {
        Product mProduct1 = ProductFactory.createProduct(Product1.class);
        Product mProduct2 = ProductFactory.createProduct(Product2.class);
    }
    /**************************************************************************/

    /**
     * 多工厂模式
     */
    public abstract class AbstactProductFactory {
        public abstract Product createProduct();
    }

    public class Product1Factory extends AbstactProductFactory {
        @Override
        public Product createProduct() {
            return new Product1();
        }
    }

    public class Product2Factory extends AbstactProductFactory {

        @Override
        public Product createProduct() {
            return new Product2();
        }
    }

    //场景类
    public class Client3 {
        Product mProduct1 = (new Product1Factory()).createProduct();
        Product mProduct2 = (new Product2Factory()).createProduct();
    }
    /*************************************************************************************************/

    /******************抽象工厂模式********************************/
    /**
     * 产品族A
     */
    public abstract class AbstractProductA {
        public void method1() {
        }

        public abstract void method2();
    }

    public class ProductA1 extends AbstractProductA {
        @Override
        public void method2() {
        }
    }

    public class ProductA2 extends AbstractProductA {
        @Override
        public void method2() {
        }
    }

    /**
     * 产品族B
     */
    public abstract class AbstractProductB {
        public void method1() {
        }

        public abstract void method2();
    }

    public class ProductB1 extends AbstractProductB {
        @Override
        public void method2() {
        }
    }

    public class ProductB2 extends AbstractProductB {
        @Override
        public void method2() {
        }
    }

    /**
     * 抽象工厂
     */
    public abstract class AbstractFactory{
        public abstract AbstractProductA createProductA();
        public abstract AbstractProductB createProductB();
    }

    /**
     * 生产1类产品工厂
     */
    public class Factory1 extends AbstractFactory{

        @Override
        public AbstractProductA createProductA() {
            return new ProductA1();
        }

        @Override
        public AbstractProductB createProductB() {
            return new ProductB1();
        }
    }
    /**
     * 生产2类产品工厂
     */
    public class Factory2 extends AbstractFactory{

        @Override
        public AbstractProductA createProductA() {
            return new ProductA2();
        }

        @Override
        public AbstractProductB createProductB() {
            return new ProductB2();
        }
    }

    //场景类
    public class Client4 {

        AbstractFactory mFactory1 = new Factory1();
        AbstractFactory mFactory2 = new Factory2();

        AbstractProductA a1 = mFactory1.createProductA();
        AbstractProductB b1 = mFactory1.createProductB();

        AbstractProductA a2 = mFactory2.createProductA();
        AbstractProductB b2 = mFactory2.createProductB();


    }
}
