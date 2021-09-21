package com.lhd.qd.module.sys.dict.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 * @since 2019-05-31
 */
@Setter
@Getter
@ToString
@ApiModel(value = "字典前端绑定视图对象")
public class DictPageBindVo {

    @ApiModelProperty(value = "值")
    private Integer value;
    @ApiModelProperty(value = "描述")
    private String desc;

    public DictPageBindVo() {}

    public DictPageBindVo(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
