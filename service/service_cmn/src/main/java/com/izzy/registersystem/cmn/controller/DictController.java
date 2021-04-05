package com.izzy.registersystem.cmn.controller;

import com.izzy.registersystem.cmn.service.DictService;
import com.izzy.registersystem.common.result.Result;
import com.izzy.registersystem.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Izzy
 * @date 2021/4/5
 */
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {

    @Resource
    DictService dictService;

    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("/findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "数据字典导出")
    @GetMapping("/exportData")
    public Result exportData(HttpServletResponse response) {
        dictService.exportDictData(response);
        return Result.ok();
    }

    @ApiOperation(value = "数据字典导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        dictService.importDictData(file);
        return Result.ok();
    }

}
