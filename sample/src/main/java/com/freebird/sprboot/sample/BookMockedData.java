package com.freebird.sprboot.sample;

import java.util.ArrayList;
import java.util.List;

public class BookMockedData {
    //list of books
    private List<Book> books;

    private static BookMockedData instance = null;
    public static BookMockedData getInstance(){
         if(instance == null){
             instance = new BookMockedData();
         }
         return instance;
    }


    public BookMockedData(){
        books = new ArrayList<Book>();
        books.add(new Book(1, "A Tale of Two Cities", "Charles Dickens"));
        books.add(new Book(2, "Treasure Island", "Robert Louie Stevenson"));
        books.add(new Book(3, "Atomic Habits", "James Clear"));
        books.add(new Book(4, "Jaya", "Devdutt Pattnaik"));
        books.add(new Book(5, "An Era of Darkness", "Shashi Tharoor"));
        books.add(new Book(6, "Clear Light of Day", "Anita Desai"));
    }

    // return all books
    public List<Book> fetchBooks() {
        return books;
    }

    // return book by id
    public Book getBookById(int id) {
        for(Book b: books) {
            if(b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    // search books
    public List<Book> searchBook(String searchTerm) {
        List<Book> searchedBooks = new ArrayList<Book>();
        for(Book b: books) {
            if(b.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
               b.getAuthor().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchedBooks.add(b);
            }
        }

        return searchedBooks;
    }

    // create book
    public Book createBook(int id, String title, String author) {
        Book newBook = new Book(id, title, author);
        books.add(newBook);
        return newBook;
    }

    // update book
    public Book updateBook(int id, String title, String author) {
        for(Book b: books) {
            if(b.getId() == id) {
                int bookIndex = books.indexOf(b);
                b.setTitle(title);
                b.setAuthor(author);
                books.set(bookIndex, b);
                return b;
            }

        }

        return null;
    }

    // delete book by id
    public boolean delete(int id){
        int booksIndex = -1;
        for(Book b: books) {
            if(b.getId() == id) {
            	booksIndex = books.indexOf(b);
                continue;
            }
        }
        if(booksIndex > -1){
            books.remove(booksIndex);
        }
        return true;
    }

}
