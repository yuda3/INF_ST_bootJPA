package com.group.bootjpa.controller;

import com.group.bootjpa.entity.Book;
import com.group.bootjpa.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookRestController {

    @Autowired
    public BookService service;

    @GetMapping("/books")
    public ResponseEntity<?> getAllList(){
        return new ResponseEntity<>(service.getAllList(), HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> register(@RequestBody Book book){
        return new ResponseEntity<>(service.register(book), HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Book not found with id : "+ id,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book reqBook){
        try {
            Book book = service.update(id, reqBook);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Something is wrong : ",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        try {
            service.deleteById(id);
            return new ResponseEntity<>("Deleted book with id : "+ id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Failed : " + id,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
