package net.xdclass.product_service.controller;

import net.xdclass.product_service.domain.Product;
import net.xdclass.product_service.service.ProductorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/vi/product")
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductorService productorService;

    /**
     * 获取所有商品列表
     * @return
     */
    @RequestMapping("list")
    public Object list(){
        return productorService.listProductor();
    }

    /**
     * 根据id查找商品
     * @return
     */
    @RequestMapping("find")
    public Object findById(@RequestParam("id") int id){
        try {
            // 模拟商品服务响应比较慢的情况
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Product product = productorService.findById(id);
        Product result = new Product();
        BeanUtils.copyProperties(product,result);
        result.setName(result.getName()+" data from port: "+port);
        return result;
    }
}
