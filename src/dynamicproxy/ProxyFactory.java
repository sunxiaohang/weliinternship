package dynamicproxy;

import staticproxy.Calculator;
import staticproxy.CalculatorImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author sunhang
 * @date 2019/8/2 14:19
 * @email sunhang@weli.cn
 */
public class ProxyFactory implements InvocationHandler {
    private Class<?> target;
    private Object real;

    public ProxyFactory(Class<?> target) {
        this.target = target;
    }

    public Object bind(Object real){
        this.real=real;
        return Proxy.newProxyInstance(target.getClassLoader(),new Class[]{target},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before advice");
        Object result = method.invoke(real,args);
        System.out.println("after advice");
        return result;
    }

    public static void main(String[] args) {
        Calculator proxy = (Calculator) new ProxyFactory(Calculator.class).bind(new CalculatorImpl());
        proxy.add(1,2);
    }
}
