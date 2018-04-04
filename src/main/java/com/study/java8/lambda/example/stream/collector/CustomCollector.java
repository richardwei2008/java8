package com.study.java8.lambda.example.stream.collector;

import com.study.java8.lambda.example.stream.collector.collectors.ClaimProductTypeCollector;
import com.study.java8.lambda.example.stream.collector.model.Claim;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Date: Created in 上午11:27 18/3/24
 * @Modified: by 
 */
public class CustomCollector {

    public static void main(String[] args) {
        Set<Claim> claims = new HashSet<>();
        claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
        claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));
        claims.add(new Claim(Claim.PRODUCT_TYPE.MOTOR));

        claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));
        claims.add(new Claim(Claim.PRODUCT_TYPE.HOUSEHOLD));

        ClaimProductTypeCollector<Claim> claimProductTypeCollector = new ClaimProductTypeCollector();
        claimProductTypeCollector.getRequiredTypes().add(Claim.PRODUCT_TYPE.MOTOR);
        claimProductTypeCollector.getRequiredTypes().add(Claim.PRODUCT_TYPE.HOUSEHOLD);
        Map oneClaimPerProductType = claims.stream().collect(claimProductTypeCollector);

        System.out.println(oneClaimPerProductType);
    }
}
