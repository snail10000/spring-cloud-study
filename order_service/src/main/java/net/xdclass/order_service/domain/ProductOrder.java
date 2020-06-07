package net.xdclass.order_service.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductOrder implements Serializable {

    private int id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 订单号
     */
    private String triadNO;

    private int price;

    private Date createTime;

    private int userId;

    private String productName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTriadNO() {
        return triadNO;
    }

    public void setTriadNO(String triadNO) {
        this.triadNO = triadNO;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
