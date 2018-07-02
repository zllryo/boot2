package com.ryo.boot2.controller;

import com.ryo.boot2.model.Girls;
import com.ryo.boot2.model.User;
import com.ryo.boot2.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Api(value = "user操作controller", description = "user相关的操作", tags = {"user模块校验接口"})
@Path("/user")
@Controller
public class UserController {
    @Resource
    private UserService userService;


    @ApiOperation(value="获取user详细信息", notes="根据url的id来获取user详细信息")
    @ApiImplicitParam(name = "id", value = "user的ID", required = true, dataType = "Integer", paramType = "path")
    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam(value = "id") int id)
    {
           return userService.selectByPrimaryKey(id);
    }

    @ApiOperation(value="新增user信息")
    @Path("/post")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postUser(User user)
    {
        try
        {
            userService.insert(user);
            return  "success";
        }
        catch (RuntimeException ex)
        {
            throw ex;
        }
    }


    @ApiOperation(value="删除user详细信息", notes="根据url的id来删除user详细信息")
    @ApiImplicitParam(name = "id", value = "user的ID", required = true, dataType = "Integer", paramType = "path")
    @Path("/delete/{id}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public  String deleteUser(@PathParam(value = "id") int id)
    {
        try
        {
            userService.deleteByPrimaryKey(id);
            return  "success";
        }
        catch (RuntimeException ex)
        {
            throw ex;
        }
    }
}
