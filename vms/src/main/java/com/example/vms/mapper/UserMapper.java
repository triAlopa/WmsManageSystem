package com.example.vms.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vms.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author Chen
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-05-06 12:46:01
* @Entity com.example.vms.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
// 定义一个名为 PageC 的方法，该方法接收一个 IPage<User> 类型的参数 page，并返回一个 IPage<User> 类型的结果
    IPage<User> PageC (IPage <User> page);

    IPage<User> PageCC (IPage <User> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage<User> PageSelectAdmin (IPage <User> page);
}




