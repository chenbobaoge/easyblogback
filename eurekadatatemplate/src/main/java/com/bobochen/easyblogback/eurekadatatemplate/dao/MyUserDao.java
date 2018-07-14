package com.bobochen.easyblogback.eurekadatatemplate.dao;

import com.bobochen.easyblogback.eurekadatatemplate.entitiy.MyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface  MyUserDao extends JpaRepository<MyUserEntity, Integer>,CustomRepository {
    //     List<Easyblog> findAll();
//     Easyblog findOne(Integer id);
//     Easyblog save(Easyblog site);
    //void delete(Integer id);
    List<MyUserEntity> findByUserName(String userName);

    @Query(value = "select * from my_users where user_name like %:name%",nativeQuery = true)
    List<MyUserEntity> findByUserName2(@Param("name") String userName);
}


interface CustomRepository {
    List<MyUserEntity> searchUserName(String key);
}


class CustomRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<MyUserEntity> searchUserName(String key) {
        List<MyUserEntity> es= em.createNativeQuery("select * from my_users where user_name like :name ",MyUserEntity.class).setParameter("name","%"+key+"%").getResultList();
        return es;
//                CriteriaBuilder builder = em.getCriteriaBuilder();
//                CriteriaQuery<Blog> query = builder.createQuery(Blog.class);
//                Root<Blog> root = query.from(Blog.class);
//                query.where(builder.like(root.get("title"), "%" + key + "%"));
//                return em.createQuery(query.select(root)).getResultList();
    }
}