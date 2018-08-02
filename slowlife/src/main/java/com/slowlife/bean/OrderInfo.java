package com.slowlife.bean;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Ignore;
import io.github.biezhi.anima.annotation.Table;

import java.util.List;


//针对订单数据的专用数据表模型
@Table(name = "order_info",pk = "order_number")
public class OrderInfo extends Model {
    @Ignore
    private List<Commodity> commodityList;
    //private String orderId;
    private String orderNumber;
    private float price;
    private String status;
    @Ignore
    private Merchent merchent;
    private int commodityNumber;
    private int merchantNumber;

    public void setMerchantNumber(int number){merchantNumber=number;}

    public int getMerchantNumber(){return merchantNumber;}

    public void setCommodityNumber(int number){commodityNumber=number;}

    public int getCommodityNumber(){return commodityNumber;}

    public void setMerchent(Merchent mer){merchent=mer;}

    public Merchent getMerchent(){return merchent;}

    public void setOrderNumber(String number){orderNumber=number;}

    private String getOrderNumber(){return orderNumber;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

   /* public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String id) {
        this.orderId = id;
    }
*/
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
