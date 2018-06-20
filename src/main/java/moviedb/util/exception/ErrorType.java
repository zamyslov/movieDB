package moviedb.util.exception;

public enum ErrorType {
    APP_ERROR("error.appError"),
    DATA_NOT_FOUND("error.dataNotFound"),
    VALIDATION_ERROR("error.validationError"),
    DATA_ERROR("error.dataError");

    private final String errorCode;

    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}