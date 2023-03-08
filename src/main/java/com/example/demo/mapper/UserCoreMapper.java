package com.example.demo.mapper;

import com.example.demo.entry.UserCore;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserCoreMapper {

    ArrayList<UserCore> selectUserCore();

    Integer selectIdToUsername(String username);
}
