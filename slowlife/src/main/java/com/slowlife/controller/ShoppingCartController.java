package com.slowlife.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.slowlife.service.interfaces.ShoppingCartInterface;
import org.json.JSONObject;

@Path
public class ShoppingCartController {


    @Inject
    private ShoppingCartInterface service;


    @GetRoute("/v1/user-cart-info")
    public void getUserCartInfo(@Param String data, Response response){

        //JSONObject jsonObject=new JSONObject(data);

        //return service.getUserCartInfo(jsonObject);

         response.json(service.getUserCartInfo(data));

    }

    @GetRoute("/v1/commodity_add")
    public void commodityAdd(@Param String SCnumber,Response response){

        response.json(service.setSCadd(SCnumber));
    }


}
