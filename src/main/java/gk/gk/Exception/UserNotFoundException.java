package gk.gk.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("User Name Not Exist " + id);
    }
}
