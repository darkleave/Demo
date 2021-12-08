import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaInterfaceTest {

    public static void main(String[] args) {

        Object product = getSomething(() -> Integer.MAX_VALUE);
        System.out.println(product);

        doSomething(Integer.MAX_VALUE,v -> System.out.println("doSomething: " + v));

        String result = factory(Integer.MAX_VALUE,v -> "整数最大值为:" + v);
        System.out.println(result);

        boolean predicateResult = check(Integer.MAX_VALUE,v -> v > Short.MAX_VALUE);

        System.out.println("断言结果:" + predicateResult);
    }

    /**
     *
     * @param supplier 供给型接口为参数,没有输入参数，只有返回值
     * @param <T>
     * @return
     */
    public static <T> T getSomething(Supplier<T> supplier){
        return supplier.get();
    }

    /**
     *
     * @param t
     * @param consumer 消费型接口为参数,只有输入参数，没有返回值
     * @param <T>
     */
    public static <T> void doSomething(T t, Consumer<T> consumer){
        consumer.accept(t);
    }

    /**
     *
     * @param t
     * @param function 函数型接口，既有输入参数，又有返回值
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> R factory(T t, Function<T,R> function){
        return function.apply(t);
    }

    /**
     *
     * @param t
     * @param predicate 断言型接口，有输入参数，返回值为boolean
     * @param <T>
     * @return
     */
    public static <T> Boolean check(T t, Predicate<T> predicate){
        return predicate.test(t);
    }
}
