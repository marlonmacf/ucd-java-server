package domain.repositories;

import domain.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * A Repository for the entity User is simply created by extending the
 * CrudRepository interface provided by spring.
 *
 * @author mandrel
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmailAndPassword(String email, String password);

    public Iterable<User> findAllByOrderByScoreDesc();

} // class UserRepository
