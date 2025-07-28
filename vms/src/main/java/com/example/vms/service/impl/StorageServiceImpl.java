package com.example.vms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vms.entity.Storage;
import com.example.vms.service.StorageService;
import com.example.vms.mapper.StorageMapper;
import org.springframework.stereotype.Service;

/**
* @author Chen
* @description 针对表【storage】的数据库操作Service实现
* @createDate 2025-07-23 08:51:11
*/
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage>
    implements StorageService{

}




