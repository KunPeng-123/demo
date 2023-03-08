package com.example.demo.mapper;

import com.example.demo.entry.UserOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserOrderMapper {

    ArrayList<UserOrder> selectAllUserOrder(Integer userId);

    ArrayList<UserOrder> selectSuccessUserOrder(Integer userId);

    int updateUserOrderSuccess(Integer id);

    int updateUserOrderRefund(Integer id);

    int deleteUserOrderToId(Integer id);

    int insertUserOrder(UserOrder userOrder);
}
