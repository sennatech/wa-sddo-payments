package br.com.sennatech.wasddopayments.infra.exception;

public class ErrorResponses {
    private String message;

    public ErrorResponses(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}