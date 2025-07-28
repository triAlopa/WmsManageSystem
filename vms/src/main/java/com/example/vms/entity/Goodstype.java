package com.example.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName goodstype
 */
@TableName(value ="goodstype")
@Data
public class Goodstype {
    private Integer id;

    private String name;

    private String remark;
}