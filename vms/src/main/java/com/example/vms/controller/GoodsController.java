package com.example.vms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vms.common.Result;
import com.example.vms.common.queryPageParam;
import com.example.vms.entity.Goods;
import com.example.vms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController 
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;


    //增加用户信息的接口
    @PostMapping("/save")
    public Result save(@RequestBody Goods Goods) {
        // 调用userService的save方法保存用户信息
//        return userService.save(user);
//        if(userService.save(user)) return new Result(200, "success",1,user);
        return goodsService.save(Goods) ? Result.success() : Result.fail();
    }

    @PostMapping("/update")
    private Result update(@RequestBody Goods Goods) {
        return goodsService.updateById(Goods) ? Result.success() : Result.fail();
    }

    // 根据编号查询用户信息
    @GetMapping("/findById")
    public Result findById(@RequestParam String id) {
        // 根据编号查询用户信息
        List list = goodsService.lambdaQuery().eq(Goods::getId,id).list();
        // 如果查询结果不为空，则返回成功，否则返回失败
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return goodsService.removeById(id) ? Result.success() : Result.fail();
        //return userService.removeById(id);
    }

    @PostMapping("/listPage")
// 注解：指定HTTP POST请求的URL路径为"/listPage"
    public Result listPage(@RequestBody queryPageParam query) {

        // 创建一个Page对象，用于分页查询
        Page<Goods> GoodsPage = new Page<>();
        // 设置每页显示的记录数，从查询参数中获取
        GoodsPage.setSize(query.getPageSize());
        // 设置当前页码，从查询参数中获取
        GoodsPage.setCurrent(query.getPageNum());

        // 从查询参数中获取参数集合
        HashMap param = query.getParam();
        // 从参数集合中获取用户名
        String name = (String) param.get("name");
        String storage = (String) param.get("storage");
        String goodstype = (String) param.get("goodsType");

        // 创建一个LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            // 添加查询条件，模糊查询性别
            lambdaQueryWrapper.like(Goods::getName, name);
        }

        if (StringUtils.isNotBlank(storage)) {
            // 添加查询条件，模糊查询性别
            lambdaQueryWrapper.eq(Goods::getStorage, storage);
        }
        if (StringUtils.isNotBlank(goodstype)) {
            // 添加查询条件，模糊查询性别
            lambdaQueryWrapper.eq(Goods::getGoodstype, goodstype);
        }
        // 执行分页查询，返回分页结果
        IPage<Goods> result = goodsService.page(GoodsPage,lambdaQueryWrapper);


        // 返回查询结果中的记录列表
        return Result.success(result.getTotal(), result.getRecords());
    }
}
