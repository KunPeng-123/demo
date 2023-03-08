package com.example.demo.mapper;

import com.example.demo.entry.UserWallet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserWalletMapper {

    UserWallet selectUserWalletToUserId(Integer userId);

    Long selectMoneyToUserId(Integer userId);

    int updateUserWallet(UserWallet userWallet);

    int deleteUserWallet(Integer userId);

    int insertUserWallet(UserWallet userWallet);
}
