/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package facade;

import dto.CourseDTO;
import entities.Course;
import entities.CourseClass;
import facades.CourseFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author abed
 
public class CourseFacadeTest {
    private static EntityManagerFactory emf;
    private static CourseFacade facade;
    private Course c1, c2;
    private CourseClass cc1, cc2;
    
    @BeforeAll
    public static void setUpClass() {

        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CourseFacade.getFacade(emf);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Course c").executeUpdate();
        em.createNativeQuery("ALTER TABLE `Course` AUTO_INCREMENT = 1").executeUpdate();
    }
    
    @BeforeEach
    public void setUp() {
        c1 = new Course("Course 1", "Course 1 description");
        c2 = new Course("Course 2", "Course 2 description");
        
        cc1 = new CourseClass(1, 40);
        
        c1.addCourseClasses(cc1);
        c1.addCourseClasses(cc2);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Course c").executeUpdate();
            em.persist(c1);
            em.persist(c2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Course c").executeUpdate();
        em.createNativeQuery("ALTER TABLE `Course` AUTO_INCREMENT = 1").executeUpdate();
        em.getTransaction().commit();
    }
    
    @Test
    public void getListCoursesTest(){
        List<CourseDTO> result = facade.getListCourses();
        assertEquals(2, result.size());
    }
    
    @Test
    public void updateCourseTest(){
        facade.updateCourse(new CourseDTO(1, "test", "test tester"));
        assertThat("test", not(equalTo(c1.getCourseName())));
    }
}*/
