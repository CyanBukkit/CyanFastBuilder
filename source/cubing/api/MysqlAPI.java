package cubing.api;

import cubing.*;
import java.util.logging.*;
import java.sql.*;

public class MysqlAPI
{
    public static Connection connection;
    
    public MysqlAPI() {
        super();
    }
    
    public void Load(final String url, final String user, final String password) {
        try {
            if (MysqlAPI.connection != null && MysqlAPI.connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            MysqlAPI.connection = DriverManager.getConnection(url, user, password);
            speedcubingLib.logger.log(Level.INFO, "[speedcubingLib] Mysql successfully connected.");
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void delete(final String table, final String where) {
        try {
            final PreparedStatement prepsInsertProduct = MysqlAPI.connection.prepareStatement("DELETE  FROM " + table + " WHERE " + where);
            prepsInsertProduct.execute();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void update(final String table, final String field, final String where) {
        try {
            MysqlAPI.connection.prepareStatement("UPDATE " + table + " SET " + field + " WHERE " + where).execute();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void insert(final String table, final String field, final String value) {
        try {
            final PreparedStatement prepsInsertProduct = MysqlAPI.connection.prepareStatement("INSERT INTO " + table + " " + field + " VALUES " + value);
            prepsInsertProduct.execute();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isStringExist(final String table, final String field, final String target) {
        try {
            final PreparedStatement statement = MysqlAPI.connection.prepareStatement("SELECT * FROM " + table + " WHERE " + field + "=?");
            statement.setString(1, target);
            return statement.executeQuery().next();
        }
        catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static double selectDouble(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT " + field + " FROM " + table + " WHERE " + where);
            resultSet.next();
            return resultSet.getDouble(1);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return -1.0;
        }
    }
    
    public static int selectInt(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT " + field + " FROM " + table + " WHERE " + where);
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static int count(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT count(" + field + ") FROM " + table + " WHERE " + where);
            resultSet.next();
            return resultSet.getInt(1);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public static int[] selectInts(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT " + field + " FROM " + table + " WHERE " + where);
            resultSet.next();
            final int size = resultSet.getMetaData().getColumnCount();
            final int[] ints = new int[size];
            for (int i = 0; i < size; ++i) {
                ints[i] = resultSet.getInt(i + 1);
            }
            return ints;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean[] selectBooleans(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT " + field + " FROM " + table + " WHERE " + where);
            resultSet.next();
            final int size = resultSet.getMetaData().getColumnCount();
            final boolean[] bs = new boolean[size];
            for (int i = 0; i < size; ++i) {
                bs[i] = resultSet.getBoolean(i + 1);
            }
            return bs;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean selectBoolean(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT " + field + " FROM " + table + " WHERE " + where);
            resultSet.next();
            return resultSet.getBoolean(1);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String selectString(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT " + field + " FROM " + table + " WHERE " + where);
            resultSet.next();
            return resultSet.getString(1);
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String[] selectStrings(final String table, final String field, final String where) {
        try {
            final ResultSet resultSet = MysqlAPI.connection.createStatement().executeQuery("SELECT " + field + " FROM " + table + " WHERE " + where);
            resultSet.next();
            final int size = resultSet.getMetaData().getColumnCount();
            final String[] str = new String[size];
            for (int i = 0; i < size; ++i) {
                str[i] = resultSet.getString(i + 1);
            }
            return str;
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
