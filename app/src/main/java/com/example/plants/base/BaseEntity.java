package com.example.plants.base;

import java.io.Serializable;

/**
 * @Description :
 * @Author MIKE-MILK
 * @Date 11/26/20 5:36 PM
 */

public class BaseEntity<E> implements Serializable {

    private int code;
    private E data;
    private String message;

    public E getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
