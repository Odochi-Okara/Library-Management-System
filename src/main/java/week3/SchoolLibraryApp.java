package week3;

import week3.enums.Roles;
import week3.interfaces.Borrower;
import week3.models.Book;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SchoolLibraryApp {


    private Map<String, String> configMap;
    private Map<Book, Borrower> borrowedBooks;
    private Library library;
    private Librarian librarian;
    private static Scanner scanner;
    private static final String  FILE_PATH = "src/resources/app_config.txt";
    private static final String MAXIMUM_QUEUE_SIZE = "maximum_queue_size";
    private static final String IMPLEMENTATION = "implementation";
    private static final String LIBRARIAN_NAME = "librarian_name";

    public SchoolLibraryApp() {
        configMap = new HashMap<>();
    }

    public static void main(String[] args) {


        SchoolLibraryApp schoolLibraryApp = new SchoolLibraryApp();

        try {

            System.out.println("WELCOME TO TEMI'S SCHOOL LIBRARY SYSTEM");
            System.out.println();
            System.out.println();
            schoolLibraryApp.startApplication();
        }
         catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }


    private void startApplication() throws FileNotFoundException, InterruptedException {
        System.out.println("Loading configuration settings params...");
        sleep(3000);
        loadConfigurationParameters();
        System.out.println("Starting Dependency Injection...");
        sleep(2000);
        initDependencies();
    }


    private void loadConfigurationParameters() throws FileNotFoundException {

        scanner = new Scanner(new FileInputStream(FILE_PATH));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(!line.isEmpty()){
                String[] token = line.split("=");
                configMap.put(token[0], token[1]);
            }

        }
    }


    private void initDependencies() throws InterruptedException {

        System.out.println("Loading list of borrowed books...");
        sleep(3000);
        borrowedBooks = new HashMap<>();
        System.out.println("Loading Library...");
        sleep(3000);
        library = new Library();
        System.out.println("Instantiating the Librarian...");
        sleep(5000);
        librarian = new Librarian(configMap.get(LIBRARIAN_NAME), Roles.LIBRARIAN,borrowedBooks,library);
        librarian.setImplementation(configMap.get(IMPLEMENTATION));
        librarian.setQueue_Size(Integer.parseInt(configMap.get(MAXIMUM_QUEUE_SIZE )));
        System.out.println("Loading Library books...");
        sleep(5000);
        addBooksToLibrary();
        System.out.println("Librarian Started accepting Requests for Books...");
        sleep(5000);
        createStudentsAndTeachers ();
        System.out.println("Librarian started servicing to Book requests...\n\n");
        sleep(5000);
        librarian.startServicingBookRequests();





    }



    private void sleep(int millisecond) throws InterruptedException{
        Thread.sleep(millisecond);
    }

    private void createStudentsAndTeachers (){

        Student temi = new Student("TEMI", Roles.SENIOR_STUDENT,librarian);
        temi.addResponseListener(
                response ->{
                    System.out.println(response.getMessage());
                }
        );

        Student vikki = new Student("VIKKI",Roles.JUNIOR_STUDENT,librarian);
        vikki.addResponseListener(
                response ->{
                    System.out.println(response.getMessage());}
        );
        Student odochi = new Student("ODOCHI",Roles.SENIOR_STUDENT,librarian);
        odochi.addResponseListener(
                response ->{
                    System.out.println(response.getMessage());}
        );

        Student golden = new Student("GOLDEN",Roles.JUNIOR_STUDENT,librarian);
        golden.addResponseListener(
                response ->{
                    System.out.println(response.getMessage());}
        );

        Teacher daro = new Teacher("DARO",Roles.TEACHER,librarian);
        Teacher francis = new Teacher("FRANCIS",Roles.TEACHER,librarian);
        Teacher rex = new Teacher("REX",Roles.TEACHER,librarian);



        daro.addResponseListener(response ->{
            System.out.println(response.getMessage());});
        francis.addResponseListener(response ->{
            System.out.println(response.getMessage());});
        rex.addResponseListener(response ->{
            System.out.println(response.getMessage());});
        daro.borrowBook("Mathematics");
        francis.borrowBook("English");
        rex.borrowBook("Biology");


        golden.borrowBook("Biology");
        temi.borrowBook("Mathematics");
        odochi.borrowBook("Mathematics");
        temi.borrowBook("English");
        golden.borrowBook("Mathematics");
        temi.borrowBook("English");










    }


    private void addBooksToLibrary(){
        librarian.addBookToLibrary(librarian,new Book("Mathematics","Temi"),3);
        librarian.addBookToLibrary(librarian,new Book("English","Temi"),5);
        librarian.addBookToLibrary(librarian,new Book("Biology","Temi"),5);
        librarian.addBookToLibrary(librarian,new Book("Geography","Temi"),5);
    }
}
