package com.slowlife.service;

import com.blade.ioc.annotation.Bean;
import com.slowlife.bean.Commodity;
import com.slowlife.bean.Merchent;
import com.slowlife.service.interfaces.IndexInterface;
import com.slowlife.utils.JsonUtils;
import io.github.biezhi.anima.Anima;
import io.github.biezhi.anima.core.functions.TypeFunction;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Bean
public class IndexService implements IndexInterface {


    /**
     * 首页信息
     *
     * @return
     */
    @Override
    public String indexInfo() {

        List<Merchent> merchentList=Anima.select("merchent_number,merchent_name,commodity_quantity,merchent_picture").from(Merchent.class).all();

        JSONObject jsonObject=JsonUtils.getJsonObject("success");

        JSONArray jsonArray=new JSONArray(merchentList);

        jsonObject.put("result",jsonArray);


        return jsonObject.toString();
    }

    /**
     * 搜索
     * @param keyword
     * @return
     */
    @Override
    public String search(JSONObject keyword) {
        String key=keyword.getString("key");
        System.out.println("通过关键字查询商品"+key);
        //查询商品表匹配名字以及说明
        List<Commodity> commodityList=Anima
                .select()
                .bySQL(Commodity.class,"select * from commodity where concat (commodity_name,commodity_description) like ?","%"+key+"%")
                .all();


        JSONArray jsonArray=new JSONArray(commodityList);

        JSONObject jsonObject=JsonUtils.getJsonObject("success");

        jsonObject.put("result",jsonArray);

        return jsonObject.toString();
    }

    @Override
    public String getTypeCommodity(JSONObject type) {

        String ty=type.getString("key");

        System.out.println("获取类型为"+ty+"的商品记录");

        List<Commodity> commodityList=Anima.select().from(Commodity.class).where("commodity_type",ty).all();

        JSONArray jsonArray=new JSONArray(commodityList);

        JSONObject jsonObject=JsonUtils.getJsonObject("success");

        jsonObject.put("result",jsonArray);

        return jsonObject.toString();
    }


}
