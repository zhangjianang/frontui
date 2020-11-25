package com.ang.frontui.controller.pf;

import com.ang.frontui.bean.pf.ColumVo;
import com.ang.frontui.bean.pf.TableVo;
import com.ang.frontui.service.pf.DataSourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mydataSource")
public class DataSourceController {

    @Autowired
    DataSourceService dataSourceService;

    @PostMapping(value = "getAllTables")
    @ApiOperation("获取所有表信息")
    public ResultData<List<TableVo>> getAllTables() {

        return ResultData.success(dataSourceService.getAllTables());
    }

    @PostMapping("getAllField")
    @ApiOperation("获取所有字段信息")
    public ResultData<List<ColumVo>> getAllColumn(@RequestParam String tableCode){
        return ResultData.success(dataSourceService.getAllColumn(tableCode));
    }

}
