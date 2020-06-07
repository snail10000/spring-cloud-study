package net.xdclass.order_service.controller;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import net.xdclass.order_service.domain.ProductOrder;
import net.xdclass.order_service.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod="saveOrderFiled")
    public Object save(@RequestParam("user_id") int  userId ,@RequestParam("porduct_id") int productId ,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>(16);
        map.put("code",0);
        map.put("msg",orderService.save(userId,productId));
        return map;
    }

    /**可以使用全局异常或者熔断方法
     * 熔断的方法
     * @param userId
     * @param productId
     * @return
     */
    //注意方法签名与需要一致
    private Object saveOrderFiled(int  userId , int productId, HttpServletRequest request){
        String saveOrder = "save-order";

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder() .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        singleThreadPool.execute(()->{
            String ip = request.getRemoteAddr();
            //监控报警伪代码
            String sendvalue = redisTemplate.opsForValue().get(saveOrder);
            if(StringUtils.isBlank(sendvalue)){
                System.out.println("紧急短信,用户下单失败,请查找原因,ip地址为"+ip);
                // 发送一个http请求调用短信服务

                redisTemplate.opsForValue().set(saveOrder,"save-order-fail",20,TimeUnit.SECONDS);
            }else{
                System.out.println("已经发送过短信,20秒内不重复发送");
            }
            System.out.println(Thread.currentThread().getName());
        } );
        singleThreadPool.shutdown();
        Map<String,Object> map = new HashMap<>();
        map.put("code",-1);
        map.put("msg","抢购人数太多,您被挤出来了,请稍后重试");
        return map;
    }
}
