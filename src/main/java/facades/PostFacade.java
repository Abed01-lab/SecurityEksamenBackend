/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.PostDTO;
import entities.Post;
import entities.UserInfo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author abedh
 */
public class PostFacade {

  private static PostFacade instance;
  private static EntityManagerFactory emf;

  private PostFacade() {}

  public static PostFacade getFacade(EntityManagerFactory _emf) {
    if (instance == null) {
      emf = _emf;
      instance = new PostFacade();
    }
    return instance;
  }

  private EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  //Get post
  public List<PostDTO> getAllPost(String uid) {
    EntityManager em = getEntityManager();
    List<PostDTO> posts = new ArrayList();
    TypedQuery<UserInfo> query = em
      .createQuery(
        "SELECT u FROM UserInfo u WHERE u.uid = :input",
        UserInfo.class
      )
      .setParameter("input", uid);

    UserInfo userInfo = query.getSingleResult();
    for (Post p : userInfo.getPosts()) {
      posts.add(new PostDTO(p.getMessage()));
    }
    return posts;
  }

  //Add post
  public List<PostDTO> addPost(PostDTO postDTO, String uid) {
    EntityManager em = getEntityManager();
    TypedQuery<UserInfo> query = em
      .createQuery(
        "SELECT u FROM UserInfo u WHERE u.uid = :input",
        UserInfo.class
      )
      .setParameter("input", uid);

    UserInfo userInfo = query.getSingleResult();
    Post post = new Post(postDTO.getMessage());
    try {
      em.getTransaction().begin();
      userInfo.addPost(post);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
    return getAllPost(uid);
  }
}
