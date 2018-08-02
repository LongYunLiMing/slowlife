package com.slowlife.service.interfaces;

import org.json.JSONObject;

public interface IndexInterface {

    String indexInfo();

    String search(JSONObject keyword);

    String getTypeCommodity(JSONObject type);

}
