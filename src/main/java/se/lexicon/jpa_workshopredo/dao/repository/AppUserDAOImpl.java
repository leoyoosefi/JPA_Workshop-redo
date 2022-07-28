package se.lexicon.jpa_workshopredo.dao.repository;

import org.springframework.stereotype.Repository;
import se.lexicon.jpa_workshopredo.dao.AppUserDAO;
import se.lexicon.jpa_workshopredo.entity.AppUser;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Repository
public class AppUserDAOImpl implements AppUserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<AppUser> findById(int id) {
        return Optional.ofNullable(entityManager.find(AppUser.class,id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<AppUser> findAll() {
        return (Collection<AppUser>) entityManager.createQuery("select au from AppUser au").getResultList();
    }

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser is null");
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser Update is null");
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void delete(AppUser appUser) throws DataNotFoundException {

        findById(appUser.getAppUserId()).orElseThrow(() -> new DataNotFoundException("Not Found", " AppUser"));
        entityManager.remove(entityManager.contains(appUser) ? appUser : entityManager.merge(appUser));

    }
}
