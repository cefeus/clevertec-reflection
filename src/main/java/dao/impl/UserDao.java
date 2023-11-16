package dao.impl;

import config.db.ConnectionSingleton;
import dao.Dao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static util.constants.SqlQueryConstants.*;

public class UserDao implements Dao<User> {

    private final Connection connection;

    {
        try {
            connection = ConnectionSingleton.getConnection().open();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get db connection");
        }
    }

    @Override
    public Optional<User> get(UUID id) {
        try(var statement = prepare(SQL_GET_USER_BY_ID, id)){
            try (var rs = statement.executeQuery()) {
                return rs.first()
                        ? Optional.of(buildUser(rs))
                        : Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get user");
        }
    }

    @Override
    public List<User> getAll() {
        try(var statement = prepare(SQL_GET_ALL_USERS)){
            try (var rs = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while(rs.next()) {
                    users.add(buildUser(rs));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to get all users");
        }
    }

    @Override
    public void save(User user) {
        try(var statement = user.getId() == null
                ? prepare(SQL_CREATE_USER, user.getName(), user.getSurname(), user.getEmail(), user.getEmail())
                : prepare(SQL_UPDATE_USER, user.getName(), user.getSurname(), user.getEmail(), user.getEmail(), user.getId())){
            var rs = statement.executeUpdate();
            if (rs != 1){
                throw new RuntimeException("Unable to save or update");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        try(var statement = prepare(SQL_DELETE_USER, id)) {
            var rs = statement.executeUpdate();
            if (rs != 1){
                throw new RuntimeException("Unable delete user");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement prepare(String query, Object... params) {
        try {
        var statement = connection.prepareStatement(
                query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        for (int i = 1; i < params.length; i++) {
            statement.setObject(i, params[i]);
        }
        return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet rs) throws SQLException {
        return User.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .email(rs.getString("email"))
                .age(rs.getInt("age"))
                .build();
    }

}
