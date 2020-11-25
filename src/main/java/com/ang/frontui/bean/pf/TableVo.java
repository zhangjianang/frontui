package com.ang.frontui.bean.pf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor // 全参构造方法,字段比较少时，建议使用
@NoArgsConstructor // 无参构造方法
@ApiModel(value="数据源表信息",description="数据源表信息")
public class TableVo {

    @ApiModelProperty(value = "表名")
    private String tableCode;
    @ApiModelProperty(value = "表注释")
    private String tableComments;



}
