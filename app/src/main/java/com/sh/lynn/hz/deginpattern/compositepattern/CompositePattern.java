package com.sh.lynn.hz.deginpattern.compositepattern;

import static com.sh.lynn.hz.deginpattern.Utils.tempBuffer;

/**
 * 定义：将对象组合成树形结构以表示"部分—整体"的层次结构使得用户对单个对象和组合对象的使用具有一致性。
 * 角色：
 * Component 抽象构件角色 定义参加组合对象的共有方法和属性，可以定义一些默认行为或属性；
 * Leaf 叶子构件 叶子对象，其下再也没有其他的分支
 * Composite 树枝构件 树枝对象，它的作用是组合树枝节点和叶子节点形成一个树状结构
 * Created by hyz84 on 17/1/10.
 */

public class CompositePattern {
StringBuffer mBuffer = new StringBuffer();
    public void showExample() {

        Composite root = new Composite();
        mBuffer.append(root.doSomething() + "\n");
        Composite branch = new Composite();
        Leaf leaf = new Leaf();
        root.add(branch);
        branch.add(leaf);
        display(root);
        tempBuffer.append( mBuffer.toString());
    }
    private void display(Composite _root){
        for (Component component:_root.getChildren()){
            if(component instanceof  Leaf){
                mBuffer.append( component.doSomething()+"\n");
            }else{
                display((Composite)component);
            }
        }
    }
}
