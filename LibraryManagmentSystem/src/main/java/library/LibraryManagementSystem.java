package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryManagementSystem {
    private Map<String, String> users;
    private Map<String, String> books;
    private Map<String, String> borrowedBooks;

    public LibraryManagementSystem() {
        users = new HashMap<>();
        books = new HashMap<>();
        borrowedBooks = new HashMap<>();
    }

    public String registerUser(String userId, String name, String email, String password) {
        if (users.containsKey(userId)) {
            return "User already registered";
        }
        users.put(userId, name);
        return "User registered successfully";
    }

    public String loginUser(String userId, String password) {
        if (!users.containsKey(userId)) {
            return "Invalid login credentials";
        }
        // For simplicity, we skip password checking in this example.
        return "Login successful";
    }

    public String addBook(String bookId, String title) {
        if (books.containsKey(bookId)) {
            return "Book already exists";
        }
        books.put(bookId, title);
        return "Book added successfully";
    }

    public List<String> searchBook(String keyword) {
        List<String> result = new ArrayList<>();
        for (String title : books.values()) {
            if (title.toLowerCase().contains(keyword.toLowerCase())) {
                result.add(title);
            }
        }
        return result;
    }

    public String borrowBook(String userId, String bookId) {
        if (!users.containsKey(userId)) {
            return "User not registered";
        }
        if (!books.containsKey(bookId)) {
            return "Book not found";
        }
        if (borrowedBooks.containsKey(bookId)) {
            return "Book already borrowed";
        }
        borrowedBooks.put(bookId, userId);
        return "Book borrowed successfully";
    }

    public String returnBook(String userId, String bookId) {
        if (!borrowedBooks.containsKey(bookId)) {
            return "Book not borrowed by this user";
        }
        if (!borrowedBooks.get(bookId).equals(userId)) {
            return "Book not borrowed by this user";
        }
        borrowedBooks.remove(bookId);
        return "Book returned successfully";
    }
}
