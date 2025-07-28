package com.example.vms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vms.entity.Record;
import com.example.vms.service.RecordService;
import com.example.vms.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;

/**
* @author Chen
* @description 针对表【record】的数据库操作Service实现
* @createDate 2025-07-25 15:17:40
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public IPage pageC(IPage<Record> recordIPage, QueryWrapper queryWrapper) {
       return recordMapper.pageC(recordIPage,queryWrapper);
    }
}




