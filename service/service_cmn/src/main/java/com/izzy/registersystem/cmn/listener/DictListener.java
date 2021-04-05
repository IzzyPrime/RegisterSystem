package com.izzy.registersystem.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.izzy.registersystem.cmn.mapper.DictMapper;
import com.izzy.registersystem.model.cmn.Dict;
import com.izzy.registersystem.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

/**
 * @author Izzy
 * @date 2021/4/5
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {

    private DictMapper dictMapper;

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }



    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo, dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
