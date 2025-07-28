package com.example.vms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vms.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Chen
* @description 针对表【user】的数据库操作Service
* @createDate 2025-05-06 12:46:01
*/
public interface UserService extends IService<User> {

// 定义一个接口方法 PageC，用于处理分页查询用户信息
// IPage<User> 表示返回一个分页结果对象，其中包含 User 类型的数据
// Page<User> page 表示传入一个分页参数对象，其中包含 User 类型的数据
    IPage<User> PageC(Page<User> page);
}
