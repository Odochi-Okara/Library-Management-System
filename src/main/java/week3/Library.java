package week3;


import week3.models.Book;
import week3.models.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Library {
    private final Map<Book, Integer> libraryBooks;

    public Library() {
        libraryBooks = new HashMap<>();
    }


    public boolean addNewBookToLibrary(Book book, int copies) {

        if (libraryBooks.containsKey(book))
            return false;
        else {
            libraryBooks.put(book, copies);
            return true;
        }
    }


    public Response<Book> getBook(String bookName) {
        Response<Book> response = checkIfBookExist(bookName);
        if(response.isOperationStatus()){

            if(libraryBooks.get(response.getData()) > 0)
            {libraryBooks.put(response.getData(), (libraryBooks.get(response.getData())-1));}

            else{
                return new Response(false, "All Copies of this Book have been borrowed out", null);
            }
        }

        return response;
    }

    public Response addBookCopies(String bookName, int copiesToAdd) {

        Response<Book> response = checkIfBookExist(bookName);
        if (response.isOperationStatus()) {

            libraryBooks.put(response.getData(), (libraryBooks.get(response.getData()) + copiesToAdd));
            return new Response(true, "Copies of books added", null);

        } else {
            return new Response(false, "No such book exist", null);
        }
    }


    public Response<Book> checkIfBookExist(String bookName) {
        for (Book book : libraryBooks.keySet()) {
            if (Objects.equals(book.getName(), bookName)) {
                return new Response<Book>(true, "Book Exist", book);
            }
        }
        return new Response<>(false, "Book Does Not Exist", null);
    }
}
