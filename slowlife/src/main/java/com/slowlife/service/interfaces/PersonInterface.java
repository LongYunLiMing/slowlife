package com.slowlife.service.interfaces;

public interface PersonInterface {

    String getPersonInfo(String data);

    String addAddress(String data);

    String updataAddress(String data);

    String deleteAddress(String data);

    String getAddress(String data);

    String getAllOrder(String data);

    String getGoodsOrder(String data);

    String getMoneyOrder(String data);

    String getSendGoodsOrder(String data);

    String getOrderDealWith(String data);

}
