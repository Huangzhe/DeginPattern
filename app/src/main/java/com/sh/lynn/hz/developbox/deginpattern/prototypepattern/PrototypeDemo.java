package com.sh.lynn.hz.developbox.deginpattern.prototypepattern;

import java.util.ArrayList;

/**
 * 浅拷贝
 * Created by hyz84 on 16/12/19.
 */

public class PrototypeDemo implements Cloneable {
    private ArrayList<String> mArrayList = new ArrayList<>();
    @Override
    public PrototypeDemo clone() throws CloneNotSupportedException {
        PrototypeDemo paper =null;
        try{
            paper =(PrototypeDemo)  super.clone();
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
