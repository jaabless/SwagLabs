package com.swaglabs.data;

import java.util.stream.Stream;

public class LoginTestData {
    public static Stream<Object[]> validLoginTestData() {
        return Stream.of(
                new Object[]{"standard_user", "secret_sauce", true, "Login successful"},
                new Object[]{"problem_user", "secret_sauce", true, "Login successful"},
                new Object[]{"performance_glitch_user", "secret_sauce", true, "Login successful"}

                );
    }

    public static Stream<Object[]> invalidLoginTestData() {
        return Stream.of(
                new Object[]{"invalid_user", "secret_sauce", false, "Epic sadface: Username and password do not match any user in this service"},
                new Object[]{"standard_user", "invalid_pass", false, "Epic sadface: Username and password do not match any user in this service"},
                new Object[]{"", "", false, "Epic sadface: Username is required"},
                new Object[]{"standard_user", "", false, "Epic sadface: Password is required"},
                new Object[]{"", "secret_sauce", false, "Epic sadface: Username is required"},
                new Object[]{"locked_out_user", "secret_sauce", false, "Epic sadface: Sorry, this user has been locked out."},
                new Object[]{"STANDARD_USER", "secret_sauce", false, "Epic sadface: Username and password do not match any user in this service"},
                new Object[]{"standard_user", "SECRET_SAUCE", false, "Epic sadface: Username and password do not match any user in this service"}
        );
    }

    public static Stream<Object[]> loggedOutTestData() {
        return Stream.of(
                new Object[]{"invalid_user", "secret_sauce", false, "Epic sadface: Username and password do not match any user in this service"},
                new Object[]{"standard_user", "invalid_pass", false, "Epic sadface: Username and password do not match any user in this service"},
                new Object[]{"", "", false, "Epic sadface: Username is required"},
                new Object[]{"standard_user", "", false, "Epic sadface: Password is required"},
                new Object[]{"", "secret_sauce", false, "Epic sadface: Username is required"},
                new Object[]{"locked_out_user", "secret_sauce", false, "Epic sadface: Sorry, this user has been locked out."},
                new Object[]{"STANDARD_USER", "secret_sauce", false, "Epic sadface: Username and password do not match any user in this service"},
                new Object[]{"standard_user", "SECRET_SAUCE", false, "Epic sadface: Username and password do not match any user in this service"}
        );
    }
}