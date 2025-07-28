package com.example.vms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vms.entity.User;
import com.example.vms.service.UserService;
import com.example.vms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Chen
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-05-06 12:46:01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserService userService;

    @Override
    public IPage<User> PageC(Page<User> page) {

        return userService.PageC(page);
    }
}




