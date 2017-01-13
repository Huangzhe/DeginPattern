package com.sh.lynn.hz.developbox.deginpattern.prototypepattern;

import java.util.ArrayList;

/**深拷贝
 * Created by hyz84 on 16/12/19.
 */

public class PrototypeDemoDeep implements Cloneable {

    private ArrayList<String> mArrayList = new ArrayList<>();
    @Override
    public PrototypeDemoDeep clone() throws CloneNotSupportedException {
        PrototypeDemoDeep paper =null;
        try{
            paper =(PrototypeDemoDeep)  super.clone();
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
