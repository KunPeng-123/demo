package com.example.demo.controller;

import com.example.demo.entry.UserOrder;
import com.example.demo.service.impl.UserWalletAndOrderServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/WalletOrOrder")
public class UserWalletAndOrderController {

    UserWalletAndOrderServiceImpl userWalletAndOrderService;

    public UserWalletAndOrderController(UserWalletAndOrderServiceImpl userWalletAndOrderService) {
        this.userWalletAndOrderService = userWalletAndOrderService;
    }

    @GetMapping("/queryWalletMoney/{userId}")
    public Long queryWalletMoney(@PathVariable("userId") Integer userId) {
        return userWalletAndOrderService.queryUserWalletMoney(userId);
    }

    @GetMapping("/queryAllOrder/{userId}")
    public ArrayList<UserOrder> queryAllUserOrder(@PathVariable("userId") Integer userId) {
        return userWalletAndOrderService.queryAllUserOrder(userId);
    }

    @GetMapping("/querySuccessOrder/{userId}")
    public ArrayList<UserOrder> querySuccessOrder(@PathVariable("userId") Integer userId) {
        return userWalletAndOrderService.querySuccessOrder(userId);
    }

    @PostMapping("/createOrder")
    public void createOrder(Integer userId, Long money, String details) {
        userWalletAndOrderService.createOrder(userId, money, details);
    }

    @PutMapping("/completeOrder")
    public void completeOrder(@RequestBody UserOrder userOrder) {
        userWalletAndOrderService.completeOrder(userOrder);
    }

    @PutMapping("/refundOrder")
    public void refundOrder(@RequestBody UserOrder userOrder) {
        userWalletAndOrderService.refundOrder(userOrder);
    }
}
