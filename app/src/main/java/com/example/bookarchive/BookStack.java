package com.example.bookarchive;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class BookStack {
    private static BookStack instance;
    private final Stack<Book> stack;

    private BookStack() {
        stack = new Stack<>();
    }

    public static synchronized BookStack getInstance() {
        if (instance == null) {
            instance = new BookStack();
        }
        return instance;
    }

    public void push(Book book) {
        stack.push(book);
    }

    public Book pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        return null;
    }

    public Book peek() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        return null;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    // Returns books in LIFO order (newest on top)
    public List<Book> getBooksTopToBottom() {
        List<Book> list = new ArrayList<>();
        for (int i = stack.size() - 1; i >= 0; i--) {
            list.add(stack.get(i));
        }
        return list;
    }
}
