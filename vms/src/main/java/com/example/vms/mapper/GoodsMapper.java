package com.example.vms.mapper;

import com.example.vms.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Chen
* @description 针对表【goods】的数据库操作Mapper
* @createDate 2025-07-24 16:00:53
* @Entity com.example.vms.entity.Goods
*/

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}




