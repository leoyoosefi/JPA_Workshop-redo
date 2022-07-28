package se.lexicon.jpa_workshopredo.dao.repository;

import org.springframework.stereotype.Repository;
import se.lexicon.jpa_workshopredo.dao.BookLoanDAO;
import se.lexicon.jpa_workshopredo.entity.BookLoan;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class BookLoanDAOImpl implements BookLoanDAO
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BookLoan findById(int id) throws DataNotFoundException
    {
        return Optional.ofNullable(entityManager.find(BookLoan.class, id)).orElseThrow(() -> new DataNotFoundException("Not Found", "BookLoan"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<BookLoan> findAll()
    {
        return (List<BookLoan>) entityManager.createQuery("select bl from BookLoan bl").getResultList();
    }

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan)
    {
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan is null");
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan)
    {
        if (bookLoan == null) throw new IllegalArgumentException("BookLoan is null");
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void delete(int id) throws DataNotFoundException
    {
        Optional.ofNullable(findById(id)).ifPresent(entityManager::remove);
    }
}
