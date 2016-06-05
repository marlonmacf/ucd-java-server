package domain.repositories;

import domain.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmailAndPassword(String email, String password);

    public Iterable<User> findAllByOrderByScoreDesc();

}
