package util.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SqlQueryConstants {

    public static String SQL_GET_USER_BY_ID = "SELECT * FROM \"reflection-task\".users WHERE id = ?";
    public static String SQL_GET_ALL_USERS = "SELECT * FROM \"reflection-task\".users";
    public static String SQL_CREATE_USER = "INSERT INTO \"reflection-task\".users (name, surname, email, age) VALUES ( ?, ?, ?, ?)";
    public static String SQL_UPDATE_USER = "INSERT INTO \"reflection-task\".users (name, surname, email, age) VALUES ( ?, ?, ?, ?) WHERE id = ?";
    public static String SQL_DELETE_USER = "DELETE FROM \"reflection-task\".users WHERE id = ?";
}
