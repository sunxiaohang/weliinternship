package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunhang
 * @date 2019/8/2 16:55
 * @email sunhang@weli.cn
 */
public class MyJUnitFramework {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = MainTest.class;
        Object obj = clazz.newInstance();
        Method[] methods = clazz.getMethods();
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        for (Method method:methods){
            if(method.isAnnotationPresent(MyBefore.class)){
                beforeMethods.add(method);
            }else if(method.isAnnotationPresent(MyTest.class)){
                testMethods.add(method);
            }else if(method.isAnnotationPresent(MyAfter.class)){
                afterMethods.add(method);
            }
        }
        for (Method testMethod:testMethods){
            for (Method method:beforeMethods){
                method.invoke(obj);
            }
            testMethod.invoke(obj);
            for (Method method:afterMethods){
                method.invoke(obj);
            }
        }
    }
}
