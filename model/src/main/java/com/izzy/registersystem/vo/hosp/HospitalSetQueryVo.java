package com.izzy.registersystem.vo.hosp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Izzy
 * @date 2021/4/2
 */
@Data
public class HospitalSetQueryVo {

    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    @ApiModelProperty(value = "医院编号")
    private String hospitalCode;

}
