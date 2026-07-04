package xuanmi.ning.common.result;

/**
 * 通用 API 响应包装器
 */
public class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.success = true;
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.success = true;
        result.data = data;
        return result;
    }

    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.success = true;
        result.message = message;
        result.data = data;
        return result;
    }

    public static <T> Result<T> failure(String message) {
        Result<T> result = new Result<>();
        result.success = false;
        result.message = message;
        return result;
    }

    public static <T> Result<T> failure(String message, T data) {
        Result<T> result = new Result<>();
        result.success = false;
        result.message = message;
        result.data = data;
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
