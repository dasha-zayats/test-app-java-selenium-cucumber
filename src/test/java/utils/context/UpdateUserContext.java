package utils.context;

public class UpdateUserContext {
    private static String userName;
    private static String userSurname;
    private static String userEmail;
    private static String userPosition;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        UpdateUserContext.userName = userName;
    }

    public static String getUserSurname() {
        return userSurname;
    }

    public static void setUserSurname(String userSurname) {
        UpdateUserContext.userSurname = userSurname;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        UpdateUserContext.userEmail = userEmail;
    }

    public static String getUserPosition() {
        return userPosition;
    }

    public static void setUserPosition(String userPosition) {
        UpdateUserContext.userPosition = userPosition;
    }
}
