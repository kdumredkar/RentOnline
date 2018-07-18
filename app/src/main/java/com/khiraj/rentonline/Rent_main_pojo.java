package com.khiraj.rentonline;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rent_main_pojo {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Data_pojo> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data_pojo> getData() {
        return data;
    }

    public void setData(List<Data_pojo> data) {
        this.data = data;
    }

}


