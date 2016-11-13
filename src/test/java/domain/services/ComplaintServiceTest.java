package domain.services;

import domain.entities.Complaint;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

import java.util.ArrayList;

public class ComplaintServiceTest {

    public ComplaintServiceTest() {
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
        ComplaintService instance = new ComplaintService();
        Iterable<Complaint> expResult = null;
        Iterable<Complaint> result = instance.fetchAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testFetchOne() {
        System.out.println("fetchOne");
        Integer complaintId = null;
        ComplaintService instance = new ComplaintService();
        Complaint expResult = null;
        Complaint result = instance.fetchOne(complaintId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testInsert() {
        System.out.println("insert");
        String latitude = "";
        String longitude = "";
        String description = "";
        Integer idUser = null;
        ComplaintService instance = new ComplaintService();
        Complaint expResult = null;
        ArrayList<String> photosBase = new ArrayList<>();
        Complaint result = instance.insert(latitude, longitude, description, idUser, photosBase);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testUpdate() {
        System.out.println("update");
        Integer idComplaint = null;
        String description = "";
        ComplaintService instance = new ComplaintService();
        Complaint expResult = null;
        Complaint result = instance.update(idComplaint, description);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testDelete() {
        System.out.println("delete");
        Integer idComplaint = null;
        ComplaintService instance = new ComplaintService();
        Boolean expResult = null;
        Boolean result = instance.delete(idComplaint);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testInspect() {
        System.out.println("inspect");
        Integer idComplaint = null;
        Integer idInspector = null;
        ComplaintService instance = new ComplaintService();
        Complaint expResult = null;
        Complaint result = instance.inspect(idComplaint, idInspector);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testCheck() {
        System.out.println("check");
        Integer idComplaint = null;
        ComplaintService instance = new ComplaintService();
        Complaint expResult = null;
        Complaint result = instance.check(idComplaint);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    @Ignore
    public void testDenounce() {
        System.out.println("denounce");
        Integer idComplaint = null;
        ComplaintService instance = new ComplaintService();
        Complaint expResult = null;
        Complaint result = instance.denounce(idComplaint);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
