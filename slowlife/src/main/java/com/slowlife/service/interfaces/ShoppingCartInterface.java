package com.slowlife.service.interfaces;

import com.blade.mvc.http.Request;
import org.json.JSONObject;

public interface ShoppingCartInterface {

    //String getUserCartInfo(JSONObject data);
    String getUserCartInfo(String data);

    String setSCadd(String SCadd);

}
