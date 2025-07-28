package com.example.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer storage;

    private Integer goodstype;

    private Integer count;

    private String remark;
}