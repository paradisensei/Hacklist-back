package org.hacklist.util.misc;

/**
 * @author Neil Alishev
 */
public class ErrorObject {
    private String errorMessage;

    public ErrorObject(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
