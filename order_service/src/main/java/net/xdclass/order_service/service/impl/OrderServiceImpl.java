package net.xdclass.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import net.xdclass.order_service.domain.ProductOrder;
import net.xdclass.order_service.service.OrderClient;
import net.xdclass.order_service.service.OrderService;
import net.xdclass.order_service.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private OrderClient orderClient;
    @Override
    public ProductOrder save(int userId, int productId) {

//        Map<String,Object> productmap = restTemplate.getForObject("http://porduct-service/api/vi/product/find?id=" + productId, Map.class);
        //调用订单服务
        String res = orderClient.findById(productId);

        //调用用户服务
        //TODO
        JsonNode jsonNode = JsonUtils.StrToJsonNode(res);
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTriadNO(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
