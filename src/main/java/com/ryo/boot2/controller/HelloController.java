package com.ryo.boot2.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryo.boot2.model.Girls;
import com.ryo.boot2.model.User;
import com.ryo.boot2.model.common.CommonConfig;
import com.ryo.boot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@ApiIgnore
@RestController
@EnableConfigurationProperties({CommonConfig.class})
public class HelloController {

    @Resource
    private UserService userService;

    @Autowired
    private CommonConfig commonConfig;

    @RequestMapping("/")
    public  String index()
    {
        return "hello"+commonConfig.getAddress()+""+commonConfig.getSize();
    }

    @RequestMapping("list")
    public List<User> selectAll()
    {
        return userService.selectAll();
    }

    @RequestMapping("listPage")
    public  List<User> selectAllPage()
    {
        PageHelper.startPage(1,2);
        List<User> list=userService.selectAll();
        PageInfo<User> pageInfo=new PageInfo<User>(list);
        return  list;
    }

    @RequestMapping(value = "insert")
    public int InsertAll()
    {

        Girls girls=new Girls();
        girls.setId(100);
        girls.setName("啊飒");
        girls.setAge(34);

        User user=new User();
        user.setId(100);
        user.setUsername("啊飒飒的啊飒飒的啊飒飒的啊飒飒的啊飒飒的啊飒飒的");
        user.setPassword("1");

        return   userService.insertAll(girls,user);
    }
}
