package yun.web.common.util;

import java.sql.*;
import java.util.*;


/**
 * Created by larry on 15/12/15.
 */
public class DBKit {
    private static String username = PropKit.getPro("username");
    private static String password = PropKit.getPro("password");
    private static String ip = PropKit.getPro("ip");
    private static String db = PropKit.getPro("db");
    private static String port = PropKit.getPro("port");
    private static String host = "jdbc:mysql://"+ip+":"+port+"/"+db;

    private static Connection connect = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println(host + "|" + username + "|" + password);
            connect = DriverManager.getConnection(host, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection coo = null;
        try {
            coo = DriverManager.getConnection(host, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(coo != null) return coo;
            else return null;
        }
    }

    public static List<Map<String, Object>> select(String sql) {
        List list = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            list = fillMap(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static Map<String, Object> selectOne(String sql) {
        Map map = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(sql);
            map = fillMapOne(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return map==null? new HashMap():map;
    }


    private static List<Map> fillMap(ResultSet rs) throws SQLException {
        List<Map> list = new ArrayList<Map>();
        while (true) {
            Map map = fillMapOne(rs);
            if(map != null) list.add(map);
            else break;
        }
        return list;
    }

    private static Map fillMapOne(ResultSet rs) throws SQLException {
        Map<String, Object> map = null;
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        if(rs.next()) {
            map = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                String colName = metaData.getColumnName(i);
                Object value = rs.getObject(colName);
                map.put(colName, value);
            }
        }
        return map;
    }

}
