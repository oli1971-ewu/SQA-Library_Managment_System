package library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryManagementSystemTest {
    private LibraryManagementSystem lms;

    @BeforeEach
    public void setUp() {
        lms = new LibraryManagementSystem();
    }

    @Test
    public void testRegisterUser() {
        String result = lms.registerUser("user01", "Alice", "alice@example.com", "password123");
        assertEquals("User registered successfully", result);
        result = lms.registerUser("user01", "Alice", "alice@example.com", "password123");
        assertEquals("User already registered", result);
    }

    @Test
    public void testLoginUser() {
        lms.registerUser("user02", "Bob", "bob@example.com", "securepass");
        String result = lms.loginUser("user02", "securepass");
        assertEquals("Login successful", result);
        result = lms.loginUser("user02", "wrongpass");
        assertEquals("Invalid login credentials", result);
        result = lms.loginUser("user03", "nopass");
        assertEquals("Invalid login credentials", result);
    }

    @Test
    public void testAddBook() {
        String result = lms.addBook("book01", "Python Programming");
        assertEquals("Book added successfully", result);
        result = lms.addBook("book01", "Python Programming");
        assertEquals("Book already exists", result);
    }

    @Test
    public void testSearchBook() {
        lms.addBook("book01", "Python Programming");
        lms.addBook("book02", "Data Structures");
        assertEquals(1, lms.searchBook("python").size());
        assertEquals("Python Programming", lms.searchBook("python").get(0));
    }

    @Test
    public void testBorrowBook() {
        lms.registerUser("user04", "Carol", "carol@example.com", "mypassword");
        lms.addBook("book03", "Machine Learning");
        String result = lms.borrowBook("user04", "book03");
        assertEquals("Book borrowed successfully", result);
        result = lms.borrowBook("user04", "book03");
        assertEquals("Book already borrowed", result);
        result = lms.borrowBook("user05", "book03");
        assertEquals("User not registered", result);
        result = lms.borrowBook("user04", "book04");
        assertEquals("Book not found", result);
    }

    @Test
    public void testReturnBook() {
        lms.registerUser("user06", "David", "david@example.com", "pass456");
        lms.addBook("book05", "Deep Learning");
        lms.borrowBook("user06", "book05");
        String result = lms.returnBook("user06", "book05");
        assertEquals("Book returned successfully", result);
        result = lms.returnBook("user06", "book05");
        assertEquals("Book not borrowed by this user", result);
    }
}
