package com.slowlife.bean;

import io.github.biezhi.anima.Model;
import io.github.biezhi.anima.annotation.Table;

@Table(name = "merchant")
public class Merchent extends Model {


    private int merchantNumber;
    private String merchantName;
    private String merchantPhone;
    private String merchantEmail;
    private String merchantAddress;
    private int commodityQuantity;
    private String registerTime;
    private String idNumber;
    private String idNFrontPicture;
    private String idNBackPicture;
    private String merchantPicture;
    private String merchantPassword;

    public int getMerchentNumber() {
        return merchantNumber;
    }

    public void setMerchentNumber(int merchentNumber) {
        this.merchantNumber = merchentNumber;
    }

    public String getMerchentName() {
        return merchantName;
    }

    public void setMerchentName(String merchentName) {
        this.merchantName = merchentName;
    }

    public String getMerchentPhone() {
        return merchantPhone;
    }

    public void setMerchentPhone(String merchentPhone) {
        this.merchantPhone = merchentPhone;
    }

    public String getMerchentEmail() {
        return merchantEmail;
    }

    public void setMerchentEmail(String merchentEmail) {
        this.merchantEmail = merchentEmail;
    }

    public String getMerchentAddress() {
        return merchantAddress;
    }

    public void setMerchentAddress(String merchentAddress) {
        this.merchantAddress = merchentAddress;
    }

    public int getCommodityQuantity() {
        return commodityQuantity;
    }

    public void setCommodityQuantity(int commodityQuantity) {
        this.commodityQuantity = commodityQuantity;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdNFrontPicture() {
        return idNFrontPicture;
    }

    public void setIdNFrontPicture(String idNFrontPicture) {
        this.idNFrontPicture = idNFrontPicture;
    }

    public String getIdNBackPicture() {
        return idNBackPicture;
    }

    public void setIdNBackPicture(String idNBackPicture) {
        this.idNBackPicture = idNBackPicture;
    }

    public String getMerchentPicture() {
        return merchantPicture;
    }

    public void setMerchentPicture(String merchentPicture) {
        this.merchantPicture = merchentPicture;
    }

    public String getMerchentPassword() {
        return merchantPassword;
    }

    public void setMerchentPassword(String merchentPassword) {
        this.merchantPassword = merchentPassword;
    }
}
