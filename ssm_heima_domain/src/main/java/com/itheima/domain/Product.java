package com.itheima.domain;

import com.itheima.utils.dateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Product {
    private String id;   //主键
    private String productNum;    //编码  唯一
    private String productName;   //名称
    private String cityName;      //出发城市
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;   //出发时间
    private String departureTimeStr;
    private double productPrice;  //产品价格
    private String productDesc;   //产品描述
    private Integer productStatus; //状态 0关闭 1开启
    private String productStatusStr;

    public String getDepartureTimeStr() {
        if(departureTime != null){
            departureTimeStr = dateUtils.date2String(departureTime, "yyyy-MM-dd hh:mm:ss");
        }
        return departureTimeStr;
    }

    public String getProductStatusStr() {
        if(productStatus != null){
            if(productStatus == 0){
                productStatusStr = "关闭";
            }else{
                productStatusStr = "开启";
            }
        }
        return productStatusStr;
    }
}
