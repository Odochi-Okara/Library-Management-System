package week3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import week3.models.Book;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibrarianTest {


    static Library library;

    @BeforeAll
    static void setUp() {
        library = new Library();
    }

    @Test
    void addBookToLibrary() {
        assertTrue(library.addNewBookToLibrary(new Book("Mathematics","Temi"),10));
    }
}