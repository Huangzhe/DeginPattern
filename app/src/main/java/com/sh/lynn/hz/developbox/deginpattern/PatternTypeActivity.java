package com.sh.lynn.hz.developbox.deginpattern;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sh.lynn.hz.developbox.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatternTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 单例模式
     */
    @OnClick(R.id.button)
    public void onClickSingletonPattern(){
        gotoPatternAct("SingletonBean.java", "单例模式");
    }

    /**
     * 工厂模式
     */
    @OnClick(R.id.button2)
    public void onClickFactoryPattern(){
        gotoPatternAct("FactoryPattern.java", "工厂模式");
    }

    /**
     * 模板方法模式
     */
    @OnClick(R.id.button4)
    public void onClickTemplateMethodPattern(){
        gotoPatternAct("TemplateMethodPattern.java", "模板方法模式");
    }

    /**
     * 建造者模式
     */
    @OnClick(R.id.button3)
    public void onClickBuildPattern(){
        gotoPatternAct("BuilderPattern.java", "建造者模式");
    }

    /**
     * 代理模式
     */
    @OnClick(R.id.button5)
    public void onClickProxyPattern(){
        gotoPatternAct("ProxyPattern.java", "代理模式");
    }

    /**
     * 原型模式
     */
    @OnClick(R.id.button6)
    public void onClickPrototypePattern(){
        gotoPatternAct("PrototypePattern.java", "原型模式");
    }

    /**
     * 中介者模式
     */
    @OnClick(R.id.button7)
    public void onClickMediatorPattern(){
        gotoPatternAct("MediatorPattern.java", "中介者模式");
    }
    /**
     * 命令模式
     */
    @OnClick(R.id.button8)
    public void onClickCommandPattern(){
        gotoPatternAct("CommandPattern.java", "命令模式");
    }

    /**
     * 责任链模式
     */
    @OnClick(R.id.button9)
    public void onClickChainPattern(){
        gotoPatternAct("ChainPattern.java", "责任链模式");
    }

    /**
     * 装饰模式
     */
    @OnClick(R.id.button10)
    public void onClickDecortorPattern(){
        gotoPatternAct("DecoratorPattern.java", "装饰模式");
    }

    /**
     * 策略模式
     */
    @OnClick(R.id.button11)
    public void onClickStrategyPattern(){
        gotoPatternAct("StrategyPattern.java", "策略模式");
    }


    /**
     * 适配器模式
     */
    @OnClick(R.id.button12)
    public void onClickAdapterPattern(){
        gotoPatternAct("AdapterPattern.java", "适配器模式");
    }
    /**
     * 迭代器模式
     */
    @OnClick(R.id.button13)
    public void onClickIteratorPattern(){
        gotoPatternAct("IteratorPattern.java", "迭代器模式");
    }

    /**
     * 组合模式
     */
    @OnClick(R.id.button14)
    public void onClickCompositePattern(){
        gotoPatternAct("CompositePattern.java", "组合模式");
    }
    /**
     * 观察者模式
     */
    @OnClick(R.id.button15)
    public void onClickObserverPattern(){
        gotoPatternAct("ObserverPattern.java", "观察者模式");
    }

    /**
     * 门面模式
     */
    @OnClick(R.id.button16)
    public void onClickFacadePattern(){
        gotoPatternAct("FacadePattern.java", "门面模式");
    }

    @OnClick(R.id.button17)
    public void onClickMementoPattern(){gotoPatternAct("MementoPattern.java","备忘录模式");}
    @OnClick(R.id.button18)
    public void onClickVisitorPattern(){gotoPatternAct("VisitorPattern.java","访问者模式");}
    @OnClick(R.id.button19)
    public void onClickStatePattern(){gotoPatternAct("StatePattern.java","状态模式");}

    @OnClick(R.id.button20)
    public void onClickInterpreterPattern(){gotoPatternAct("InterpreterPattern.java","解释器模式");}

       @OnClick(R.id.button21)
    public void onClickFlyweightPattern(){gotoPatternAct("FlyweightPattern.java","享元模式");}

    @OnClick(R.id.button22)
    public void onClickBridgePattern(){gotoPatternAct("BridgePattern.java","桥梁模式");}


    private void gotoPatternAct(String pattern, String title) {
        Intent intent = new Intent(this, PatternActivity.class);
        intent.putExtra("Pattern", pattern);
        intent.putExtra("Title", title);
        startActivity(intent);
    }
}
