package se.lexicon.jpa_workshopredo.entity.dao;

import org.springframework.stereotype.Repository;
import se.lexicon.jpa_workshopredo.entity.AppUser;
import se.lexicon.jpa_workshopredo.entity.Details;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public class DetailsDAOImpl implements DetailsDAO {

    private EntityManager entityManager;

    @Override
    public Optional<Details> findById(int id) {
        return Optional.ofNullable(entityManager.find(Details.class, id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Details> findAll() {
        return (Collection<Details>) entityManager.createQuery("select d from Details d").getResultList();
    }

    @Override
    @Transactional
    public Details create(Details details) {

        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void delete(Details details) throws DataNotFoundException {

        findById(details.getDetailsId()).orElseThrow(() -> new DataNotFoundException("Not Found", "AppUser"));
        entityManager.remove(entityManager.contains(details) ? details: entityManager.merge(details));

    }
}
