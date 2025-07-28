package com.example.vms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.sound.sampled.LineEvent;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User {
    @TableId(value = "id",type=IdType.AUTO)
    private Integer id;

    private String no;

    private String name;

    private String password;

    private Integer age;

    private Integer sex;

    private String phone;

    private Integer roleId;
    @TableField("isvalid")
    private String isValid;
}