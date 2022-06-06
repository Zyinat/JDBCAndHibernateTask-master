package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.net.IDN;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql="CREATE TABLE IF NOT EXISTS users("+
                "id bigserial primary key ,"
                +"name varchar(50)not null ,"
                +"lastname varchar(50)not null ,"
                + "age int2);";
        try(Connection conn= Util.connection();
            Statement statement=conn.createStatement()){
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
     String sql="DROP TABLE users";
     try (Connection connection=Util.connection();
     Statement statement=connection.createStatement()){
         statement.executeUpdate(sql);

     }catch (SQLException e){
         e.printStackTrace();
     }


    }

    public void saveUser(String name, String lastName, byte age) {
        String sql="INSERT INTO users(name,lastname,age) VALUES(?,?,?)";
        try (Connection connection=Util.connection();
            PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setString(1,name);
            statement.setString(2,lastName);
            statement.setByte(3,age);
                statement.executeUpdate();
            System.out.println("Successfully " +name);
            } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        String sql="DELETE FROM users WHERE id=?";
        try (Connection connection=Util.connection();
             PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setLong(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        String sql="SELECT * FROM users";
        List<User> list=new ArrayList<>();
        try (Connection connection=Util.connection();
        Statement statement=connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql)){
            User user=new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql="TRUNCATE TABLE users";
        try(Connection connection=Util.connection();
          Statement statement=connection.createStatement()){
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}