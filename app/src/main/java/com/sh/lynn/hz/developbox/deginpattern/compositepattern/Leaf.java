package com.sh.lynn.hz.developbox.deginpattern.compositepattern;

/**树叶构件
 * Created by hyz84 on 17/1/10.
 */
public class Leaf extends Component {
    /**
     * 可以复写父类方法
     * @return
     */
    public  String doSomething(){
        return "leaf ";
    }
}
