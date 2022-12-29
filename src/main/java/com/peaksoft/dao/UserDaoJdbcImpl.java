package com.peaksoft.dao;



import com.peaksoft.model.User;
import com.peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS usersjdbc ("+
                "id SERIAL PRIMARY KEY ,"+
                "name VARCHAR(30) , "+
                "lastname VARCHAR(30),"+
                "age INT);";
        try (Connection conn = Util.connection();
             Statement statement = conn.createStatement()){
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE usersjdbc ;";
        try (Connection conn = Util.connection();
             Statement statement = conn.createStatement()){
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO usersjdbc (name,lastName,age) VALUES(?,?,?);";
        try(Connection conn = Util.connection();
            PreparedStatement statement = conn.prepareStatement(SQL)){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
            statement.executeUpdate();
            System.out.println("User successfully added");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM usersjdbc WHERE id=?;";
//        User user = new User();
        try (Connection conn = Util.connection();
        PreparedStatement statement = conn.prepareStatement(SQL)){
            statement.setInt(1, (int) id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String SQL = "SELECT * FROM usersjdbc;";
        List<User> users = new ArrayList<>();

        try(Connection conn = Util.connection();
        Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                User user = new User();
                user.setId((long) resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge((byte) resultSet.getInt("age"));
                users.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE usersjdbc ;";
        try (Connection conn = Util.connection();
             Statement statement = conn.createStatement()){
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}