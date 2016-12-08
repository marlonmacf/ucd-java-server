package domain.services;

import domain.DevelopmentTest;
import domain.entities.User;
import domain.repositories.UserRepository;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Component
@Transactional
@RunWith(SpringRunner.class)
@Sql({"classpath:/database/data.sql"})
public class UserServiceTest extends DevelopmentTest {

    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Mock
    @Autowired
    private UserRepository userRepository;

    @InjectMocks
    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mvc = MockMvcBuilders.standaloneSetup(userService).build();
    }

    @Test
    public void fetchAll() throws Exception {
        List<User> users = getEntityListStubData();
        when(userService.fetchAll()).thenReturn(users);
        assertTrue(users.equals(userService.fetchAll()));
    }

    @Test
    public void fetchOne() throws Exception {
        User user = getEntityStubData();
        when(userService.fetchOne(1)).thenReturn(user);
        assertTrue(user.equals(userService.fetchOne(1)));
    }
    
    private List<User> getEntityListStubData() {
        List<User> list = new ArrayList<>();
        list.add(getEntityStubData());
        return list;
    }

    private User getEntityStubData() {
        byte inspector = 0;
        byte score = 0;

        User user = new User();
        user.setId(1);
        user.setEmail("marlonmacf@gmail.com");
        user.setName("Marlon Andrel");
        user.setPassword("123456");
        user.setInspector(inspector);
        user.setScore(score);

        return user;
    }
}
