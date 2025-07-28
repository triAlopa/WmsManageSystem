package com.example.vms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.vms.common.Result;
import com.example.vms.common.queryPageParam;
import com.example.vms.entity.Menu;
import com.example.vms.entity.User;
import com.example.vms.mapper.UserMapper;
import com.example.vms.service.MenuService;
import com.example.vms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController()
@RequestMapping("/user")
public class userController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;

    //查询全部用户
// 使用@GetMapping注解来映射HTTP GET请求到该方法上，URL路径为"/list"
    @GetMapping("/list")
// 定义一个公共方法selectAll，用于查询所有用户信息
    public List<User> selectAll() {
        // 调用userService的list方法，获取所有用户的列表
        // userService是一个服务层对象，负责处理业务逻辑
        return userService.list();
    }

    //增加用户信息的接口
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        // 调用userService的save方法保存用户信息
//        return userService.save(user);
//        if(userService.save(user)) return new Result(200, "success",1,user);
        return userService.save(user) ? Result.success() : Result.fail();
    }

    //修改用户信息的方法
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user) {
        // 调用userService的updateById方法，根据传入的用户对象更新数据库中的用户信息
        // @RequestBody注解表示请求体中的JSON数据会自动绑定到User对象上
        // 返回值为布尔类型，表示更新操作是否成功
        return userService.updateById(user);
    }

    //增加修改
// 该注释表明这个方法用于保存或修改用户信息
// 使用@PostMapping注解来映射HTTP POST请求到该方法
// "/saveOrMod"是请求的URL路径
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody User user) {
        // @RequestBody注解表示请求体中的JSON数据将自动绑定到User对象
        // User user是方法参数，表示传入的用户信息
        // 调用userService的saveOrUpdate方法来保存或更新用户信息
        // saveOrUpdate方法返回一个布尔值，表示操作是否成功
        return userService.saveOrUpdate(user);
    }

    //更新
    @PostMapping("/update")
    private Result update(@RequestBody User user) {
        return userService.updateById(user) ? Result.success() : Result.fail();
    }

    // 根据编号查询用户信息
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no) {
        // 根据编号查询用户信息
        List list = userService.lambdaQuery().eq(User::getNo, no).list();
        // 如果查询结果不为空，则返回成功，否则返回失败
        return list.size() > 0 ? Result.success(list) : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        return userService.removeById(id) ? Result.success() : Result.fail();
        //return userService.removeById(id);
    }

    //校验用户 登录验证
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List list = userService.lambdaQuery()
                .eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword()).list();
        if(list.size()>0){
            User user1 = (User)list.get(0);
            List menuList = menuService.lambdaQuery().like(Menu::getMenuright, user1.getRoleId()).list();
            HashMap res=new HashMap<>();
            res.put("user",user1);
            res.put("menu",menuList);
            return  Result.success(res);
        }

        return  Result.fail();
    }

    //查询(模糊、匹配)
    @PostMapping("/listP")
    public Result listP(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryChainWrapper = new LambdaQueryWrapper();
        if (StringUtils.isNotBlank(user.getName())) {
            lambdaQueryChainWrapper.like(User::getName, user.getName());
        }

        //return userService.list(lambdaQueryChainWrapper);
        return Result.success(userService.list(lambdaQueryChainWrapper));

    }

    @PostMapping("/listPage")
// 注解：指定HTTP POST请求的URL路径为"/listPage"
    public Result listPage(@RequestBody queryPageParam query) {

        // 创建一个Page对象，用于分页查询
        Page<User> userPage = new Page<>();
        // 设置每页显示的记录数，从查询参数中获取
        userPage.setSize(query.getPageSize());
        // 设置当前页码，从查询参数中获取
        userPage.setCurrent(query.getPageNum());

        // 从查询参数中获取参数集合
        HashMap param = query.getParam();
        // 从参数集合中获取用户名
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        String roleId = (String) param.get("roleId");

        // 创建一个LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            // 添加查询条件，模糊查询用户名
            lambdaQueryWrapper.like(User::getName, name);
        }
        if (StringUtils.isNotBlank(sex)) {
            // 添加查询条件，模糊查询性别
            lambdaQueryWrapper.eq(User::getSex, sex);
        }
        if (StringUtils.isNotBlank(roleId)) {
            // 添加查询条件，模糊查询用户名
            lambdaQueryWrapper.like(User::getRoleId, roleId);
        }
        // 执行分页查询，返回分页结果
        IPage<User> result = userService.page(userPage, lambdaQueryWrapper);


        // 返回查询结果中的记录列表
        return Result.success(result.getTotal(), result.getRecords());
    }

    // 使用@PostMapping注解指定处理POST请求，并映射到"/listPageC"路径
    @PostMapping("/listPageC")
// 定义一个公共方法listPageC，返回一个User对象的列表
    public List<User> listPageC(@RequestBody queryPageParam query) {
        // 创建一个Page对象，用于分页查询
        Page<User> userPage = new Page<>();
        // 设置每页显示的记录数，从query对象中获取pageSize
        userPage.setSize(query.getPageSize());
        // 设置当前页码，从query对象中获取pageNum
        userPage.setCurrent(query.getPageNum());

        // 调用userMapper的PageC方法进行分页查询，返回一个IPage<User>对象
        IPage<User> userIPage = userMapper.PageC(userPage);

        // 返回查询结果中的记录列表
        return userIPage.getRecords();

    }


    @PostMapping("/selectAdmin")
    public List<User> selectAdmin(@RequestBody queryPageParam query) {
        Page<User> userPage = new Page<>();

        userPage.setSize(query.getPageSize());
        userPage.setCurrent(query.getPageNum());
//
//        HashMap queryParam = query.getParam();
//        Integer roleId =(Integer) queryParam.get("roleId");
//
//        LambdaQueryWrapper<User> lambdaQueryWrapper=new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.like(User::getRoleId,roleId);

        IPage<User> userIPage = userMapper.PageSelectAdmin(userPage);

        System.out.println(userIPage.getTotal());

        return userIPage.getRecords();
    }

}
