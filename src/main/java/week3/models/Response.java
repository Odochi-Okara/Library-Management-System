package week3.models;

public class Response<T> {


    private final boolean isOperationSuccessFull;
    private  String  message;

    public T getData() {
        return data;
    }

    private T data;

    public Response(boolean isOperationSuccessFull, String message,T data) {
        this.isOperationSuccessFull = isOperationSuccessFull;
        this.message = message;
        this.data = data;
    }

    public boolean isOperationStatus() {
        return isOperationSuccessFull;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
