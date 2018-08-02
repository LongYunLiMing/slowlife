package com.slowlife.bean;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class OrderShow {

    private int MerchentNumber;

    private String MerchentPicture;

    private String MerchentName;

    private Double Money;

    //private List<Commodity> commodityList;
    private JSONArray commodityJSONArry;

    public void setMoney(Double money) {
        Money = money;
    }

    public Double getMoney() {
        return Money;
    }

    public void setCommodityJSONArry(JSONArray jsonArry){commodityJSONArry=jsonArry;}
    public JSONArray getCommodityJSONArry(){return commodityJSONArry;}
    //public List<Commodity> getCommodityList(){return commodityList;}
   // public void setCommodityList(List<Commodity> commodityList){this.commodityList=commodityList;}
    public void setMerchentNumber(int merchentNumber){MerchentNumber=merchentNumber;}
    public int getMerchentNumber(){return MerchentNumber;}
    public void setMerchentPicture(String merchentPicture){MerchentPicture=merchentPicture;}
    public String getMerchentPicture(){return MerchentPicture;}
    public void setMerchentName(String merchentName){MerchentName=merchentName;}
    public String getMerchentName(){return MerchentName;}
}
