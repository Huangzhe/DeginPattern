package com.sh.lynn.hz.developbox.deginpattern.compositepattern;

import java.util.ArrayList;

/**
 * 树枝构件
 * Created by hyz84 on 17/1/10.
 */
public class Composite extends Component {
    private ArrayList<Component> mComponentArrayList = new ArrayList<>();

    /**
     * 增加一个叶子构件或者树枝构件
     *  @param _component
     */
    public void add(Component _component) {
        mComponentArrayList.add(_component);
    }

    public void remove(Component _component){
        mComponentArrayList.remove(_component);
    }

    public ArrayList<Component> getChildren(){
        return mComponentArrayList;
    }
}
