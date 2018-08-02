package com.slowlife.service;

import com.blade.ioc.annotation.Bean;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Session;
import com.slowlife.bean.Commodity;
import com.slowlife.bean.ShoppingCart;
import com.slowlife.service.interfaces.ShoppingCartInterface;
import com.slowlife.utils.Conf;
import com.slowlife.utils.JsonUtils;
import io.github.biezhi.anima.Anima;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Bean
public class ShoppingCartService implements ShoppingCartInterface {


    @Override
    public String getUserCartInfo(String data2) {

        JSONObject data=new JSONObject(data2);

        int number=data.getInt("userNumber");

       // number="5";

        System.out.println("获取用户ID准备初始化加载数据"+number);

        JSONObject result=new JSONObject();

        if (!data.has("userNumber")){

            result.put(Conf.MSG,Conf.ERROR);

            System.out.println("用户未登录无法加载数据");

            return result.toString();

        }


        List<ShoppingCart> shoppingCartList=Anima
                .select("*")
                .from(ShoppingCart.class)
                .where("user_number=?",number)
                .all();

        System.out.println("该用户的购物车数"+shoppingCartList.size());

        for (ShoppingCart cart : shoppingCartList) {

            int n = cart.getCommodityNumber();

            Commodity commodity = Anima.select().from(Commodity.class).where("commodity_number=?", n).one();

            cart.setCommodity(commodity);
        }

        JSONArray jsonArray=new JSONArray(shoppingCartList);
        result.put(Conf.MSG,Conf.SUCCESS);

        result.put(Conf.RESULT,jsonArray);

        System.out.println("数据准备完毕"+result.toString());


        return result.toString();
    }


    @Override
    public String setSCadd(String SCadd){

        System.out.println("前台数据为"+SCadd);

        JSONObject data=new JSONObject(SCadd);

        JSONObject result=new JSONObject();

        if(!data.has("id")){

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();

        }

        int id=data.getInt("id");

        int count=data.getInt("count");

        int i=Anima.update().from(ShoppingCart.class).set("count",count).updateById(id);

        System.out.println("更新记录影响条数"+i);

        if(i==1){

            result.put(Conf.MSG,Conf.SUCCESS);

            return result.toString();

        }else {

            result.put(Conf.MSG,Conf.ERROR);

            return result.toString();

        }

    }
}
