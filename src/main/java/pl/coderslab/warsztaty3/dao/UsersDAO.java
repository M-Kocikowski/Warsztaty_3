package pl.coderslab.warsztaty3.dao;

import pl.coderslab.warsztaty3.model.User;
import pl.coderslab.warsztaty3.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private static final String INSERT_USER = "INSERT INTO users(username, email, password, group_id) VALUES(?, ?, ?, ?)";
    private static final String SELECT_USER = "SELECT id, username, email, password, group_id FROM users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, email = ?, password = ?, group_id = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SELECT_USERS_BY_GROUP = "SELECT * FROM users WHERE group_id = ?";

    public User create(User user) {

        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getGroup_id());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            System.out.println("Created new user: " + user.getUserName() + " with ID: " + user.getId());
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public User read(int userId) {

        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {

        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getGroup_id());
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Updated user with ID: " + user.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {

        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            System.out.println("Deleted user with ID: " + userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAllUsers() {

        try (Connection connection = DbUtil.getConnection()) {

            List<User> userList = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                userList.add(user);
            }
            return userList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"), false);
        user.setGroup_id(rs.getInt("group_id"));
        return user;
    }

    public List<User> findAllByGroupId(int groupId) {

        List<User> usersInGroup = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_BY_GROUP);
            preparedStatement.setInt(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = getUserFromResultSet(resultSet);
                usersInGroup.add(user);
            }
            return usersInGroup;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
