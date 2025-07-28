package com.example.vms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vms.common.Result;
import com.example.vms.common.queryPageParam;
import com.example.vms.entity.Goods;
import com.example.vms.entity.Record;
import com.example.vms.entity.User;
import com.example.vms.mapper.RecordMapper;
import com.example.vms.service.GoodsService;
import com.example.vms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @Autowired
    private GoodsService goodsService;
    //增加用户信息的接口

    @PostMapping("/save")
    public Result save(@RequestBody Record record) {
        Goods goods = goodsService.getById(record.getGoods());
        goods.setCount(record.getCount() + goods.getCount());
        goodsService.updateById(goods);

//        LambdaQueryWrapper<Record> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//
//        Record entityRecord = lambdaQueryWrapper.eq(Record::getGoods, record.getGoods()).getEntity();
//
//
//        if(entityRecord!=null){
//            System.err.println("更新记录操作");
//            record.setCount(record.getCount()+goods.getCount());
//            return recordService.updateById(record) ? Result.success() : Result.fail();
//        }
        return recordService.save(record) ? Result.success() : Result.fail();
    }

    @PostMapping("/update")
    private Result update(@RequestBody Record record) {
        return recordService.updateById(record) ? Result.success() : Result.fail();
    }

    // 根据编号查询用户信息
    @GetMapping("/findById")
    public Result findById(@RequestParam String id) {
        // 根据编号查询用户信息
        List list = recordService.lambdaQuery().eq(Record::getId, id).list();
        // 如果查询结果不为空，则返回成功，否则返回失败
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return recordService.removeById(id) ? Result.success() : Result.fail();
        //return userService.removeById(id);
    }

    @PostMapping("/listPage")
// 注解：指定HTTP POST请求的URL路径为"/listPage"
    public Result listPage(@RequestBody queryPageParam query) {

        // 创建一个Page对象，用于分页查询
        Page<Record> recordPage = new Page<>();
        // 设置每页显示的记录数，从查询参数中获取
        recordPage.setSize(query.getPageSize());
        // 设置当前页码，从查询参数中获取
        recordPage.setCurrent(query.getPageNum());

        // 从查询参数中获取参数集合
        HashMap param = query.getParam();
        // 从参数集合中获取用户名
        String name = (String) param.get("name");

        // 创建一个LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<Record> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            // 添加查询条件，模糊查询性别
            lambdaQueryWrapper.like(Record::getUserid, name);
        }
        // 执行分页查询，返回分页结果
        IPage<Record> result = recordService.page(recordPage, lambdaQueryWrapper);


        // 返回查询结果中的记录列表
        return Result.success(result.getTotal(), result.getRecords());
    }

    @GetMapping("/list")
    public Result list() {
        // 根据编号查询用户信息
        List list = recordService.list();
        // 如果查询结果不为空，则返回成功，否则返回失败
        return Result.success(list);
    }

    @PostMapping("/listPageC")
    // 定义一个公共方法listPageC，返回一个User对象的列表
    public Result listPageC(@RequestBody queryPageParam query) {
        // 创建一个Page对象，用于分页查询
        Page<Record> recordPage = new Page<>();
        // 设置每页显示的记录数，从query对象中获取pageSize
        recordPage.setSize(query.getPageSize());
        // 设置当前页码，从query对象中获取pageNum
        recordPage.setCurrent(query.getPageNum());
        // 从查询参数中获取参数集合
        HashMap param = query.getParam();
        // 从参数集合中获取用户名
        String name = (String) param.get("name");
        String storage = (String) param.get("storage");
        String goodstype = (String) param.get("goodsType");
        Integer userId = (Integer) param.get("userId");
        Integer roleId = (Integer) param.get("roleId");

        // 创建一个LambdaQueryWrapper对象，用于构建查询条件
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply(" g.id=r.goods AND g.storage=s.id AND g.goodsType=gt.id ");
        if (StringUtils.isNotBlank(name)) {
            // 添加查询条件，模糊查询性别
            queryWrapper.like("g.name", name);
        }
        if (StringUtils.isNotBlank(storage)) {
            // 添加查询条件，模糊查询性别
            queryWrapper.eq("s.id", storage);
        }
        if (StringUtils.isNotBlank(goodstype)) {
            // 添加查询条件，模糊查询性别
            queryWrapper.eq("gt.id", goodstype);
        }
        if (roleId==2) {
            queryWrapper.like("r.userId", userId);
        }

         queryWrapper.orderByDesc("createtime");

        IPage result = recordService.pageC(recordPage, queryWrapper);
        // 返回查询结果中的记录列表
        return Result.success(result.getTotal(), result.getRecords());
    }
}
