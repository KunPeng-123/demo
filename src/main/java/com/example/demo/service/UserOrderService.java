package com.example.demo.service;

import com.example.demo.entry.UserOrder;

import java.util.ArrayList;

public interface UserOrderService {

    ArrayList<UserOrder> queryAllUserOrder(Integer userId);

    ArrayList<UserOrder> querySuccessOrder(Integer userId);

    void createOrder(Integer userId, Long money, String details);

    void completeOrder(UserOrder userOrder);

    void refundOrder(UserOrder userOrder);
}
