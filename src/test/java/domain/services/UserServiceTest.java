package domain.services;

import domain.DevelopmentTest;
import domain.entities.User;
import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Component
@RunWith(SpringRunner.class)
@Sql({"classpath:/database/data.sql"})
public class UserServiceTest extends DevelopmentTest {

    @Autowired
    private UserService userService;

    @Test
    public void fetchAll() throws Exception {
        List<User> users = Lists.newArrayList(userService.fetchAll());
        assertTrue("Any user found", (!users.isEmpty()));
    }

    @Test
    public void fetchOne() throws Exception {
        User user = userService.fetchOne(1);
        assertTrue("User not found", (user != null));
    }

    @Test
    public void insert() throws Exception {
        User user;
        String email = "TEST@mail.com";
        String name = "NAME_TEST";
        String password = "123456";
        Byte inspector = 0;
        Byte score = 0;

        user = userService.insert(email, name, password, inspector, score);
        assertTrue("Wrong email value", (user.getEmail().equals(email)));
        assertTrue("Wrong name value", (user.getName().equals(name)));
        assertTrue("Wrong password value", (user.getPassword().equals(password)));
        assertTrue("Wrong inspector value", (user.getInspector().equals(inspector)));
        assertTrue("Wrong score value", (user.getScore().equals(score)));
    }

    @Test
    public void update() throws Exception {
        User user = userService.fetchOne(1);
        assertTrue("User not found", (user != null));

        String email = "NEW_EMAIL@mail.com";
        String name = "NEW_NAME";
        String password = "abcdef";
        Byte inspector = 1;
        Byte score = 10;

        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setInspector(inspector);
        user.setScore(score);

        user = userService.update(user.getId(), user.getEmail(), user.getName(), user.getPassword(),
                user.getInspector(), user.getScore());

        assertTrue("Wrong email value", (user.getEmail().equals(email)));
        assertTrue("Wrong name value", (user.getName().equals(name)));
        assertTrue("Wrong password value", (user.getPassword().equals(password)));
        assertTrue("Wrong inspector value", (user.getInspector().equals(inspector)));
        assertTrue("Wrong score value", (user.getScore().equals(score)));
    }

    @Test
    public void login() throws Exception {
        User user = userService.fetchOne(1);
        assertTrue("User not found", (user != null));

        User loggedUser = userService.login(user.getEmail(), user.getPassword());
        assertTrue("Login returning a wrong user", (loggedUser.getId().equals(user.getId())));

        String wrongEmail = "WRONG_EMAIL";
        String wrongPassword = "WRONG_PASSWORD";

        loggedUser = userService.login(wrongEmail, wrongPassword);
        assertTrue("Login with wrong credentials", (loggedUser == null));
    }

    @Test
    public void ranking() throws Exception {
        List<User> users = Lists.newArrayList(userService.ranking());
        assertTrue("Any user found", (!users.isEmpty()));

        for (int count = 0; count < users.size() - 1; count++) {
            assertTrue("Wrong ranked list", (users.get(count).getScore() >= users.get(count + 1).getScore()));
        }
    }
}