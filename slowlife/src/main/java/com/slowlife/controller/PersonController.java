package com.slowlife.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Response;
import com.slowlife.service.PersonService;

@Path
public class PersonController {
    @Inject
    PersonService service;

    @GetRoute("/v1/getPerson")
    @JSON
    public void getPersonInfo(@Param String personData, Response response){
        response.json(service.getPersonInfo(personData));
    }

    @GetRoute("/v1/addAddress")
    @JSON
    public void addAddress(@Param String addData,Response response){
        response.json(service.addAddress(addData));
    }

    @GetRoute("/v1/updataAddress")
    @JSON
    public void updataAddress(@Param String updataData,Response response){
        response.json(service.updataAddress(updataData));
    }

    @GetRoute("/v1/deleteAddress")
    @JSON
    public void deleteAddress(@Param String deleteData,Response response){
        response.json(service.deleteAddress(deleteData));
    }

    @GetRoute("/v1/getAddressP")
    @JSON
    public void getAddress(@Param String getData,Response response){
        response.json(service.getAddress(getData));
    }


    @GetRoute("/v1/allOrder")
    @JSON
    public void getAllOrder(@Param String data,Response response){
        response.json(service.getAllOrder(data));
    }

    @GetRoute("/v1/getOrder")
    @JSON
    public void getGoodsOrder(@Param String data,Response response){
        response.json(service.getGoodsOrder(data));
    }

    @GetRoute("/v1/sendOrder")
    @JSON
    public void getSendOrder(@Param String data,Response response){
        response.json(service.getSendGoodsOrder(data));
    }

    @GetRoute("/v1/moneyOrder")
    @JSON
    public void getMoneyOrder(@Param String data,Response response){
        response.json(service.getMoneyOrder(data));
    }

    @GetRoute("/v1/competeOrder")
    @JSON
    public void getCompeteOrder(@Param String data,Response response){
        response.json(service.getOrderDealWith(data));
    }
}
