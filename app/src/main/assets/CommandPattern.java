
/**
 * 命令模式：将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，
 * 对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
 * 命令模式三个角色：
 * Receive 接收者角色
 * Command 命令角色
 * Invoker 调用者角色
 * Created by hyz84 on 16/12/28.
 */

public class CommandPattern {


    public String doSomething(){
        //声明调用者Invoker
        Invoker invoker  = new Invoker();
        //定义接收者
        Receiver receiver1 = new ConcreteReceiver1();
        //定义一个发送给接收者的命令
        Command command1 = new ConcreteCommand1(receiver1);
        invoker.setCommand(command1);
        String result1 = invoker.action();
        //定义接收者
        Receiver receiver2 = new ConcreteReceiver2();
        //定义一个发送给接收者的命令
        Command command2 = new ConcreteCommand2(receiver2);
        invoker.setCommand(command2);
        String result2 =  invoker.action();
        return result1+"\n"+result2;
    }
}

public abstract class Receiver {
    //抽象接收者，定义每个接收者都必须完成的业务
    public abstract String doSomething();
}
public class ConcreteReceiver1 extends Receiver {
    @Override
    public String doSomething() {
        return "ConcreteReceiver1 doSomething ";
    }
}
public class ConcreteReceiver2 extends Receiver {
    @Override
    public String doSomething() {
        return "ConcreteReceiver2 doSomething ";
    }
}
public abstract class Command {
    //每个命令类都必须有个执行命令的方法
    public abstract String execute();
}
public class ConcreteCommand1 extends Command {
    //对哪个Receiver类进行命令处理
    private Receiver receiver;

    public ConcreteCommand1(Receiver _receiver){
        receiver = _receiver;
    }

    @Override
    public String execute() {
        return this.receiver.doSomething();
    }
}
public class ConcreteCommand2 extends com.sh.lynn.hz.deginpattern.commandpattern.Command {
    //对哪个Receiver类进行命令处理
    private com.sh.lynn.hz.deginpattern.commandpattern.Receiver receiver;

    public ConcreteCommand2(com.sh.lynn.hz.deginpattern.commandpattern.Receiver _receiver){
        receiver = _receiver;
    }

    @Override
    public String execute() {
        return  this.receiver.doSomething();
    }
}

public class Invoker {
    private Command mCommand;
    //接收命令
    public void setCommand(Command _command){
        this.mCommand=_command;
    }

    public String action(){
        return this.mCommand.execute();
    }
}