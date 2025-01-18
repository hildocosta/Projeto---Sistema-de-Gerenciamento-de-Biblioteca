package com.hildo.costa.library.repository;

import com.hildo.costa.library.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository  extends MongoRepository<Book, String> {
}
