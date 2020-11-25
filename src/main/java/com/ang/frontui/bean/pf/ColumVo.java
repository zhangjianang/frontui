package com.ang.frontui.bean.pf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="数据源表信息",description="数据源表信息")
public class ColumVo {

    public ColumVo(){

    }

    public ColumVo(String columCode, String columType, String columComments){
        this.columCode=columCode;
        this.columType=columType;
        this.columComments=columComments;

    }

    @ApiModelProperty(value = "列名")
    private String columCode;

    @ApiModelProperty(value = "列注释")
    private String columComments;

    @ApiModelProperty(value = "列数据类型")
    private String columType;

//    @ApiModelProperty(value = "列长度")
//    private String columLength;
//
//    @ApiModelProperty(value = "建表语句")
//    private String ddlSql;

}
