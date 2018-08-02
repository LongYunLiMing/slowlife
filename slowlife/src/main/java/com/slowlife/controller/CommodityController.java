package com.slowlife.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Response;
import com.slowlife.service.CommodityService;
import com.slowlife.service.interfaces.CommodityInterface;
import org.json.JSONObject;

@Path
public class CommodityController {

    @Inject
    private CommodityInterface service;

    @GetRoute("/v1/get-commodity")
    @JSON
    public void getCommodity(@Param String commodityData, Response response){

        JSONObject commodity=new JSONObject(commodityData);

        response.json(service.getCommodityInfo(commodity));
    }

    @GetRoute("/v1/addShoppingCart")
    @JSON
    public void addShoppingCart(@Param String addShoppingCartdata,Response response){

        response.json(service.addShoppingCart(addShoppingCartdata));

    }

    @GetRoute("/v1/getDetailInfo")
    @JSON
    public void getDeatilInfo(@Param String infoData,Response response){

        response.json(service.getDetailInfo(infoData));
    }
}
