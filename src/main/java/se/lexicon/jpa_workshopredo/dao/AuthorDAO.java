package se.lexicon.jpa_workshopredo.dao;

import se.lexicon.jpa_workshopredo.entity.Author;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import java.util.Collection;

public interface AuthorDAO
{
    Author findById(int id) throws DataNotFoundException;

    Collection<Author> findAll();

    Author create(Author author);

    Author update(Author author);

    void delete(int id) throws DataNotFoundException;
}
