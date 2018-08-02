package com.slowlife.service.interfaces;

import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import com.slowlife.bean.User;
import org.json.JSONObject;

public interface UserLoginAndRegisterInterface {

    String userLogin( JSONObject user);

    String userRegister(JSONObject user);

}
