package com.izzy.registersystem.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.izzy.registersystem.common.result.Result;
import com.izzy.registersystem.common.utils.MD5;
import com.izzy.registersystem.hosp.service.HospitalSetService;
import com.izzy.registersystem.model.hosp.HospitalSet;
import com.izzy.registersystem.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.expression.operators.relational.RegExpMySQLOperator;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author Izzy
 * @date 2021/4/2
 */
@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Resource
    private HospitalSetService hospitalSetService;

    @ApiOperation(value = "医院设置 - 获取所有")
    @GetMapping("/findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> hospitalSetList = hospitalSetService.list();
        return Result.ok(hospitalSetList);
    }

    @ApiOperation(value = "医院设置 - 根据ID逻辑删除")
    @DeleteMapping("{id}")
    public Result removeHospitalSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        return flag ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "医院设置 - 条件查询带分页")
    @GetMapping("/findPage/{current}/{limit}")
    public Result findPageHospitalSet(@PathVariable long current,
                                      @PathVariable long limit,
                                      @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {

        Page<HospitalSet> page = new Page<>(current, limit);
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHospitalName())) {
            queryWrapper.like("hospital_name", hospitalSetQueryVo.getHospitalName());
        }
        if (!StringUtils.isEmpty(hospitalSetQueryVo.getHospitalCode())) {
            queryWrapper.eq("hospital_code", hospitalSetQueryVo.getHospitalCode());
        }
        Page<HospitalSet> hospitalSetPage = hospitalSetService.page(page, queryWrapper);

        return Result.ok(hospitalSetPage);
    }

    @ApiOperation(value = "医院设置 - 添加")
    @PostMapping("/saveHospitalSet")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        hospitalSet.setStatus(1);
        //签名密钥
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + new Random().nextInt(1000)));
        return hospitalSetService.save(hospitalSet) ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "医院设置 - 根据ID获取")
    @GetMapping("/getHospitalSet/{id}")
    public Result getHospitalSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    @ApiOperation(value = "医院设置 - 修改")
    @PostMapping("/updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean flag = hospitalSetService.updateById(hospitalSet);
        return flag ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "医院设置 - 批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        boolean flag = hospitalSetService.removeByIds(idList);
        return flag ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "医院设置 - 锁定&解锁")
    @PutMapping("/lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                   @PathVariable Integer status) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        boolean flag = hospitalSetService.updateById(hospitalSet);
        return flag ? Result.ok() : Result.fail();
    }

    @ApiOperation(value = "医院设置 -  发送签名和密钥")
    @PutMapping("/sendKey/{id}")
    public Result sendKey(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hospitalCode = hospitalSet.getHospitalCode();
        //TODO

        return Result.ok();
    }

}