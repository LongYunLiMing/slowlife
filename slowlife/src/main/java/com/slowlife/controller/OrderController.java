package com.slowlife.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Response;
import com.slowlife.service.interfaces.OrderInterface;

@Path
public class OrderController {

    @Inject
    private OrderInterface service;


    /**
     * 接收信息并生成订单
     * 要求：token、商品编号、商品数量、用户id
     * token参数名：token
     * 商品列表参数名：list,子item内包括商品编号以及商品数量，具体格式如下
     * 商品编号参数名：commodityNumder
     * 商品数量参数名：count
     *
     *
     * 还有一个：
     * 用户id:userNumber 用于保存相对信息
     * @param orderData json数据
     *
     *                  总格式：orderData={
     *                  token:xxxx,
     *                  list:[
     *                  {
     *                      commdityNumber:1001,
     *                      count:2
     *                  },
     *
     *                  {
     *                      commdityNumber:1002,
     *                      count:1
     *                  }
     *
     *                  ],
     *
     *                  userNumber:1000
     *                  }
     *
     *
     * @return 返回订单相关内容
     */
    @GetRoute("/v1/create-order")
    @JSON
    public String createOrder(@Param String orderData){

        System.out.println("json------->>>>"+orderData);

        return service.createOrder(orderData);


    }

    @GetRoute("/v1/getAddress")
    @JSON
    public void getAddress(@Param String addressData, Response response){

        response.json(service.getAddress(addressData));

    }

    @GetRoute("/v1/order-get-commodity")
    @JSON
    public void orderGetCommodity(@Param String orderData,Response response){

        response.json(service.orderGetCommodity(orderData));

    }

}
