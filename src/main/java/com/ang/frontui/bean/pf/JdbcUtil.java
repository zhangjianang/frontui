package com.ang.frontui.bean.pf;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.*;

/**
 * 通用JDBC操作类
 */
@Slf4j
public class JdbcUtil {

     /*数据库连接对象*/
    private Connection connection = null;
     /*执行静态SQL语句*/
    private Statement statement = null;
    /*执行动态SQL语句*/
    private PreparedStatement preparedStatement = null;
     /*结果集*/
    private ResultSet resultSet = null;

    private static int QUERY_TIMEOUT=120;


    /*数据库连接URL*/
    private String url;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;
    private String driveName;


    public JdbcUtil(String url, String username, String password, String driveName) {
        this.url = url;
        this.username = username;
        this.password=password;
        this.driveName=driveName;
        init();
    }
    public void init(){
        try {
            // 加载数据驱动，需确保引入相关的jar包
            if(driveName!=null)Class.forName(driveName);
        } catch (ClassNotFoundException e) {
            log.error(" 初始化 JdbcUtil 数据驱动失败！失败的数据驱动类型：" + driveName,e);
        }
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接
     */
    private Connection getConnection() throws SQLException {
        try {
            // 建立数据库连接对象
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error(" JdbcUtil 连接数据库失败！失败原因：" + ExceptionUtils.getStackTrace(e));
            throw e;
        }
        return connection;
    }

    /**
     * 执行查询操作
     *
     * @param sql SQL语句
     * @return 返回值是一个结果集
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        try {

            connection = this.getConnection();
            if (null != connection) {
                statement = connection.createStatement();
                if (null != statement) {
                    statement.setQueryTimeout(QUERY_TIMEOUT);
                    resultSet = statement.executeQuery(sql);
                }
            }
        } catch (SQLException e) {
            log.error("JdbcUtil 执行失败：",e);
            throw e;
        }
        return resultSet;
    }

    /**
     * 关闭数据库连接
     */
    private void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(" JdbcUtil 关闭结果集失败！失败原因：" + ExceptionUtils.getStackTrace(e));
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error(" JdbcUtil 关闭执行静态SQL实例对象失败！失败原因：" + ExceptionUtils.getStackTrace(e));

            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error(" JdbcUtil 关闭执行动态SQL实例对象失败！失败原因：" + ExceptionUtils.getStackTrace(e));

            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("JdbcUtil 关闭数据库连接失败！失败原因：" + ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理resultSet结果集对象，并将结果集对象封装成 List<Map<String, Object>> 对象
     *
     * @param resultSet 结果集对象
     * @return 结果集合
     * @throws SQLException SQL异常
     */
    private static List<Map<String, Object>> getDates(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> dates = new ArrayList<>();
        // 获取结果集的数据结构对象
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnLabel(i).toLowerCase(), resultSet.getObject(i));
            }
            dates.add(rowMap);
        }
        return dates;
    }
//    /**
//     * 处理resultSet结果集，获取列类型
//     *
//     * @param resultSet 结果集对象
//     * @return 结果集合
//     * @throws SQLException SQL异常
//     */
//    private static List<Pair<String, Integer>> getColType(ResultSet resultSet) throws SQLException {
//        // 获取结果集的数据结构对象
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        List<Pair<String, Integer>> columnTypeMap = new ArrayList<Pair<String, Integer>>();
//        if(metaData!=null){
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                Pair<String, Integer> pair = new Pair<String, Integer>(metaData.getColumnLabel(i).toLowerCase(), metaData.getColumnType(i));
//                columnTypeMap.add(pair);
//            }
//        }
//        return columnTypeMap;
//    }


    /**
     * 测试数据源是否可以连通
     *
     * @return 连通结果
     */
    public Boolean testDBConnect() throws SQLException {
        if (this.getConnection() != null) {
            // 关闭相关连接
            this.close();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 执行查询语句 - 无参数
     *
     * @param querySql 查询语句
     * @return 结果集合
     */
    public List<Map<String, Object>> executeQuerySql(String querySql) throws SQLException {
        try {
            // 执行查询语句
            ResultSet rs = this.executeQuery(querySql);
            if (null != rs) {
                // 处理查询结果
                return getDates(rs);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            // 关闭相关连接
            this.close();
        }
        return null;
    }
//    /**
//     * 执行查询语句 - 无参数
//     *
//     * @param querySql 查询语句
//     * @return 结果集合
//     */
//    public List<Pair<String, Integer>> executeSqlGetColType(String querySql) throws SQLException {
//        try {
//            // 执行查询语句
//            ResultSet rs = this.executeQuery(querySql);
//            if (null != rs) {
//                // 处理查询结果
//                return getColType(rs);
//            }
//        } catch (SQLException e) {
//            throw e;
//        } finally {
//            // 关闭相关连接
//            this.close();
//        }
//        return null;
//    }



    /**
     * 带条件查询
     * @param query
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> getResultSet(String query, String[] params)
            throws SQLException {
        Connection connection = getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        try {

            ps = connection.prepareStatement(query);
            log.debug("DB Query to run " + query);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setString(i + 1, params[i]);
                    log.debug("Param" + (i + 1) + " " + params[i]);
                }
            }
            resultSet = ps.executeQuery();
            int numColumns = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new LinkedHashMap<String, Object>();
                for (int i = 0; i < numColumns; ++i) {
                    String column = resultSet.getMetaData().getColumnName(i + 1);
                    Object value = resultSet.getObject(i + 1);
                    if (value instanceof String) {
                        value = (StringUtils.trim((String) value));
                    }
                    if (value instanceof Clob) {
                        value = clobToString((Clob) value);
                    }
                    row.put(StringUtils.trim(column), value);
                }
                rows.add(row);
            }
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception e) {
            }
        }
        return rows;
    }


    /**
     * 处理blog数据
     * @param data
     * @return
     */
    private String clobToString(Clob data) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = data.getCharacterStream();
            BufferedReader br = new BufferedReader(reader);

            String line;
            while (null != (line = br.readLine())) {
                sb.append(line);
            }
            br.close();
        } catch (SQLException e) {
            log.error("[SQLUtilsImpl] - Unable to handle clob", data, e);
        } catch (IOException e) {
            log.error("[SQLUtilsImpl] -  Unable to handle clob", data, e);
        }
        return sb.toString();
    }

    /**
     * @Description 获取统计SQL的总数
     * @Date 2020/10/10
     * @param countSql
     * @return java.lang.Long
    */
    public Long executeQueryTotal(String countSql) throws SQLException {
        ResultSet resultSet = executeQuery(countSql);
        if(resultSet.next()){
            return resultSet.getLong(1);
        }
        return 0L;
    }
}
