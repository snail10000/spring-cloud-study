package net.xdclass.product_service.service.impl;

import net.xdclass.product_service.domain.Product;
import net.xdclass.product_service.service.ProductorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductorServiceImpl implements ProductorService {

    private static final Map<Integer,Product> doMap = new HashMap<>();

    static{
        Product product1 = new Product(1,"iphone",999,10);
        Product product2 = new Product(2,"冰箱",458,10);
        Product product3 = new Product(3,"洗衣机",896,10);
        Product product4 = new Product(4,"电话",236,10);
        Product product5 = new Product(5,"汽车",854,10);
        Product product6 = new Product(6,"椅子",789,10);
        Product product7 = new Product(7,"java编程思想",596,10);

        doMap.put(product1.getId(),product1);
        doMap.put(product2.getId(),product2);
        doMap.put(product3.getId(),product3);
        doMap.put(product4.getId(),product4);
        doMap.put(product5.getId(),product5);
        doMap.put(product6.getId(),product6);
        doMap.put(product7.getId(),product7);

    }

    @Override
    public List<Product> listProductor() {

        Collection<Product> collection = doMap.values();
        List<Product> list = new ArrayList<>(collection);

        return list;
    }

    @Override
    public Product findById(int id) {

        return  doMap.get(id);
    }
}
