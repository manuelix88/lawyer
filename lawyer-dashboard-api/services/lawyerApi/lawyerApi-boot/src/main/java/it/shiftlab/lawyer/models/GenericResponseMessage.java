package it.shiftlab.lawyer.models;

import java.io.Serializable;

public class GenericResponseMessage implements Serializable {

    private String status;
    private String message;

    public GenericResponseMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
