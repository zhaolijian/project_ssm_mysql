package com.itheima.domain;

import com.itheima.utils.dateUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Orders {
    private String id;
    private String orderNum;
    private Date orderTime;
    private String orderTimeStr;
    private int orderStatus;
    private String orderStatusStr;
    private int peopleCount;
//    因为这是一个旅游项目，一个订单只会有一个产品，所以不用list集合
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;

    public String getOrderTimeStr(){
        if(orderTime != null){
            orderTimeStr = dateUtils.date2String(orderTime, "yyyy-MM-dd HH:mm");
        }
        return orderTimeStr;
    }

    public String getOrderStatusStr(){
        if(orderStatus == 0){
            orderStatusStr = "未支付";
        }else if(orderStatus == 1){
            orderStatusStr = "已支付";
        }
        return orderStatusStr;
    }

    public String getPayTypeStr(){
        if(payType == 0){
            payTypeStr = "支付宝";
        }else if(payType == 1){
            payTypeStr = "微信";
        }else{
            payTypeStr = "其它";
        }
        return payTypeStr;
    }
}
