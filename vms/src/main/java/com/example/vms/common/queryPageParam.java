package com.example.vms.common;

import lombok.Data;

import java.util.HashMap;



@Data // 使用Lombok注解，自动生成getter、setter、toString、equals和hashCode方法
public class queryPageParam { // 定义一个名为queryPageParam的公共类
    private static int PAGE_SIZE=20; // 定义一个静态常量PAGE_SIZE，表示默认每页显示的记录数，初始值为20
    private static int PAGE_NUM=1; // 定义一个静态常量PAGE_NUM，表示默认的页码，初始值为1

    private int pageSize=PAGE_SIZE; // 定义一个实例变量pageSize，表示每页显示的记录数，默认值为静态常量PAGE_SIZE
    private int pageNum=PAGE_NUM; // 定义一个实例变量pageNum，表示当前页码，默认值为静态常量PAGE_NUM

     private HashMap param=new HashMap(); // 定义一个HashMap类型的实例变量param，用于存储查询参数


}
