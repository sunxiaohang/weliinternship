package annotation;

/**
 * @author sunhang
 * @date 2019/8/2 16:53
 * @email sunhang@weli.cn
 */
public class MainTest {
    @MyBefore
    public void myBefore(){
        System.out.println("before advice");
    }
    @MyAfter
    public void afterBefore(){
        System.out.println("after advice");
    }
    @MyTest
    public void testA(){
        System.out.println("test a advice");
    }
    @MyTest
    public void testB(){
        System.out.println("test b advice");
    }
}
