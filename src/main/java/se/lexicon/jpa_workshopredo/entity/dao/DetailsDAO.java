package se.lexicon.jpa_workshopredo.entity.dao;

import se.lexicon.jpa_workshopredo.entity.Details;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface DetailsDAO {

    Optional<Details> findById(int id);
    Collection<Details> findAll();
    Details create(Details details);
    Details update(Details details);
    void delete(Details details) throws DataNotFoundException;
}
