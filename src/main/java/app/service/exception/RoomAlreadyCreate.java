package app.service.exception;

/**
 * Created by alexa on 30.01.2017.
 */
public class RoomAlreadyCreate extends RuntimeException {
    public RoomAlreadyCreate(String message) {
        super(message);
    }
}
