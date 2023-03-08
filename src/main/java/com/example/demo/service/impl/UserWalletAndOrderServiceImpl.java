package com.example.demo.service.impl;

import com.example.demo.entry.UserOrder;
import com.example.demo.entry.UserWallet;
import com.example.demo.mapper.UserOrderMapper;
import com.example.demo.mapper.UserWalletMapper;
import com.example.demo.service.UserOrderService;
import com.example.demo.service.UserWalletService;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.ArrayList;

@Service
public class UserWalletAndOrderServiceImpl implements UserWalletService, UserOrderService {

    private final UserWalletMapper userWalletMapper;

    private final UserOrderMapper userOrderMapper;

    private final DataSourceTransactionManager dataSourceTransactionManager;

    private final TransactionDefinition transactionDefinition;

    public UserWalletAndOrderServiceImpl(UserWalletMapper userWalletMapper,
                                         UserOrderMapper userOrderMapper,
                                         DataSourceTransactionManager dataSourceTransactionManager,
                                         TransactionDefinition transactionDefinition) {
        this.userWalletMapper = userWalletMapper;
        this.userOrderMapper = userOrderMapper;
        this.dataSourceTransactionManager = dataSourceTransactionManager;
        this.transactionDefinition = transactionDefinition;
    }

    @Override
    public Long queryUserWalletMoney(Integer userId) {
        return userWalletMapper.selectMoneyToUserId(userId);
    }

    @Override
    public ArrayList<UserOrder> queryAllUserOrder(Integer userId) {
        return userOrderMapper.selectAllUserOrder(userId);
    }

    @Override
    public ArrayList<UserOrder> querySuccessOrder(Integer userId) {
        return userOrderMapper.selectSuccessUserOrder(userId);
    }

    @Override
    public void createOrder(Integer userId, Long money, String details) {
        int orderCreateResult = userOrderMapper.insertUserOrder(
                new UserOrder(userId, money, details));
        if (orderCreateResult != 1) {
            throw new RuntimeException("订单创建失败！");
        }
    }

    @Override
    public void completeOrder(UserOrder userOrder) {
        if (userOrder.isSuccess()) {
            return;
        }
        Long money = userWalletMapper.selectMoneyToUserId(
                userOrder.getUserId());
        if (money + userOrder.getMoney() < 0) {
            throw new RuntimeException("余额不足，订单支付失败！");
        }
        TransactionStatus transaction = dataSourceTransactionManager.
                getTransaction(transactionDefinition);
        try {
            int paymentResult = userWalletMapper.updateUserWallet(
                    new UserWallet(userOrder.getUserId(), money + userOrder.getMoney()));
            int orderUpdateResult = userOrderMapper.updateUserOrderSuccess(
                    userOrder.getId());
            if (paymentResult != 1 || orderUpdateResult != 1) {
                throw new RuntimeException();
            }
            dataSourceTransactionManager.commit(transaction);
        } catch (RuntimeException ex) {
            dataSourceTransactionManager.rollback(transaction);
            throw new RuntimeException("订单支付失败！");
        }
    }

    @Override
    public void refundOrder(UserOrder userOrder) {
        if (!userOrder.isSuccess() || userOrder.isRefund()) {
            return;
        }
        Long money = userWalletMapper.selectMoneyToUserId(
                userOrder.getUserId());
        if (money - userOrder.getMoney() <= 0) {
            throw new RuntimeException("余额不足，订单退款失败！");
        }
        TransactionStatus transaction = dataSourceTransactionManager.
                getTransaction(transactionDefinition);
        try {
            int orderUpdateResult = userOrderMapper.updateUserOrderRefund(
                    userOrder.getId());
            int paymentResult = userWalletMapper.updateUserWallet(
                    new UserWallet(userOrder.getUserId(), money - userOrder.getMoney()));
            if (orderUpdateResult != 1 || paymentResult != 1) {
                throw new RuntimeException();
            }
            dataSourceTransactionManager.commit(transaction);
        } catch (RuntimeException ex) {
            dataSourceTransactionManager.rollback(transaction);
            throw new RuntimeException("订单退款失败！");
        }
    }
}
