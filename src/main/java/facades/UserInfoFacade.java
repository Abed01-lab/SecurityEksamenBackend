/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.UIDDTO;
import dto.UserInfoDTO;
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
public class UserInfoFacade {

  private static UserInfoFacade instance;
  private static EntityManagerFactory emf;

  private UserInfoFacade() {}

  public static UserInfoFacade getFacade(EntityManagerFactory _emf) {
    if (instance == null) {
      emf = _emf;
      instance = new UserInfoFacade();
    }
    return instance;
  }

  private EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  //add userInfo
  public UserInfoDTO addUserInfo(UserInfoDTO userInfoDTO) {
    EntityManager em = getEntityManager();

    try {
      em.getTransaction().begin();
      em.persist(
        new UserInfo(
          userInfoDTO.getUid(),
          userInfoDTO.getName(),
          userInfoDTO.getPhoneNumber(),
          userInfoDTO.getSex()
        )
      );
      em.getTransaction().commit();
    } finally {
      em.close();
    }
    return userInfoDTO;
  }

  //get userInfo
  public UserInfoDTO getUserInfo(String uid) {
    EntityManager em = getEntityManager();
    UserInfoDTO userInfoDTO;
    try {
      TypedQuery<UserInfo> query = em
        .createQuery(
          "SELECT u FROM UserInfo u WHERE u.uid = :input",
          UserInfo.class
        )
        .setParameter("input", uid);
      UserInfo userInfo = query.getSingleResult();
      userInfoDTO =
        new UserInfoDTO(
          userInfo.getUid(),
          userInfo.getName(),
          userInfo.getPhoneNumber(),
          userInfo.getSex()
        );
      return userInfoDTO;
    } catch (Exception e) {
      return null;
    }
  }

  //edit userInfo

  //get All UserIngo
  public List<UserInfoDTO> getAllUserInfo() {
    EntityManager em = getEntityManager();
    TypedQuery<UserInfo> query = em.createQuery(
      "SELECT u FROM UserInfo u",
      UserInfo.class
    );
    List<UserInfoDTO> userInfoDTOs = new ArrayList();
    for (UserInfo u : query.getResultList()) userInfoDTOs.add(
      new UserInfoDTO(u)
    );

    return userInfoDTOs;
  }
}
