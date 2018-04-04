package com.study.java8.lambda.example.stream.collector.model;

/**
 * @Description:
 * @Date: Created in 上午11:28 18/3/24
 * @Modified: by 
 */
public class Claim {

    public enum PRODUCT_TYPE { MOTOR, HOUSEHOLD, TRAVEL}

    private PRODUCT_TYPE productType;

    public Claim(PRODUCT_TYPE productType) {
        this.productType = productType;
    }

    public PRODUCT_TYPE getProductType() {
        return productType;
    }

    public void setProductType(PRODUCT_TYPE productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "productType=" + productType +
                '}';
    }
}
