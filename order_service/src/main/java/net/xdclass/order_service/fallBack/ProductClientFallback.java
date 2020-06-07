package net.xdclass.order_service.fallBack;

import net.xdclass.order_service.service.OrderClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务做降级处理
 */
@Component
public class ProductClientFallback implements OrderClient {
    @Override
    public String findById(int id) {
        System.out.println("feign 调用porduct-service 异常");
        return null;
    }


}
