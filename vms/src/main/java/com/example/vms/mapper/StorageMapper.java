package com.example.vms.mapper;

import com.example.vms.entity.Storage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.Mapping;

/**
* @author Chen
* @description 针对表【storage】的数据库操作Mapper
* @createDate 2025-07-23 08:51:11
* @Entity com.example.vms.entity.Storage
*/

@Mapper
public interface StorageMapper extends BaseMapper<Storage> {

}




