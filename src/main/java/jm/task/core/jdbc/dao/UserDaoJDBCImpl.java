package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao{

    @Override
    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    name VARCHAR(50) NOT NULL,
                    lastName VARCHAR(50) NOT NULL,
                    age TINYINT
                )
                """;
        try (Connection connection = Util.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           throw new RuntimeException();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = """
                DROP TABLE IF EXISTS users;
                """;
        try (Connection connection = Util.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO users (name, lastname, age) 
                VALUES ( ?, ?, ?);
                """;
        try (Connection connection = Util.open()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = """
                DELETE FROM users 
                WHERE id = ?;
                """;
        try (Connection connection = Util.open()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = """
                SELECT * FROM users; 
                """;
        try (Connection connection = Util.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = """
                DELETE FROM users;
                """;

        try (Connection connection = Util.open()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException();
        }

    }

}
