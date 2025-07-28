package com.example.vms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vms.common.Result;
import com.example.vms.common.queryPageParam;
import com.example.vms.entity.Goodstype;
import com.example.vms.entity.Storage;
import com.example.vms.service.GoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/goodstype")
public class GoodsTypeController {
    @Autowired
    private GoodstypeService goodstypeService;
    //增加用户信息的接口

    @PostMapping("/save")
    public Result save(@RequestBody Goodstype goodstype) {
        // 调用userService的save方法保存用户信息
//        return userService.save(user);
//        if(userService.save(user)) return new Result(200, "success",1,user);
        return goodstypeService.save(goodstype) ? Result.success() : Result.fail();
    }

    @PostMapping("/update")
    private Result update(@RequestBody Goodstype goodstype) {
        return goodstypeService.updateById(goodstype) ? Result.success() : Result.fail();
    }

    // 根据编号查询用户信息
    @GetMapping("/findById")
    public Result findById(@RequestParam String id) {
        // 根据编号查询用户信息
        List list = goodstypeService.lambdaQuery().eq(Goodstype::getId,id).list();
        // 如果查询结果不为空，则返回成功，否则返回失败
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return goodstypeService.removeById(id) ? Result.success() : Result.fail();
        //return userService.removeById(id);
    }

    @PostMapping("/listPage")
// 注解：指定HTTP POST请求的URL路径为"/listPage"
    public Result listPage(@RequestBody queryPageParam query) {

        // 创建一个Page对象，用于分页查询
        Page<Goodstype> goodstypePage = new Page<>();
        // 设置每页显示的记录数，从查询参数中获取
        goodstypePage.setSize(query.getPageSize());
        // 设置当前页码，从查询参数中获取
        goodstypePage.setCurrent(query.getPageNum());

        // 从查询参数中获取参数集合
        HashMap param = query.getParam();
        // 从参数集合中获取用户名
        String name = (String) param.get("name");

        // 创建一个LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<Goodstype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            // 添加查询条件，模糊查询性别
            lambdaQueryWrapper.like(Goodstype::getName, name);
        }
        // 执行分页查询，返回分页结果
        IPage<Goodstype> result = goodstypeService.page(goodstypePage,lambdaQueryWrapper);


        // 返回查询结果中的记录列表
        return Result.success(result.getTotal(), result.getRecords());
    }
    @GetMapping("/list")
    public Result list() {
        // 根据编号查询用户信息
        List list = goodstypeService.list();
        // 如果查询结果不为空，则返回成功，否则返回失败
        return  Result.success(list);
    }
}
