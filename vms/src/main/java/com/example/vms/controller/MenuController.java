package com.example.vms.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vms.common.Result;
import com.example.vms.entity.Menu;
import com.example.vms.entity.User;
import com.example.vms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController  {

@Autowired
private MenuService menuService;

    // 根据编号查询用户信息
    @GetMapping("/list")
    public Result list(@RequestParam String roleId) {
        // 根据编号查询用户信息
        List list = menuService.lambdaQuery().like(Menu::getMenuright, roleId).list();
        // 如果查询结果不为空，则返回成功，否则返回失败
        return  Result.success(list);
    }
}
