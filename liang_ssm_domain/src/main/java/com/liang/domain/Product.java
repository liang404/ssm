package com.liang.domain;

import com.liang.utils.DateUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

/**
 * @author liang
 * @create 2020/2/24 16:17
 * 商品信息实体类
 */
public class Product implements Serializable {

    private String id;//主键
    private String productNum;//编号
    private String productName;//名称
    private String cityName;//出发城市
    private Date DepartureTime;//出发时间
    private String DepartureTimeStr;
    private Double productPrice;//产品价格
    private String productDesc;//产品描述
    private Integer productStatus;//状态：0关闭，1开启
    private String productStatusStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() throws ParseException {
        if (DepartureTimeStr != null){
            DepartureTime = DateUtils.string2Date(DepartureTime,"yyyy-MM-dd HH:mm:ss");
        }
        return DepartureTime;
    }

    public void setDepartureTime(Date departureTime) {
        DepartureTime = departureTime;
    }

    public String getDepartureTimeStr() {
        if (DepartureTime != null){
            DepartureTimeStr = DateUtils.date2String(DepartureTime,"yyyy-MM-dd HH:mm:ss");
        }
        return DepartureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        DepartureTimeStr = departureTimeStr;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if (productStatus != null){
            //状态：0关闭，1开启
            if (productStatus==0){
                productStatusStr="关闭";
            }
            if (productStatus==1){
                productStatusStr="开启";
            }
        }
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", DepartureTime=" + DepartureTime +
                ", DepartureTimeStr='" + DepartureTimeStr + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                '}';
    }
}
