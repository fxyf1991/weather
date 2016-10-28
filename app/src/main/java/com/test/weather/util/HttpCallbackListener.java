package com.test.weather.util;

/**
 * Created by LT on 2016/10/20.
 */

public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
