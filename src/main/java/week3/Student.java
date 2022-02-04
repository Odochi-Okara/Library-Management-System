package week3;

import week3.enums.Roles;
import week3.interfaces.Borrower;
import week3.interfaces.ResponseListener;
import week3.models.Request;

public class Student extends Person  implements Borrower {

    private final Librarian librarian;
    private ResponseListener responseListener;
    private int priority;

    public Student(String name, Roles role,Librarian librarian){
        super(name, role);
        if(role == Roles.JUNIOR_STUDENT){
            this.priority = 3;
        }
        else if(role == Roles.SENIOR_STUDENT){
            this.priority = 2;
        }
        this.librarian = librarian;
    }

    @Override
    public void borrowBook(String bookName) {
        librarian.requestForBook(new Request(this,bookName,this.priority));
    }

    @Override
    public void returnBook(String bookName) {
     librarian.returnBook(new Request(this,bookName,this.priority));
    }



    public void addResponseListener(ResponseListener listener){

        this.responseListener = listener;
    }

    @Override
    public ResponseListener getResponseListener()
    {
        return this.responseListener;
    }


}
