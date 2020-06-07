package net.xdclass.product_service.service;

import net.xdclass.product_service.domain.Product;

import java.util.List;

public interface ProductorService {

    List<Product> listProductor();

    Product findById(int id);

}
