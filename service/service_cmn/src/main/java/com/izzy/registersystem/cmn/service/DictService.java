package com.izzy.registersystem.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.izzy.registersystem.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Izzy
 * @date 2021/4/5
 */
public interface DictService extends IService<Dict> {

    /**
     * 根据数据id查询子数据列表
     * @param id id
     * @return List<Dict>
     */
    List<Dict> findChildData(Long id);

    /**
     * 数据字典导出
     * @param response 服务器的响应
     */
    void exportDictData(HttpServletResponse response);

    /**
     * 数据字典导入
     * @param file MultipartFile
     */
    void importDictData(MultipartFile file);
}
