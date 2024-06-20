package com.group.bootjpa.service;

import com.group.bootjpa.entity.Book;
import com.group.bootjpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAllList(){
        return repository.findAll();
    }

    public Book register(Book book){
        return repository.save(book);
    }

    public Book getById(Long id){
        Optional<Book> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }else {
            throw new RuntimeException("Book not found with id:" + id);
        }
    }
    @Transactional
    public Book update(Long id, Book reqBook){
        Optional<Book> optional = repository.findById(id);
        if (optional.isPresent()){
            Book book = optional.get();
            book.setTitle(reqBook.getTitle());
            book.setPage(reqBook.getPage());
            return book;
            //return repository.save(book);
        }else {
            throw new RuntimeException("Book not found with id:" + id);
        }
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}