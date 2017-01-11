/**
 * 定义：提供一个方法访问一个容器对象中各个元素，而又不暴露该对象内部的细节
 * 角色：
 * Iterator：抽象迭代器
 * ConcreteIterator 具体迭代器
 * Aggregate 抽象容器
 * ConcreteAggregate 具体迭代器
 * 使用：一般不需要自己写迭代器
 * Created by hyz84 on 17/1/10.
 */

public class IteratorPattern {
    public String doSomething(){
        Aggreate aggreate = new ConcreteAggregate();
        aggreate.add("abc");
        aggreate.add("xyz");
        Iterator iterator  = aggreate.iterator();
        String result="";
        while (iterator.hasNext()){
            result+=iterator.next();
        }
        return result;
    }
}


public interface Iterator {
    //遍历下一个元素
    public Object next();
    //判断是否已经遍历到尾部
    public boolean hasNext();
    //删除当前指向的元素
    public boolean remove();
}

public class ConcreteIterator implements Iterator {

    private Vector mVector = new Vector();
    //定义当前游标
    public int cursor=0;

    public ConcreteIterator(Vector _vector){
        mVector=_vector;
    }

    @Override
    public Object next() {
        Object result = null;
        if(hasNext()){
            result=mVector.get(cursor++);
        }

        return result;
    }

    @Override
    public boolean hasNext() {
        if(cursor==mVector.size()){
            return false;
        }
        return true;
    }

    @Override
    public boolean remove() {
        mVector.remove(cursor);
        return true;
    }
}

public interface Aggreate {
    //添加元素
    public void add(Object object);
    //减少元素
    public void remove(Object object);
    //由迭代器来遍历所有元素
    public Iterator iterator();
}


public class ConcreteAggregate implements Aggreate {
    //容纳对象的容器
    private Vector mVector = new Vector();
    @Override
    public void add(Object object) {
        mVector.add(object);
    }

    @Override
    public void remove(Object object) {
        mVector.remove(object);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(mVector);
    }
}