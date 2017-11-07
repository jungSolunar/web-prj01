package com.atto.server.model.ft;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by dhjung on 2017. 11. 7..
 */
public class Order {
    private String orderSid;
    private String storeSid;
    private String userSid;
    private String payment;
    private Boolean pay;
    private DateTime orderDTM;
    private Boolean complete;
    private List<OrderMenu> menus;

    public Order(){

    }

    public Order(String storeSid, String orderSid){
        this.storeSid = storeSid;
        this.orderSid = orderSid;
    }

    public String getOrderSid() {
        return orderSid;
    }

    public void setOrderSid(String orderSid) {
        this.orderSid = orderSid;
    }

    public String getStoreSid() {
        return storeSid;
    }

    public void setStoreSid(String storeSid) {
        this.storeSid = storeSid;
    }

    public String getUserSid() {
        return userSid;
    }

    public void setUserSid(String userSid) {
        this.userSid = userSid;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    public DateTime getOrderDTM() {
        return orderDTM;
    }

    public void setOrderDTM(DateTime orderDTM) {
        this.orderDTM = orderDTM;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public List<OrderMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<OrderMenu> menus) {
        this.menus = menus;
    }
}
