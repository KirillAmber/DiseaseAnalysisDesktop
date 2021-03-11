package dao;

import java.io.File;
import java.sql.*;

public class DatabaseRepository {
    private Connection connection;
    /**
     * � ����� ���� ����� ��������� �� �� ������ �������, ����� ���� ����������� �������� �.
     * ��� ���� ����� ������� config.ini, ��� ����� ������������ ���� �� ��.
     * ����� ����� ������� ���, ����� ���� ���� ��������� � � ����� ���������� ����� ���������.
     */
    private final String PATH = new File("").getAbsolutePath()+"\\src\\main\\java\\db\\test_db";
    private final String URI = "jdbc:sqlite:" + PATH;

    public boolean connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URI);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean close() {
        try {
            connection.close();
            return true;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }

    public boolean insert(String sqlQuery){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            statement.close();
            System.out.println("������ ���������");
            return true;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
            return false;
        }
    }

    /**
     * ��� ��� select ����� ���������� ������ ���� ������ � ����������� �� �������,
     * �� ������ ���� ������� ��� ��� ����� ������� �������������.
     * @param sqlQuery ������� ������ ������� SELECT
     * @return resultSet ���������� ��������� �������. �������� ����� ���� null, ������� ����� ������ ��������.
     */
    public ResultSet select(String sqlQuery){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return resultSet;
    }
}