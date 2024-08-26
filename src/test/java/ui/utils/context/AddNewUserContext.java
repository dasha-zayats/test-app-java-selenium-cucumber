package ui.utils.context;

public class AddNewUserContext {
    private static String userName;
    private static String userSurname;
    private static String userEmail;
    private static String userPosition;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        AddNewUserContext.userName = userName;
    }

    public static String getUserSurname() {
        return userSurname;
    }

    public static void setUserSurname(String userSurname) {
        AddNewUserContext.userSurname = userSurname;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        AddNewUserContext.userEmail = userEmail;
    }

    public static String getUserPosition() {
        return userPosition;
    }

    public static void setUserPosition(String userPosition) {
        AddNewUserContext.userPosition = userPosition;
    }

}
