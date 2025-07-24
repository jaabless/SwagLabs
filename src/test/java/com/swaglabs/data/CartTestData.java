package com.swaglabs.data;

import java.util.stream.Stream;

public class CartTestData {
    public static Stream<Object[]> cartTestData() {
        return Stream.of(
                new Object[]{"Sauce Labs Backpack", "1", true},
                new Object[]{"Sauce Labs Backpack,Sauce Labs Bike Light", "2", true},
                new Object[]{"Sauce Labs Backpack", "0", false}
        );
    }
}
