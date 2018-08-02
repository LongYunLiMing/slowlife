package com.slowlife.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.slowlife.service.interfaces.IndexInterface;
import org.json.JSONObject;

@Path
public class IndexController {

    @Inject
    private IndexInterface service;

    @GetRoute("/v1/slowlife/index")
    @JSON
    public String indexInfo(){


        return service.indexInfo();
    }

    @GetRoute("/v1/slowlife/search-by-keyword")
    @JSON
    public String search(@Param String keyword){

        System.out.println("关键字"+keyword);

        JSONObject keywordJSON=new JSONObject(keyword);

        return service.search(keywordJSON);

    }

    @GetRoute("/v1/slowlife/type-commodity")
    @JSON
    public String getTypeCommodity(@Param String type){

        System.out.println("类型"+type);

        JSONObject typeJSON=new JSONObject(type);

        return service.getTypeCommodity(typeJSON);

    }

    @GetRoute("/index")
    public String index(){


        return "index.html";
    }


    @GetRoute("/sss")
    public String ss(){

        return "sss.html";
    }

}
