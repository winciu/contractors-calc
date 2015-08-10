package pl.winciu.calc.api.representation;

/**
 * @author Adam Winciorek
 */
public class ErrorRepresentation {
    private final String message;

    public ErrorRepresentation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
