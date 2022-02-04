package week3;


import week3.enums.Roles;
import week3.interfaces.Borrower;
import week3.models.Book;
import week3.models.Request;
import week3.models.Response;

import java.util.*;

public class Librarian extends Person {

    private final Map<Book, Borrower> listOfBorrowedBooks;
    private final Queue<Request> bookRequestQueue;
    private final PriorityQueue<Request> bookRequestPriorityQueue;
    private final Library library;
    private String implementation = "normal";


    private int queue_Size = 10;

    public Librarian(
            String name,
            Roles role,
            Map<Book, Borrower> listOfBorrowedBooks,
            Library library
    ) {
        super(name, role);
        this.listOfBorrowedBooks = listOfBorrowedBooks;
        this.bookRequestQueue = new ArrayDeque<>();
        this.bookRequestPriorityQueue = new PriorityQueue<>();
        this.library = library;
    }

    public Response addBookToLibrary(Person person, Book book, int copies) {


        Response operationResponse;

        if (person instanceof Librarian) {


            if (library.addNewBookToLibrary(book, copies)) {
                operationResponse = new Response(true, "Book Was added successfully", null);
            } else {
                operationResponse = new Response(false, "Book already exist in the Library", null);
            }

            return operationResponse;
        } else {
            return new Response(false, "You do not have the privilege to add books to Library ", null);
        }

    }


    public void requestForBook(Request bookRequest) {

        addBookRequestToQueue(bookRequest);

    }


   public void returnBook(Request returnBookRequest) {

         for(Book book: listOfBorrowedBooks.keySet()){

             if(Objects.equals(book.getName(), returnBookRequest.getBookNameToBorrow())){
                 library.addBookCopies(book.getName(),1);
                 listOfBorrowedBooks.remove(book);
                 notifyResponseListener(new Response(true,"You have successfully returned "+book.getName(),null),
                         returnBookRequest.getBookBorrower());
                 return;
             }

         }

       notifyResponseListener(new Response(false,"You cant return a book that you have not borrowed",null),
               returnBookRequest.getBookBorrower());

   }


    private Response<Book> issueBook(String bookName, Borrower borrower) {
        Response<Book> response = library.checkIfBookExist(bookName);

        if (response.isOperationStatus()) {

            if (listOfBorrowedBooks.get(response.getData()) == borrower) {
                return new Response(false, ((Person)borrower).name+", you have already borrowed " + bookName, null);
            } else {

                Response<Book> response1 = library.getBook(bookName);

                if(response1.isOperationStatus()){
                    listOfBorrowedBooks.put(response1.getData(), borrower);
                    return new Response(true, ((Person)borrower).name+ ", you have been successfully borrowed " + bookName, response1.getData());
                }

                else {

                    return new Response(false, response1.getMessage(), null);
                }
            }
        } else {
            return new Response(false, response.getMessage(), null);
        }

    }


    private void notifyResponseListener(Response<Book> response, Borrower borrower) {


        borrower.getResponseListener().onResponse(response);

    }


    private void addBookRequestToQueue(Request bookRequest) {
        if (Objects.equals(implementation, "normal")) {

            if (bookRequestQueue.size() < queue_Size) {

                bookRequestQueue.add(bookRequest);

            } else {

                notifyResponseListener(new Response(false,   ((Person)bookRequest.getBookBorrower()).name+", the Librarian is no more accepting book requests", null), bookRequest.getBookBorrower());

            }


        } else if (Objects.equals(implementation, "priority")) {


            if (bookRequestPriorityQueue.size() < queue_Size) {

                bookRequestPriorityQueue.add(bookRequest);

            } else {
                notifyResponseListener(new Response(false, ((Person)bookRequest.getBookBorrower()).name+", the Librarian No more Accepting Book Requests.Check Back Later", null), bookRequest.getBookBorrower());

            }
        }

    }


    public void startServicingBookRequests() {

        Queue<Request> queue = bookRequestQueue;
        if(Objects.equals(implementation, "priority")){
            queue = bookRequestPriorityQueue;
        }

        Response<Book> response;
        Request request = queue.poll();
        while (request != null) {
            response = issueBook(request.getBookNameToBorrow(), request.getBookBorrower());
            response.setMessage(((Person)request.getBookBorrower()).name+", "+response.getMessage());
            notifyResponseListener(response, request.getBookBorrower());
            request = queue.poll();
        }
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public void setQueue_Size(int queue_Size) {
        this.queue_Size = queue_Size;
    }



}
