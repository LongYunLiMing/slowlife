package com.slowlife.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.JSON;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.slowlife.service.interfaces.UserLoginAndRegisterInterface;
import org.json.JSONObject;

@Path
public class UserLoginAndRegisterController {

    @Inject
    private UserLoginAndRegisterInterface service;

    @GetRoute("/v1/test")
    @JSON
    public String test(Response response){

        return "这是来自后端的信息";
    }

    @GetRoute("/v1/user-login")
    @JSON
    public String login(@Param String loginData){



        JSONObject jsonObject=new JSONObject(loginData);

        return service.userLogin(jsonObject);

    }


    @GetRoute("/v1/user-register")
    @JSON
    public String register(@Param String registerData){

        System.out.println("账号和密码"+registerData);

        JSONObject jsonObject=new JSONObject(registerData);


        return service.userRegister(jsonObject);

    }


}
