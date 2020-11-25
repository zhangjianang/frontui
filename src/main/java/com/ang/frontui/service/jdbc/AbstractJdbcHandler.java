package com.ang.frontui.service.jdbc;

import com.ang.frontui.bean.pf.ColumVo;
import com.ang.frontui.bean.pf.DatasourceInfo;
import com.ang.frontui.bean.pf.JdbcUtil;
import com.ang.frontui.bean.pf.TableVo;
import com.ang.frontui.util.ValidateUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * jdbc操作类
 */
@Slf4j
public abstract class AbstractJdbcHandler {


    protected DatasourceInfo datasourceInfo; // 入参

    protected String exeSql;   //执行的sql



    public AbstractJdbcHandler(DatasourceInfo datasourceInfo) {
       this.datasourceInfo=datasourceInfo;
    }

    /**
     * 获取url连接
     * @return
     */
    public String getJdbcUrl() throws Exception{

        if(ValidateUtils.isEmpty(datasourceInfo)){
            throw new Exception("数据源信息为NULL");
        }

        if(ValidateUtils.isEmpty(datasourceInfo.getMachineIp())){
            throw new Exception("数据库IP不能为空");
        }

        if(ValidateUtils.isEmpty(datasourceInfo.getPort())){
            throw new Exception("数据库端口不能为空");
        }

        if(ValidateUtils.isEmpty(datasourceInfo.getDatasourceName())){
            throw new Exception("数据库名称不能为空");
        }

        return bulidUrl(datasourceInfo.getMachineIp(),datasourceInfo.getPort(),datasourceInfo.getDatasourceName());
    }


    /**
     * 获取jdbc操作对象
     * @return
     */
    public JdbcUtil getJdbcUtil() throws Exception {
       return bulidJdbcUtil();
    }



    /**
     * 列出所有的表和视图
     * @return 所有的表和视图
     */
    public List<TableVo> getAllTables() throws Exception{
        return allTables();
    }


    /**
     * 列出所有的字段
     * @param
     * @return 所有的字段
     */
    public List<ColumVo> getCreateTableInfo(String tableName) throws Exception {
        return createTableInfo(tableName);
    }


    /**
     * 获取所有表信息
     * @return
     */
    protected  abstract List<TableVo> allTables() throws Exception;


    protected  abstract List<ColumVo> createTableInfo(String tableName) throws Exception;



    /**
     * 构建URL，由子类实现
     */
    protected  abstract String bulidUrl(String ip, Integer port, String dataBaseName);






    /**
     * 构建URL，由子类实现
     */
    protected abstract JdbcUtil bulidJdbcUtil() throws Exception;




}
