/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Course;
import entities.CourseClass;
import entities.Teacher;
import facades.CourseFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author abed
 */
public class SetupTestDB {
    public static void main(String[] args) {
        
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        
        Course course1 = new Course("Java for beginngers", "For people who has no exp in java before.");
        Course course2 = new Course("Python for beginngers", "For people who has no exp in Python before.");
        
        CourseClass courseClass1 = new CourseClass(1, 40);
        CourseClass courseClass2 = new CourseClass(1, 40);
        
        course1.addCourseClasses(courseClass1);
        course1.addCourseClasses(courseClass2);
        
        Teacher t1 = new Teacher("Jesper", "jesper@gmail.com"); 
        Teacher t2 = new Teacher("Jørg", "Jørg@gmail.com"); 
        Teacher t3 = new Teacher("Hanne", "Hanne@gmail.com"); 
        Teacher t4 = new Teacher("Abed", "Hanne@gmail.com"); 
        
        courseClass1.addTeachers(t3);
        courseClass1.addTeachers(t2);
        courseClass1.addTeachers(t1);
        courseClass2.addTeachers(t3);
        
        em.getTransaction().begin();
        em.persist(courseClass1);
        em.persist(courseClass2);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        courseClass1.addTeachers(t4);
        em.getTransaction().commit();
        
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
        for (Course c : query.getResultList()){
            System.out.println(c.getCourseName());
        }

    }
}
