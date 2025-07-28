package com.example.vms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vms.entity.Menu;
import com.example.vms.service.MenuService;
import com.example.vms.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author Chen
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2025-07-22 15:20:04
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




