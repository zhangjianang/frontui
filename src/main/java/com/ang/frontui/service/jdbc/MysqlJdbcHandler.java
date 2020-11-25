package com.ang.frontui.service.jdbc;

import com.ang.frontui.bean.pf.ColumVo;
import com.ang.frontui.bean.pf.DatasourceInfo;
import com.ang.frontui.bean.pf.JdbcUtil;
import com.ang.frontui.bean.pf.TableVo;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MysqlJdbcHandler extends AbstractJdbcHandler{

    public MysqlJdbcHandler(DatasourceInfo datasourceInfo) {
        super(datasourceInfo);

    }

    @Override
    protected List<TableVo> allTables() throws Exception {

        List<TableVo> list=new ArrayList<>();
       JdbcUtil jdbcUtil= getJdbcUtil();
       String sqlQuery="show table status";
        ResultSet resultSet=null;
        try {
            resultSet = jdbcUtil.executeQuery(sqlQuery);
       }catch (SQLException e){
           log.error("执行sql异常",e);
           throw new Exception("执行sql异常");
       }
        try {
            while (resultSet.next()) {
                list.add(new TableVo(resultSet.getString("Name"),resultSet.getString("Comment")));
            }
        }catch (SQLException e){
            log.error("解析resultSet异常!",e);
            throw new Exception("解析resultSet异常!");
        }
        return list;
    }

    @Override
    protected List<ColumVo> createTableInfo(String tableName) throws Exception{
        List<ColumVo> list=new ArrayList<>();
        JdbcUtil jdbcUtil= getJdbcUtil();
        String querySql=String.format("show full fields from %s",tableName);
        ResultSet resultSet=null;

        try {
            resultSet = jdbcUtil.executeQuery(querySql);
        }catch (SQLException e){
            log.error("执行sql异常",e);
            throw new Exception("执行sql异常");
        }
        try {
            while (resultSet.next()) {
                list.add(new ColumVo(resultSet.getString("Field"),resultSet.getString("Type"),resultSet.getString("Comment")));
            }
        }catch (SQLException e){
            log.error("解析resultSet异常!",e);
            throw new Exception("解析resultSet异常!");
        }
        return list;
    }

    @Override
    protected String bulidUrl(String ip, Integer port, String dataBaseName) {
        StringBuffer urlBuff=new StringBuffer();
        urlBuff.append("jdbc:mysql://");
        urlBuff.append(ip).append(":").append(port).append("/");
        urlBuff.append(dataBaseName);
        urlBuff.append("?useUnicode=true&characterEncoding=utf-8");
        return urlBuff.toString();
    }

    @Override
    protected JdbcUtil bulidJdbcUtil() throws Exception {
        return new JdbcUtil(getJdbcUrl(),datasourceInfo.getConnUser(),datasourceInfo.getConnPwd(),"com.mysql.jdbc.Driver");
    }


    }
