package com.ryo.boot2.controller;

import com.ryo.boot2.model.Girls;
import com.ryo.boot2.service.GirlsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Api(value = "girls操作controller", description = "girls相关的操作", tags = {"girls模块校验接口"})
@RequestMapping("/girls")
@RestController
public class GirlsController {

    @Resource
    private GirlsService girlsService;

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public List<Girls> getGirlslist()
    {
        return   girlsService.selectAll();
    }

    @ApiOperation(value="获取girls详细信息", notes="根据url的id来获取girls详细信息")
    @ApiImplicitParam(name = "id", value = "girls的ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Girls getGirls(@PathVariable(value = "id") Integer id)
    {
       return   girlsService.selectByPrimaryKey(id);
    }

    @ApiOperation(value="新增girls信息")
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public String postGirls(@ModelAttribute Girls girls)
    {
        try
        {
            girlsService.insert(girls);
            return  "success";
        }
        catch (RuntimeException ex)
        {
            throw ex;
        }
    }

    @ApiOperation(value="删除girls详细信息", notes="根据url的id来删除girls详细信息")
    @ApiImplicitParam(name = "id", value = "girls的ID", required = true, dataType = "Integer", paramType = "path")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public  String deleteGirls(@PathVariable(value = "id") Integer id)
    {
        try
        {
            girlsService.deleteByPrimaryKey(id);
            return  "success";
        }
        catch (RuntimeException ex)
        {
              throw ex;
        }
    }

    @ApiOperation(value="修改girls信息", notes="根据url的id来修改girls详细信息")
    @RequestMapping(value = "/put/{id}",method = RequestMethod.PUT)
    public  String putGirls(@PathVariable(value = "id") Integer id ,@ModelAttribute  Girls girls)
    {
        try
        {
            Girls model=girlsService.selectByPrimaryKey(id);
            model.setName(girls.getName());
            model.setAge(girls.getAge());
            model.setCupsize(girls.getCupsize());
            girlsService.updateByPrimaryKey(model);
            return "success";
        }
        catch (RuntimeException ex)
        {
            throw ex;
        }
    }
}
