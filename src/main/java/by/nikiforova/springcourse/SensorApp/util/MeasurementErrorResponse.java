package by.nikiforova.springcourse.SensorApp.util;

public class MeasurementErrorResponse {
    private String errors;
    private long timestamp;

    public MeasurementErrorResponse(String errors, long timestamp) {
        this.errors = errors;
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
