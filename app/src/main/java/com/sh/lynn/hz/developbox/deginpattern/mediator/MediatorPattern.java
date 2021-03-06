package com.sh.lynn.hz.developbox.deginpattern.mediator;

/**
 * 定义：用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示相互作用，
 *       从而使其耦合松散，而且可以独立地改变它们之间的交互。
 *中介者模式的组成部分：
 * 1.Mediator 抽象中介者角色 定义统一的接口，用于各同事角色之间的通信。
 * 2.Concrete Mediator 具体中介者角色  通过协调各同事角色实现协作行为
 * 3.Colleague 同事角色
 *     每一个同事角色都知道中介者角色，而且与其它的同事角色通信时候，一定通过中介者角色协作。
 *     同事类行为：
 *     （1）.同事类本身行为，比如改变对象本身的状态，处理自己的行为等，自有方法
 *     （2）.必须依赖中介者才能完成的行为 ，依赖方法
 * Created by hyz84 on 16/12/20.
 */

public class MediatorPattern {
}
