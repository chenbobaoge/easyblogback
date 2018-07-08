package com.bobochen.easyblogback.dao;


import com.bobochen.easyblogback.entity.Easyblog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface  EasyblogDao extends JpaRepository<Easyblog, Integer> ,CustomRepository {
//     List<Easyblog> findAll();
//     Easyblog findOne(Integer id);
//     Easyblog save(Easyblog site);
     //void delete(Integer id);
        List<Easyblog> findByUserName(String userName);

        @Query(value = "select * from my_users where user_name like %:name%",nativeQuery = true)
        List<Easyblog> findByUserName2(@Param("name") String userName);
}


interface CustomRepository {
        List<Easyblog> searchUserName(String key);
}

class CustomRepositoryImpl implements CustomRepository {

        @PersistenceContext
        private EntityManager em;
        @Override
        public List<Easyblog> searchUserName(String key) {
               List<Easyblog> es= em.createNativeQuery("select * from my_users where user_name like '%"+key+"%' ",Easyblog.class).getResultList();
               return es;
//                CriteriaBuilder builder = em.getCriteriaBuilder();
//                CriteriaQuery<Blog> query = builder.createQuery(Blog.class);
//                Root<Blog> root = query.from(Blog.class);
//                query.where(builder.like(root.get("title"), "%" + key + "%"));
//                return em.createQuery(query.select(root)).getResultList();
        }
}