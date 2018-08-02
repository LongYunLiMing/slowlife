package com.slowlife.service;

import com.blade.ioc.annotation.Bean;
import com.slowlife.bean.Commodity;
import com.slowlife.bean.ShoppingCart;
import com.slowlife.service.interfaces.CommodityInterface;
import com.slowlife.utils.Conf;
import io.github.biezhi.anima.Anima;
import org.json.JSONObject;

@Bean
public class CommodityService implements CommodityInterface {

    @Override
    public String getCommodityInfo(JSONObject number){

        JSONObject result=new JSONObject();

        if(!number.has("commodityNumber")){

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();
        }

        int id=number.getInt("commodityNumber");

        Commodity commodity=Anima.select().from(Commodity.class).where("commodity_number",id).one();

        if(commodity!=null){

            JSONObject object=new JSONObject(commodity);

            result.put("commodity",object);

            result.put(Conf.MSG,Conf.SUCCESS);

            return result.toString();
        }else {

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();
        }
    }

    @Override
    public String addShoppingCart(String data){
        System.out.println("前台传递数据"+data);

        JSONObject result=new JSONObject();

        JSONObject jsonObject=new JSONObject(data);

        if(!jsonObject.has("user_number")||!jsonObject.has("commodity_number")||!jsonObject.has("count")){

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();

        }

        int user_number=jsonObject.getInt("user_number");

        System.out.println("用户编号"+user_number);

        int commodity_number=jsonObject.getInt("commodity_number");

        System.out.println("商品编号"+commodity_number);

        int count=jsonObject.getInt("count");

        System.out.println("商品数量"+count);

        ShoppingCart shoppingCart=new ShoppingCart();

        shoppingCart.setUserNumber(user_number);

        shoppingCart.setCommodityNumber(commodity_number);

        shoppingCart.setCount(count);

        String key=shoppingCart.save().asString();

        if(key==null||key==""){

            System.out.println("记录保存失败");

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();

        }

        result.put(Conf.MSG,Conf.SUCCESS);

        return result.toString();
    }

    @Override
    public String getDetailInfo(String data){

        JSONObject result=new JSONObject();

        JSONObject jsonObject=new JSONObject(data);

        if(!jsonObject.has("commodityNumber")){

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();

        }

        int commodityNumber=jsonObject.getInt("commodityNumber");

        System.out.println("商品编号"+commodityNumber);

        Commodity commodity=new Commodity();

        commodity=Anima.select("commodity_description").from(Commodity.class).byId(commodityNumber);

        //String description=Anima.select().from(Commodity.class).exclu

        JSONObject com=new JSONObject(commodity);

        result.put(Conf.RESULT,com);

        result.put(Conf.MSG,Conf.SUCCESS);

        System.out.println("返回数据整理"+result.toString());

        return result.toString();

    }
}
