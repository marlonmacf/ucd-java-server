package domain.repositories;

import domain.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * A Repository for the entity User is simply created by extending the
 * CrudRepository interface provided by spring. The following methods are some
 * of the ones available from such interface: save, delete, deleteAll, findOne
 * and findAll. The magic is that such methods must not be implemented, and
 * moreover it is possible create new query methods working only by defining
 * their signature!
 *
 * @author mandrel
 */
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByEmailAndPassword(String email, String password);

} // class UserRepository
