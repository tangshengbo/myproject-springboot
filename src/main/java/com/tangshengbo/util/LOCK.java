package com.tangshengbo.util;

/**
 * Created by TangShengBo on 2017-07-25.
 */
public class Lock {

    private String key;

    private String value;

    public Lock(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
