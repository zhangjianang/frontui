package com.ang.frontui.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UploadData {

    @ExcelProperty("显示名称")
    private String name;
    @ExcelProperty("列名")
    private String code;
    @ExcelProperty("表名")
    private String groupName;

    @ExcelProperty("筛选项查询符号")
    private String symboliSet;

    @ExcelProperty("筛选项呈现方式")
    private String assemblyId;

}
