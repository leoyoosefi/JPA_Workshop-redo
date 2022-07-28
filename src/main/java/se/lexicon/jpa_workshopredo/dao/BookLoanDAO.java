package se.lexicon.jpa_workshopredo.dao;

import se.lexicon.jpa_workshopredo.entity.BookLoan;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import java.util.Collection;

public interface BookLoanDAO
{
    BookLoan findById(int id) throws DataNotFoundException;

    Collection<BookLoan> findAll();

    BookLoan create(BookLoan bookLoan);

    BookLoan update(BookLoan bookLoan);

    void delete(int id) throws DataNotFoundException;
}