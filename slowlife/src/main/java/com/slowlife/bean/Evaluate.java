package com.slowlife.bean;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "evaluate")
public class Evaluate extends Model {

    private int evaluateNumber;//评价id，自增
    private int commodityNumberl;//评价商品id
    private int userNumber;//评价人
    private String evaluateContent;//评价内容
    private String evaluateTime;//评价时间

    public int getEvaluateNumber() {
        return evaluateNumber;
    }

    public void setEvaluateNumber(int evaluateNumber) {
        this.evaluateNumber = evaluateNumber;
    }

    public int getCommodityNumberl() {
        return commodityNumberl;
    }

    public void setCommodityNumberl(int commodityNumberl) {
        this.commodityNumberl = commodityNumberl;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(String evaluateTime) {
        this.evaluateTime = evaluateTime;
    }
}
