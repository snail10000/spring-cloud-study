package net.xdclass.order_service.service;

import net.xdclass.order_service.fallBack.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务客户端
 */
@FeignClient(name="porduct-service" , fallback = ProductClientFallback.class)
public interface OrderClient {

    @GetMapping("api/vi/product/find")
    String findById(@RequestParam(value = "id") int id);
}
