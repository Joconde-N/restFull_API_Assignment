package auca.ac.rw.question1_library_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import auca.ac.rw.question1_library_api.model.Book;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final List<Book> books = new ArrayList<>();
    private Long nextId = 4L;

    public BookController(){
        books.add(new Book(1L, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "9780590353427", 1998));
        books.add(new Book(2L, "The Lord of the Rings", "J.R.R. Tolkien", "9780544003415", 1954));
        books.add(new Book(3L, "pride and Prejudice", "Jane Austen", "9780141439518", 1813));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(books);//200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return ResponseEntity.ok(book);//200
            }
        }
        return ResponseEntity.notFound().build();//404
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title){
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return ResponseEntity.ok(result);//200
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        book.setId(nextId++);
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);//201
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        for (Book book : books) {
            if (book.getId().equals(id)) {
                books.remove(book);
                return ResponseEntity.noContent().build();//204
            }
        }
        return ResponseEntity.notFound().build();//404
    }
}
