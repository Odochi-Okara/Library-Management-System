package week3.interfaces;



public interface Borrower {


    public void borrowBook(String bookName);
    public void returnBook(String bookName);
    public void addResponseListener(ResponseListener listener);
    public ResponseListener getResponseListener();
}
