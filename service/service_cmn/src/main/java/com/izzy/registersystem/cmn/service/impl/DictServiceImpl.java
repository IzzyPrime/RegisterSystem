package com.izzy.registersystem.cmn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.izzy.registersystem.cmn.mapper.DictMapper;
import com.izzy.registersystem.cmn.service.DictService;
import com.izzy.registersystem.model.cmn.Dict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Izzy
 * @date 2021/4/5
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    /**
     * 根据数据id查询子数据列表
     * @param id id
     * @return List<Dict>
     */
    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        for (Dict dict:dictList) {
            boolean hasChildren = this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        }
        return dictList;
    }

    /**
     * 判断id下面是否有子节点
     * @param id id
     * @return boolean 是否有子节点
     */
    private boolean hasChildren(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }
}