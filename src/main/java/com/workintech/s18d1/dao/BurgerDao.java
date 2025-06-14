package com.workintech.s18d1.dao;

import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.entity.BreadType;

import java.util.List;

public interface BurgerDao {
   Burger save(Burger burger);
   Burger findById(long id);
   List<Burger> findAll();
   List<Burger> findByPrice(int price);
   List<Burger> findByBreadType(BreadType breadType);
   List<Burger> findByContent(String contents);
   Burger update(Burger burger);
   Burger remove (long id);

}
