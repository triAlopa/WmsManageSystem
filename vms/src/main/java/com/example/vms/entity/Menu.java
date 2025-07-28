package com.example.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName menu
 */
@TableName(value ="menu")
@Data
public class Menu {
    private Integer id;

    private String menucode;

    private String menuname;

    private String menulevel;

    private String menuparentcode;

    private String menuclick;

    private String menuright;

    private String menucomponent;

    private String menuicon;
}