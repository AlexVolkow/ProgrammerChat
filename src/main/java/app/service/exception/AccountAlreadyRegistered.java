package app.service.exception;

/**
 * Created by alexa on 30.01.2017.
 */
public class AccountAlreadyRegistered extends RuntimeException {
    public AccountAlreadyRegistered(String message) {
        super(message);
    }

    public AccountAlreadyRegistered(Throwable cause) {
        super(cause);
    }
}
