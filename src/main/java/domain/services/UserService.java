package domain.services;

import domain.entities.User;
import domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> fetchAll() {
        try {
            return userRepository.findAll();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public User fetchOne(Integer userId) {
        try {
            return userRepository.findOne(userId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public User insert(String email, String name, String password, Byte inspector, Byte score) {
        try {
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setInspector(inspector);
            user.setScore(score);
            return userRepository.save(user);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public User update(Integer userId, String email, String name, String password, Byte inspector, Byte score) {
        try {
            User user = userRepository.findOne(userId);
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setInspector(inspector);
            user.setScore(score);
            return userRepository.save(user);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Boolean delete(Integer userId) {
        try {
            userRepository.delete(new User(userId));
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);

        }
    }

    public User login(String email, String password) {
        try {
            return userRepository.findByEmailAndPassword(email, password);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Iterable<User> ranking() {
        try {
            return userRepository.findAllByOrderByScoreDesc();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
