package com.bobochen.easyblogback.dao;


import com.bobochen.easyblogback.entity.Easyblog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  EasyblogDao extends CrudRepository<Easyblog, Integer> {
//     List<Easyblog> findAll();
//     Easyblog findOne(Integer id);
//     Easyblog save(Easyblog site);
     //void delete(Integer id);

}
