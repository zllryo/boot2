package com.ryo.boot2.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ryo.boot2.controller.aop.OperateLog;
import com.ryo.boot2.service.common.MongoLogDao;
import com.ryo.boot2.model.Girls;
import com.ryo.boot2.model.User;
import com.ryo.boot2.model.common.CommonConfig;
import com.ryo.boot2.model.common.MongoLog;
import com.ryo.boot2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@ApiIgnore
@Controller
@EnableConfigurationProperties({CommonConfig.class})
public class HelloController {

    @Resource
    private UserService userService;

    @Autowired
    private MongoLogDao mongoLogDao;

    @Autowired
    private CommonConfig commonConfig;

    private  final Logger logger=LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/default")
    public  String index(ModelMap map)
    {
        map.addAttribute("host",commonConfig.getAddress()+""+commonConfig.getSize());
        return "default";
    }

    /**
    *
     * (用了thymeleaf组件，默认地址变为：templates；需要重定向)
     * */
    @RequestMapping("/")
    public  String indexs()
    {
        return "redirect:index.html";
    }


    @RequestMapping("list")
    @ResponseBody
    public List<User> selectAll()
    {
        return userService.selectAll();
    }

    @RequestMapping("listPage")
    @ResponseBody
    public  List<User> selectAllPage()
    {
        PageHelper.startPage(1,2);
        List<User> list=userService.selectAll();
        PageInfo<User> pageInfo=new PageInfo<User>(list);
        return  list;
    }

    @RequestMapping(value = "insert")
    @ResponseBody
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
