package staticproxy;

/**
 * @author sunhang
 * @date 2019/8/2 14:16
 * @email sunhang@weli.cn
 */
public class CalculatorProxy implements Calculator{
    private Calculator calculator;

    public CalculatorProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int a, int b) {
        System.out.println("before advice");
        int result = calculator.add(a,b);
        System.out.println("after advice");
        return result;
    }
}
