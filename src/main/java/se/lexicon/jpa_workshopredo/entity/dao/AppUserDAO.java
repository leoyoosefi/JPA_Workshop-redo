package se.lexicon.jpa_workshopredo.entity.dao;

import se.lexicon.jpa_workshopredo.entity.AppUser;

import java.util.Collection;
import java.util.Optional;

public interface AppUserDAO {

    Optional<AppUser> findById(int id);
    Collection<AppUser> findAll();
    AppUser create(AppUser appUser);
    AppUser update(AppUser appUser);
    void delete(AppUser appUser);

}
