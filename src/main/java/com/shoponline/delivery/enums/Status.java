package com.shoponline.delivery.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    SHIPPED("Shipped"),
    IN_TRANSIT("In-Transit"),
    OUT_FOR_DELIVERY("Out for delivery"),
    FAILED_DELIVERY_ATTEMPT("Failed delivery attempt");

    private String name;
}
