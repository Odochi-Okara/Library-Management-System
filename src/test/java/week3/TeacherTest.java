package week3;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import week3.enums.Roles;
import week3.interfaces.Borrower;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TeacherTest {


    static Borrower borrower = new Student(
            "Daro",
            Roles.TEACHER,
            new Librarian(
                    "Temi",
                    Roles.LIBRARIAN,
                    null,
                    null)
    );

    @BeforeEach
    void setUp() {
        borrower.addResponseListener(response -> {});
    }

    @Test
    void getResponseListener() {
        assertNotNull(borrower.getResponseListener());
    }
}