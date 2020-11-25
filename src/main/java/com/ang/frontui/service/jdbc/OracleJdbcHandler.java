//package com.ang.frontui.service.jdbc;
//
//
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * appservice cts打包操作
// */
//@Slf4j
//public class OracleJdbcHandler extends AbstractJdbcHandler {
//
//    public OracleJdbcHandler(DatasourceInfo datasourceInfo) {
//        super(datasourceInfo);
//
//    }
//
//    @Override
//    protected List<TableVo> allTables() {
//
//        List<TableVo> list=new ArrayList<>();
//        JdbcUtil jdbcUtil= getJdbcUtil();
//        String sqlQuery="select a.TABLE_NAME as name,b.COMMENTS as comm from user_tables a left join user_tab_comments b on a.TABLE_NAME=b.TABLE_NAME";
//        ResultSet resultSet=null;
//        try {
//            resultSet = jdbcUtil.executeQuery(sqlQuery);
//        }catch (SQLException e){
//            log.error("执行sql异常",e);
//            throw new BizException("执行sql异常");
//        }
//        try {
//            while (resultSet.next()) {
//                list.add(new TableVo(resultSet.getString("name"),resultSet.getString("comm")));
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
//
//        String querySql=String.format("select a.COLUMN_NAME,a.DATA_TYPE,b.COMMENTS from user_tab_columns a left join (select COLUMN_NAME,comments from USER_COL_COMMENTS where TABLE_NAME='%s') b on a.COLUMN_NAME=b.COLUMN_NAME where a.TABLE_NAME='%s'",tableName,tableName);
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
//                list.add(new ColumVo(resultSet.getString("COLUMN_NAME"),resultSet.getString("DATA_TYPE"),resultSet.getString("COMMENTS")));
//            }
//        }catch (SQLException e){
//            log.error("解析resultSet异常!",e);
//            throw new BizException("解析resultSet异常!");
//        }
//        return list;
//    }
//
//
////    @Override
////    protected String createTableInfo(String tableName) {
////        JdbcUtil jdbcUtil= getJdbcUtil();
////        List list=new ArrayList();
////        String querySql = "SELECT u.column_name,u.data_type,u.data_length,u.data_precision,u.data_Scale,u.nullable,u.data_default,c.comments FROM user_tab_columns u,user_col_comments c" +
////
////                " WHERE u.table_name='" + tableName + "' and u.table_name=c.table_name and c.column_name=u.column_name";
////
////        ResultSet resultSet=null;
////        String result="";
////        try {
////            resultSet = jdbcUtil.executeQuery(querySql);
////        }catch (SQLException e){
////            log.error("执行sql异常",e);
////            throw new BizException("执行sql异常");
////        }
////        try {
////            while (resultSet.next()) {
////                String[] string = new String[8];
////                for (int i = 1; i < 8 + 1; i++) {
////                    string[i - 1] = resultSet.getString(i);
////                }
////                list.add(string);
////            }
////        }catch (SQLException e){
////            log.error("解析resultSet异常!",e);
////            throw new BizException("解析resultSet异常!");
////        }
////        result=new Gson().toJson(list);
////        return result;
////    }
//
//    @Override
//    protected String bulidUrl(String ip, Integer port, String dataBaseName) {
//
//        StringBuffer urlBuff=new StringBuffer();
//        urlBuff.append("jdbc:oracle:thin:@");
//        urlBuff.append(ip).append(":").append(port).append(":").append(dataBaseName);
//        return urlBuff.toString();
//    }
//
//
//    @Override
//    protected JdbcUtil bulidJdbcUtil() {
//        return new JdbcUtil(getJdbcUrl(),datasourceInfo.getConnUser(),datasourceInfo.getConnPwd(), DBDrriverEnum.ORACLE_DRIVE);
//    }
//
//}
//
//
//
