package com.ang.frontui.service.pf;

import com.ang.frontui.bean.pf.ColumVo;
import com.ang.frontui.bean.pf.DatasourceInfo;
import com.ang.frontui.bean.pf.TableVo;
import com.ang.frontui.service.jdbc.AbstractJdbcHandler;
import com.ang.frontui.service.jdbc.MysqlJdbcHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSourceService {
    AbstractJdbcHandler jdbcHandler;

    private void init(){
        DatasourceInfo datasourceInfo = new DatasourceInfo();
        datasourceInfo.setDataName("测试连接");
        datasourceInfo.setConnectUrl("jdbc:mysql://www.zja.com");
        datasourceInfo .setConnPwd("ang001");
        datasourceInfo.setConnUser("ang01");
        datasourceInfo.setMachineIp("49.233.198.165");
        datasourceInfo.setDatasourceName("ang_bi");
        datasourceInfo.setPort(3306);
        jdbcHandler = new MysqlJdbcHandler(datasourceInfo);
    }

    public List<TableVo> getAllTables(){
        try {
            init();
            return jdbcHandler.getAllTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<ColumVo> getAllColumn(String tableName) {
        try {
            init();
            return jdbcHandler.getCreateTableInfo(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
