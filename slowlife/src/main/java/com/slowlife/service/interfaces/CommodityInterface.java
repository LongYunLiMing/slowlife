package com.slowlife.service.interfaces;

import org.json.JSONObject;

public interface CommodityInterface {

    String getCommodityInfo(JSONObject number);

    String addShoppingCart(String data);

    String getDetailInfo(String data);
}
