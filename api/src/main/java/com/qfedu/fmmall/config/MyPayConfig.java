package com.qfedu.fmmall.config;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.InputStream;

public class MyPayConfig implements WXPayConfig {
    @Override
    public String getAppID() {
        return "wx632c8f211f8122c6";//商户号
    }

    @Override
    public String getMchID() {//商户编号
        return "1497984412";
    }

    @Override
    public String getKey() {//商户key
        return "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 0;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 0;
    }
}
