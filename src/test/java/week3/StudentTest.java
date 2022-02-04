package week3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import week3.enums.Roles;
import week3.interfaces.Borrower;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentTest {

    static Borrower borrower = new Student(
            "Temi",
                    Roles.SENIOR_STUDENT,
                      new Librarian(
                    "Temi",
                    Roles.LIBRARIAN,
                    null,
                    null)
             );

    @BeforeAll
    static void setUp() {
        borrower.addResponseListener(response -> {});
    }

    @Test
    void getResponseListener() {

        assertNotNull(borrower.getResponseListener());

    }
}