package week3.interfaces;


import week3.models.Book;
import week3.models.Response;

public interface ResponseListener {

    public void onResponse(Response<Book> response);
}
