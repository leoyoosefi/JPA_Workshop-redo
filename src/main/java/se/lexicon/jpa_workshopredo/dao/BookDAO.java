package se.lexicon.jpa_workshopredo.dao;

import se.lexicon.jpa_workshopredo.entity.Book;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import java.util.Collection;

public interface BookDAO
{
    Book findById(int id) throws DataNotFoundException;

    Collection<Book> findAll();

    Book create(Book book);

    Book update(Book book);

    void delete(int id) throws DataNotFoundException;
}