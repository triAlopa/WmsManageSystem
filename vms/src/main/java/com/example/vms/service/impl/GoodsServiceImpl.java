package com.example.vms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vms.entity.Goods;
import com.example.vms.service.GoodsService;
import com.example.vms.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author Chen
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2025-07-24 16:00:53
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




