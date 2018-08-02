package com.slowlife.service;

import com.blade.ioc.annotation.Bean;
import com.slowlife.bean.*;
import com.slowlife.service.interfaces.PersonInterface;
import com.slowlife.utils.Conf;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.core.Joins;
import org.json.JSONArray;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Bean
public class PersonService implements PersonInterface {

    @Override
    public String getPersonInfo(String data){
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        int userNumber=jsonObject.getInt("userNumber");
        User user=Anima.select("user_name,user_picture").from(User.class).where("user_number=?",userNumber).one();
        if(user.getUserName()==null||user.getUserName()==""){
            user.setUserName("用户");
        }
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put(Conf.RESULT,new JSONObject(user));
        return result.toString();
    }

    @Override
    public String addAddress(String data){
        System.out.println("传递参数："+data);
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        Address address=new Address();
        address.setAddress(jsonObject.getString("address"));
        address.setPhone(jsonObject.getString("phone"));
        address.setUserNumber(jsonObject.getInt("userNumber"));
        String addressNumber=address.save().asString();//保存订单并返回主键
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put("addressNumber",addressNumber);
        System.out.println("数据整理："+result.toString());
        return result.toString();
    }

    @Override
    public String updataAddress(String data){
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("addressNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        String address=jsonObject.getString("address");
        String phone=jsonObject.getString("phone");
        Address address1=new Address();
        address1.setPhone(phone);
        address1.setAddress(address);
        int i=address1.updateById(jsonObject.getInt("addressNumber"));
        if(i==1){
            result.put(Conf.MSG,Conf.SUCCESS);
            return result.toString();
        }else {
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }

    }

    @Override
    public String deleteAddress(String data){
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("addressNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        int i=Anima.deleteById(Address.class,jsonObject.getInt("addressNumber"));
        if(i==1){
            result.put(Conf.MSG,Conf.SUCCESS);
            return result.toString();
        }else {
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
    }

    @Override
    public String getAddress(String data){
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        List<Address> addressList=Anima.select("address,phone,address_number").from(Address.class).where("user_number=?",jsonObject.getInt("userNumber")).all();
        JSONArray jsonArray=new JSONArray(addressList);
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put(Conf.RESULT,jsonArray);
        System.out.println("数据整理："+result.toString());
        return result.toString();
    }

    @Override
    public String getAllOrder(String data){//所有订单
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        int userNumber=jsonObject.getInt("userNumber");
        System.out.println("查询用户所有的订单:"+userNumber);
        List<OrderInfo> orderInfos=new ArrayList<>();
        orderInfos=Anima.select().from(OrderInfo.class)
                .join(
                        Joins.with(Commodity.class).as(OrderInfo::getCommodityNumber)
                        .on(OrderInfo::getCommodityNumber,Commodity::getCommodityNumber)
                )
               /* .join(
                        Joins.with(Merchent.class).as(OrderInfo::getMerchantNumber)
                        .on(OrderInfo::getMerchantNumber,Merchent::getMerchentNumber)
                )*/
                .where("user_number=?",userNumber).all();
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put(Conf.RESULT,new JSONObject(orderInfos));
        System.out.println("数据整理："+result.toString());
        return result.toString();
    }

    @Override
    public String getGoodsOrder(String data){//待收货订单
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        int userNumber=jsonObject.getInt("userNumber");
        List<OrderInfo> orderInfos=new ArrayList<>();
        orderInfos=Anima.select().from(OrderInfo.class)
                .join(
                        Joins.with(Commodity.class).as(OrderInfo::getCommodityNumber)
                                .on(OrderInfo::getCommodityNumber,Commodity::getCommodityNumber)
                )
                .join(
                        Joins.with(Merchent.class).as(OrderInfo::getMerchantNumber)
                                .on(OrderInfo::getMerchantNumber,Merchent::getMerchentNumber)
                )
                .where("user_number=?",jsonObject.getInt("userNumber")).and("order_status=?",Conf.PROCESSED).all();
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put(Conf.RESULT,new JSONObject(orderInfos));
        System.out.println("数据整理："+result.toString());
        return result.toString();

    }

    @Override
    public String getMoneyOrder(String data){//待退款订单
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        int userNumber=jsonObject.getInt("userNumber");
        List<OrderInfo> orderInfos=new ArrayList<>();
        orderInfos=Anima.select().from(OrderInfo.class)
                .join(
                        Joins.with(Commodity.class).as(OrderInfo::getCommodityNumber)
                                .on(OrderInfo::getCommodityNumber,Commodity::getCommodityNumber)
                )
                .join(
                        Joins.with(Merchent.class).as(OrderInfo::getMerchantNumber)
                                .on(OrderInfo::getMerchantNumber,Merchent::getMerchentNumber)
                )
                .where("user_number=?",jsonObject.getInt("userNumber")).and("order_status=?",Conf.PENDING_REFUND).all();
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put(Conf.RESULT,new JSONObject(orderInfos));
        System.out.println("数据整理："+result.toString());
        return result.toString();

    }

    @Override
    public String getSendGoodsOrder(String data){//待发货订单
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        int userNumber=jsonObject.getInt("userNumber");
        List<OrderInfo> orderInfos=new ArrayList<>();
        orderInfos=Anima.select().from(OrderInfo.class)
                .join(
                        Joins.with(Commodity.class).as(OrderInfo::getCommodityNumber)
                                .on(OrderInfo::getCommodityNumber,Commodity::getCommodityNumber)
                )
                .join(
                        Joins.with(Merchent.class).as(OrderInfo::getMerchantNumber)
                                .on(OrderInfo::getMerchantNumber,Merchent::getMerchentNumber)
                )
                .where("user_number=?",jsonObject.getInt("userNumber")).and("order_status=?",Conf.TO_BE_PROCESSED).all();
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put(Conf.RESULT,new JSONObject(orderInfos));
        System.out.println("数据整理："+result.toString());
        return result.toString();

    }

    @Override
    public String getOrderDealWith(String data){//已完成订单
        JSONObject jsonObject=new JSONObject(data);
        JSONObject result=new JSONObject();
        if(!jsonObject.has("userNumber")){
            result.put(Conf.MSG,Conf.ERROR);
            return result.toString();
        }
        int userNumber=jsonObject.getInt("userNumber");
        List<OrderInfo> orderInfos=new ArrayList<>();
        orderInfos=Anima.select().from(OrderInfo.class)
                .join(
                        Joins.with(Commodity.class).as(OrderInfo::getCommodityNumber)
                                .on(OrderInfo::getCommodityNumber,Commodity::getCommodityNumber)
                )
                .join(
                        Joins.with(Merchent.class).as(OrderInfo::getMerchantNumber)
                                .on(OrderInfo::getMerchantNumber,Merchent::getMerchentNumber)
                )
                .where("user_number=?",jsonObject.getInt("userNumber")).and("order_status=?",Conf.COMPLETE).all();
        result.put(Conf.MSG,Conf.SUCCESS);
        result.put(Conf.RESULT,new JSONObject(orderInfos));
        System.out.println("数据整理："+result.toString());
        return result.toString();

    }
}
