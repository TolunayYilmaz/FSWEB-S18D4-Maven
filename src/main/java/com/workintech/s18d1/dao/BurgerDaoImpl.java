package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BurgerDaoImpl implements  BurgerDao{
   private EntityManager entityManager;

   @Autowired
    public BurgerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Burger save(Burger burger) {
     entityManager.persist(burger);
     return  burger;
    }

    @Override
    public Burger findById(long id) {
        if(entityManager.find(Burger.class,id)==null){
            throw new BurgerException("Burger Yok", HttpStatus.NOT_FOUND);
        }
       return entityManager.find(Burger.class,id);
    }

    @Override
    public List<Burger> findAll() {
        String jpql = "SELECT b FROM Burger b";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Burger> findByPrice(int price) {
        String jpql = "SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price DESC";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }


    @Override
    @Transactional
    public List<Burger> findByBreadType(BreadType breadType) {
        String jpql = "SELECT b FROM Burger b WHERE b.breadType = :breadType ORDER BY b.name ASC";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }


    @Override
    @Transactional
    public List<Burger> findByContent(String content) {
        String jpql = "SELECT b FROM Burger b WHERE b.contents LIKE :content";
        TypedQuery<Burger> query = entityManager.createQuery(jpql, Burger.class);
        query.setParameter("content", "%" + content + "%");
        return query.getResultList();
    }

    @Override
    @Transactional
    public Burger update(Burger burger) {
       return entityManager.merge(burger);
    }

    @Override
    @Transactional
    public Burger remove(long id) {
       Burger burger =findById(id);
       entityManager.remove(burger);
       return  burger;
    }
}
