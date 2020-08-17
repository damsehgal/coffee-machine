package com.coffee.machine.model;

public class Response {
    boolean success;
    String reason;

    public Response() {
    }

    public Response(boolean success) {
        this.success = success;
    }

    public Response(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return isSuccess() ? "true" : reason;
    }
}
