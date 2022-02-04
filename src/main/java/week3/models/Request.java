package week3.models;



import week3.interfaces.Borrower;


public class Request  implements Comparable<Request>{


    private final Borrower bookBorrower;
    private final String bookNameToBorrow;
    private final  int requestPriority;

    public Borrower getBookBorrower() {
        return bookBorrower;
    }

    public String getBookNameToBorrow() {
        return bookNameToBorrow;
    }

    public Request(Borrower bookBorrower, String bookNameToBorrow,int requestPriority) {
        this.bookBorrower = bookBorrower;
        this.bookNameToBorrow = bookNameToBorrow;
        this.requestPriority = requestPriority;
    }


    @Override
    public int compareTo(Request request) {
        int priority = 0;
        if(this.requestPriority < request.requestPriority ){
           return -1;
        }
        else  if(this.requestPriority > request.requestPriority ){
            return  1;
        }

        return priority;
    }
}
