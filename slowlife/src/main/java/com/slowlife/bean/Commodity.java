package com.slowlife.bean;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Column;
import io.github.biezhi.anima.annotation.Ignore;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "commodity",pk = "commodity_number")
public class Commodity extends Model {

    private Integer commodityNumber;

    private String commodityName;

    private float commodityPrice;

    private String commodityDescription;

    private int commodityInventory;

    private String commodityType;

    private String shelfTime;

    private String commodityPicture;

    private Integer totalSalesValume;

    private Integer merchantNumber;

    @Ignore
    private Integer count;

    public void setCount(int c){count=c;}

    public Integer getCount(){return count;}

    public void setMerchantNumber(int merchantNumber){this.merchantNumber=merchantNumber;}

    public Integer getMerchantNumber(){return this.merchantNumber;}

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public float getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(float commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityDescription() {
        return commodityDescription;
    }

    public void setCommodityDescription(String commodityDescription) {
        this.commodityDescription = commodityDescription;
    }

    public int getCommodityInventory() {
        return commodityInventory;
    }

    public void setCommodityInventory(int commodityInventory) {
        this.commodityInventory = commodityInventory;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public String getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(String shelfTime) {
        this.shelfTime = shelfTime;
    }

    public String getCommodityPicture() {
        return commodityPicture;
    }

    public void setCommodityPicture(String merchentPicture) {
        this.commodityPicture = merchentPicture;
    }

    public Integer getTotalSalesValume() {
        return totalSalesValume;
    }

    public void setTotalSalesValume(Integer totalSalesValume) {
        this.totalSalesValume = totalSalesValume;
    }
}
