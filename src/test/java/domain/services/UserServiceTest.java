package domain.services;

import domain.entities.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class UserServiceTest {

    public UserServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Ignore
    public void testFetchAll() {
        System.out.println("fetchAll");
        UserService instance = new UserService();
        Iterable<User> expResult = null;
        Iterable<User> result = instance.fetchAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testFetchOne() {
        System.out.println("fetchOne");
        Integer userId = null;
        UserService instance = new UserService();
        User expResult = null;
        User result = instance.fetchOne(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testInsert() {
        System.out.println("insert");
        String email = "";
        String name = "";
        String password = "";
        Byte inspector = null;
        Byte score = null;
        UserService instance = new UserService();
        User expResult = null;
        User result = instance.insert(email, name, password, inspector, score);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testUpdate() {
        System.out.println("update");
        Integer userId = null;
        String email = "";
        String name = "";
        String password = "";
        Byte inspector = null;
        Byte score = null;
        UserService instance = new UserService();
        User expResult = null;
        User result = instance.update(userId, email, name, password, inspector, score);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testDelete() {
        System.out.println("delete");
        Integer userId = null;
        UserService instance = new UserService();
        Boolean expResult = null;
        Boolean result = instance.delete(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testLogin() {
        System.out.println("login");
        String email = "";
        String password = "";
        UserService instance = new UserService();
        User expResult = null;
        User result = instance.login(email, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testRanking() {
        System.out.println("ranking");
        UserService instance = new UserService();
        Iterable<User> expResult = null;
        Iterable<User> result = instance.ranking();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
