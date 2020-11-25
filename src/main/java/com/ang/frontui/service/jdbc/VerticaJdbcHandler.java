//package com.ang.frontui.service.jdbc;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//public class VerticaJdbcHandler extends AbstractJdbcHandler{
//
//    public VerticaJdbcHandler(DatasourceInfo datasourceInfo) {
//        super(datasourceInfo);
//    }
//
//    @Override
//    protected List<TableVo> allTables() {
//
//        List<TableVo> list=new ArrayList<>();
//        JdbcUtil jdbcUtil= getJdbcUtil();
//        String sqlQuery="SELECT a.table_name,b.comment FROM tables a left join (select object_name,comment from comments where object_type='TABLE') b on a.table_name=b.object_name\n";
//        ResultSet resultSet=null;
//        try {
//            resultSet = jdbcUtil.executeQuery(sqlQuery);
//        }catch (SQLException e){
//            log.error("执行sql异常",e);
//            throw new BizException("执行sql异常");
//        }
//        try {
//            while (resultSet.next()) {
//                list.add(new TableVo(resultSet.getString("table_name"),resultSet.getString("comment")));
//            }
//        }catch (SQLException e){
//            log.error("解析resultSet异常!",e);
//            throw new BizException("解析resultSet异常!");
//        }
//        return list;
//    }
//
//    @Override
//    protected List<ColumVo> createTableInfo(String tableName) {
//
//        List<ColumVo> list=new ArrayList<>();
//        JdbcUtil jdbcUtil= getJdbcUtil();
//        String querySql=String.format("SELECT column_name,data_type,b.comment FROM columns a left join (select object_name,comment from comments where object_type='COLUMN') b on a.column_name=b.object_name where a.table_name='%s'",tableName);
//        ResultSet resultSet=null;
//
//        try {
//            resultSet = jdbcUtil.executeQuery(querySql);
//        }catch (SQLException e){
//            log.error("执行sql异常",e);
//            throw new BizException("执行sql异常");
//        }
//        try {
//            while (resultSet.next()) {
//                list.add(new ColumVo(resultSet.getString("column_name"),resultSet.getString("data_type"),resultSet.getString("comment")));
//            }
//        }catch (SQLException e){
//            log.error("解析resultSet异常!",e);
//            throw new BizException("解析resultSet异常!");
//        }
//        return list;
//    }
//
//    @Override
//    protected String bulidUrl(String ip, Integer port, String dataBaseName) {
//
//
//        StringBuffer urlBuff=new StringBuffer();
//        urlBuff.append("jdbc:vertica://");
//        urlBuff.append(ip).append(":").append(port).append("/");
//        urlBuff.append(dataBaseName);
//        return urlBuff.toString();
//    }
//
//    @Override
//    protected JdbcUtil bulidJdbcUtil() {
//       return new JdbcUtil(getJdbcUrl(),datasourceInfo.getConnUser(),datasourceInfo.getConnPwd(),DBDrriverEnum.VERTICA_DRIVE);
//    }
//}
