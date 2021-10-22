import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

public class RateLimiterDemo {

    public static void main(String[] args) throws Exception{

        // 每秒释放一个permit，预热5秒内达到 每秒1个的速度
        RateLimiter limiter = RateLimiter.create(1,5, TimeUnit.SECONDS);

        for(int i = 0;i < 10000;i++){
            boolean result = limiter.tryAcquire();
            System.out.println("result:" + result);
            Thread.sleep(100);
        }

    }


}
