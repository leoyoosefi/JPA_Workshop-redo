package se.lexicon.jpa_workshopredo.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.jpa_workshopredo.entity.AppUser;
import se.lexicon.jpa_workshopredo.entity.Details;
import se.lexicon.jpa_workshopredo.exception.DataNotFoundException;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppUserDAOTest
{
    @Autowired
    private AppUserDAO testObject;

    private AppUser testAppUser;

    @BeforeAll
    public void setUp()
    {
        testAppUser = testObject.create(new AppUser("Leo", "kasdhgku4",
                new Details("leoyoosefi@gmail.com", "Leo", "1991-12-02")));

        testObject.create(new AppUser("Negar", "wgjkdg23",
                new Details("negar@gmail.com", "Negar", "1992-08-07")));
    }

    @Test
    @Order(1)
    @Transactional
    void create()
    {
        AppUser actualData = testObject.create(new AppUser("somePerson", "sytytkk43",
                new Details("qwert@gmail.com", "Test", "1980-10-20")));
        AppUser expectedData = testObject.findById(3).orElse(null);
        assertEquals(expectedData, actualData);
    }

    @Test
    @Order(2)
    @DisplayName("test find id 1 with the result and testAppUser being equal")
    @Transactional
    public void findId1()
    {
        try
        {
            AppUser expectedData = testAppUser;
            AppUser actualData = testObject.findById(1).orElseThrow(() -> new DataNotFoundException("Not Found", " AppUser"));
            assertEquals(expectedData, actualData);
        }
        catch (DataNotFoundException e)
        {
            System.out.println(e.getObjectName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    @Order(3)
    @DisplayName("test get all method from AppUserDAO with result 2")
    public void findAll()
    {
        int actualSize = testObject.findAll().size();
        int expectedSize = 2;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(4)
    @DisplayName("test get delete method from AppUser, then get all method from AppUserDAO with result 1")
    public void delete()
    {
        try
        {
            testObject.delete(testAppUser);
        }
        catch (DataNotFoundException e)
        {
            e.printStackTrace();
        }

        int actualSize = testObject.findAll().size();
        int expectedSize = 1;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    @Order(5)
    void find_gives_OptionalEmpty()
    {
        assertEquals(Optional.empty(), testObject.findById(10));
    }

    @Test
    @Order(6)
    void delete_throws_DataNotFoundException()
    {
        assertThrows(DataNotFoundException.class, () -> testObject.delete(new AppUser("sdfasd4", "dsasdadfwt23rv",
                new Details("asdasd4@gmail.com", "Test", "1980-10-20"))));
    }
}
