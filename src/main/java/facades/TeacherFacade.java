/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.TeacherDTO;
import entities.Teacher;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author abed
 */
public class TeacherFacade {
    private static TeacherFacade instance;
    private static EntityManagerFactory emf;
    
    private TeacherFacade(){}
    
     public static TeacherFacade getFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TeacherFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //add teacher
    public void addTeacher(TeacherDTO teacherDTO){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(new Teacher(teacherDTO.getName(), teacherDTO.getEmail()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<TeacherDTO> getAllTeachers(){
        EntityManager em = getEntityManager();
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        List<TeacherDTO> teachers = new ArrayList<>();
        for (Teacher t : query.getResultList()){
            teachers.add(new TeacherDTO(t));
        }
        return teachers;
    }
}
