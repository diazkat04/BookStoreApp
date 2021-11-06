package edu.fiu.cen4010.g5.BookStoreApp.service;

import edu.fiu.cen4010.g5.BookStoreApp.model.Book;
import edu.fiu.cen4010.g5.BookStoreApp.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.insert(book);
    }

    public void updateBook(Book book) {
        Book savedBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Book by ID %s", book.getId())
                ));

        savedBook.setISBN(book.getISBN());
        savedBook.setTitle(book.getTitle());
        savedBook.setDescription(book.getDescription());
        savedBook.setPrice(book.getPrice());
        savedBook.setAuthorIDs(book.getAuthorIDs());
        savedBook.setGenre(book.getGenre());
        savedBook.setBookPublisher(book.getBookPublisher());
        savedBook.setPublishedYear(book.getPublishedYear());
        savedBook.setCopiesSold(book.getCopiesSold());
        bookRepository.save(savedBook);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByISBN(String isbn) {
        return bookRepository.findByISBN(isbn).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Book by ISBN %s", isbn)
        ));
    }

    public List<Book> getBookByAuthor(String authorID) {

        List<Book> allBooks = getAllBooks();
        List<Book> booksByAuthor = null;

        for (Book book : allBooks) {
            for (String id : book.getAuthorIDs()) {
                if (id.equals(authorID)) {
                    booksByAuthor.add(book);
                    continue;
                }
            }
        }

        if (booksByAuthor.isEmpty()) {
            throw new RuntimeException(String.format("Cannot find Book by author with ID " + authorID));
        }
        else {
            return booksByAuthor;
        }

    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public boolean validateBook(String id) {
        // query the database for books with this id
        List<Book> repositoryResults = bookRepository.findByBookId(id).get();

        // return true if there is a book with this id, and false if not
        return !repositoryResults.isEmpty();
    }

}
//working on stuff down here
/*
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.insert(book);
    }

    public void updateBook(Book book) {
        Book savedBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Book by ID %s", book.getId())
                ));

        savedBook.setISBN(book.getISBN());
        savedBook.setTitle(book.getTitle());
        savedBook.setDescription(book.getDescription());
        savedBook.setPrice(book.getPrice());
        savedBook.setAuthor(book.getAuthor());
        savedBook.setGenre(book.getGenre());
        savedBook.setBookPublisher(book.getBookPublisher());
        savedBook.setPublishedYear(book.getPublishedYear());
        savedBook.setCopesSold(book.getCopiesSold());
        bookRepository.save(savedBook);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public List<Book> getBookByID(String id) {
        return bookRepository.findByID(id);
    }
    public List<Book> getBookByISBN(String isbn) {
        return bookRepository.findByISBN(isbn);
    }
    public List<Book> getBooksByTITLE(String title){return bookRepository.findByTITLE(title);}
    public List<Book> getBookByDESCRIPTION(String description) {
        return bookRepository.findByDESCRIPTION(description);
    }
    public List<Book> getBooksByPRICE(double price) {
        return bookRepository.findByPRICE(price);
    }
    public List<Book> getBooksByAUTHOR(String author) {
        return bookRepository.findByAUTHOR(author);
    }
    public List<Book> getBooksByGENRE(String genre) {
        return bookRepository.findByGENRE(genre);
    }
    public List<Book> getBooksByBOOKPUBLISHER(String publisher) {
        return bookRepository.findByBOOKPUBLISHER(publisher);
    }
    public List<Book> getBooksByPUBLISHEDYEAR(int publishedYear) {
        return bookRepository.findByPUBLISHEDYEAR(publishedYear);
    }
    public List<Book> getBooksByCOPIESSOLD(int copiesSold) {
        return bookRepository.findByCOPIESSOLD(copiesSold);
    }
    //public Book getBookByPRICE(String PRICE) {
        /*return bookRepository.findByPRICE(PRICE).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find Book by PRICE %s", PRICE)
        ));
    //}

    //public void deleteBook(String id) {
      //  bookRepository.deleteById(id);
   // }

}*/


