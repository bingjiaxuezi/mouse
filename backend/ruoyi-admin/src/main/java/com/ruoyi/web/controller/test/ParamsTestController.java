package com.ruoyi.web.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;

/**
 * 参数测试控制器
 * 
 * @author test
 */
@RestController
@RequestMapping("/test")
public class ParamsTestController extends BaseController
{
    /**
     * 测试params参数转换
     */
    @Anonymous
    @GetMapping("/params")
    public AjaxResult testParams(SysUser user)
    {
        return AjaxResult.success("params转换成功", user.getParams());
    }
}