package com.example.plants.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :
 * @Author MIKE-MILK
 * @Date 11/26/20 5:20 PM
 */

public class Search  implements Serializable {

    private int result;
    private Response response;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    class Response {

        private List<Identify_results> identify_results;

        class  Identify_results{
            private String reference_url;
            private String name;
            private String desc;
            private double probability;
            private String detail_url;

        }
    }
}
