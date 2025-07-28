package com.example.vms.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.vms.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Wrapper;

/**
* @author Chen
* @description 针对表【record】的数据库操作Service
* @createDate 2025-07-25 15:17:40
*/
public interface RecordService extends IService<Record> {

    IPage pageC(IPage<Record> recordIPage , QueryWrapper queryWrapper);
}
