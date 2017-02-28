
/**
 * 责任链模式：使多个对象都有机会处理请求，从而避免了请求的发送者和接收者之间的耦合关系。
 * 将这些对象连成一条链，并沿着这条链传递该请求，直到有对象处理它为止。
 * Created by hyz84 on 16/12/28.
 */

public class ChainPattern {

    public String doSomething(){
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);
//注意这里的调用 都是handle1
        Response response1 = handler1.handleMessage(new Request(Level.HIGHT,"hight request"));
        Response response2 = handler1.handleMessage(new Request(Level.LOW,"low request"));
        Response response3 = handler1.handleMessage(new Request(Level.MID,"mid request"));
        return response1.getResult()+"\n"+response2.getResult()+"\n"+response3.getResult();
    }
}

public class Request {
    private Level mLevel;
    private String msg ;
    public Request(Level _level,String _msg){
        mLevel=_level;
        msg=_msg;
    }
    public Level getRequestLevel(){
        return mLevel;
    }

    public String getMsg(){
        return  msg;
    }
}

public enum  Level {
    LOW ,MID,HIGHT
}
public class Response {
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

public abstract class Handler {
    private Handler nextHandler;
    //每个处理者都必须对请求做出处理
    public final Response handleMessage(Request request) {
        Response response = null;
        //判断是否是自己的处理级别
        if (getHandlerLevel().equals(request.getRequestLevel())) {
            response = echo(request);
        } else {
            //下一个处理者
            if (this.nextHandler != null) {
                response = this.nextHandler.handleMessage(request);
            } else {

            }
        }
        return response;
    }
    //设置下一个处理者是谁
    public void setNextHandler(Handler _handler) {
        this.nextHandler = _handler;
    }
    //每个处理者都有一个处理级别
    protected abstract Level getHandlerLevel();
    //每个处理者都必须实现处理任务
    protected abstract Response echo(Request request);
}

public class ConcreteHandler1 extends Handler {
    @Override
    protected Level getHandlerLevel() {
        return Level.LOW;
    }

    @Override
    protected Response echo(Request request) {
        Response response = new Response();
        response.setResult(request.getMsg());
        return response;
    }
}

public class ConcreteHandler2 extends Handler {
    @Override
    protected Level getHandlerLevel() {
        return Level.MID;
    }

    @Override
    protected Response echo(Request request) {
        Response response = new Response();
        response.setResult(request.getMsg());
        return response;
    }
}

public class ConcreteHandler3 extends Handler {
    @Override
    protected Level getHandlerLevel() {
        return Level.HIGH;
    }

    @Override
    protected Response echo(Request request) {
        Response response = new Response();
        response.setResult(request.getMsg());
        return response;
    }
}