package system.management.book.services;

public final class UserSession {
    private static boolean isLoggedIn = false;
    private static int idUser = -1;

    public static void logInUser(int idUser) {
        isLoggedIn = true;
        UserSession.idUser = idUser;
    }

    public static void logOutUser() {
        isLoggedIn = false;
        idUser = -1;
    }

    public static boolean isUserLoggedIn() {
        return isLoggedIn;
    }

    public static int getIdUser(){
        return idUser;
    }
}
