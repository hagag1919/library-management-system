package com.example.library_management_system.Services;
import com.example.library_management_system.Database.BookRepository;
import com.example.library_management_system.Models.Book;
import com.example.library_management_system.Services.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;





@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() throws Exception {
        bookRepository.deleteAll(); // Clear data before each test
    }

    @AfterEach
    public void tearDown() throws Exception {
        bookRepository.deleteAll(); // Clear data after each test
    }

    @Test
    public void testAddBook() {
        Book book = new Book(2L,"The Lord of the Rings", "J. R. R. Tolkien",2020,"12nw334knre445t555");
        bookService.addBook(book);

        Optional<Book> savedBook = bookRepository.findById(book.getBookId());
        assertTrue(savedBook.isPresent());
        assertEquals(book, savedBook.get());
    }

    @Test
    public void testGetBookById_existing() {
        Book book = bookRepository.save(new Book(2L,"The Lord of the Rings", "J. R. R. Tolkien",2020,"12nw334knre445t555"));

        Optional<Book> fetchedBook = bookService.getBookById(book.getBookId());
        assertTrue(fetchedBook.isPresent());
        assertEquals(book, fetchedBook.get());
    }

    @Test
    public void testGetBookById_nonExisting() {
        Optional<Book> fetchedBook = bookService.getBookById(1L); // Assuming no book with ID 1
        assertFalse(fetchedBook.isPresent());
    }

    @Test
    public void testGetAllBooks_empty() {
        Iterable<Book> books = bookService.getAllBooks();

        assertFalse(books.iterator().hasNext());
    }

    @Test
    public void testGetAllBooks_withData() {
        // Create and save some books
        bookRepository.save(new Book(2L,"The Lord of the Rings", "J. R. R. Tolkien",2020,"12nw334knre445t555"));
        bookRepository.save(new Book(1L,"The Lord of the Rings ||", "J. R. R. Tolkien",2022,"12nw334knre44545t555"));

        Iterable<Book> books = bookService.getAllBooks();
        int count = 0;
        for (Book book : books) {
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void testDeleteBook() {
        Book book = bookRepository.save(new Book(2L,"The Lord of the Rings", "J. R. R. Tolkien",2020,"12nw334knre445t555"));

        bookService.deleteBook(book.getBookId());

        Optional<Book> deletedBook = bookRepository.findById(book.getBookId());
        assertFalse(deletedBook.isPresent());
    }

    @Test
    public void testUpdateBook_existing() {
        Book book = bookRepository.save(new Book(2L,"The Lord of the Rings", "J. R. R. Tolkien",2020,"12nw334knre445t555"));
        book.setTitle("Dune Messiah");

        bookService.updateBook(book.getBookId(), book);

        Optional<Book> updatedBook = bookRepository.findById(book.getBookId());
        assertTrue(updatedBook.isPresent());
        assertEquals("Dune Messiah", updatedBook.get().getTitle());
    }

    @Test
    public void testUpdateBook_nonExisting() {
        Book book = new Book(2L,"The Lord of the Rings", "J. R. R. Tolkien",2020,"12nw334knre445t555");

        bookService.updateBook(1L, book); // Assuming no book with ID 1

        Optional<Book> updatedBook = bookRepository.findById(1L);
        assertFalse(updatedBook.isPresent());
    }
}
