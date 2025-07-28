package com.example.vms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.vms.entity.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Wrapper;

/**
* @author Chen
* @description 针对表【record】的数据库操作Mapper
* @createDate 2025-07-25 15:17:40
* @Entity com.example.vms.entity.Record
*/

@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    IPage pageC(IPage<Record> recordIPage,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}




