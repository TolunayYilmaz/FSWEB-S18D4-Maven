package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {

    private BurgerDao burgerDao;

@Autowired
public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @PostMapping()
    public Burger save(@RequestBody Burger burger){
    return burgerDao.save(burger);
    }

    @GetMapping()
    public List<Burger>  findAll(){
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable long id) {
        if(burgerDao.findById(id)==null){
            throw new BurgerException("Burger Yok", HttpStatus.NOT_FOUND);
        }
        return burgerDao.findById(id);
    }


    @PutMapping()
    public Burger update(@RequestBody Burger burger) {


        return burgerDao.update(burger);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        burgerDao.remove(id);
    }


    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable int price) {
        return burgerDao.findByPrice(price);
    }


    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        return burgerDao.findByBreadType(breadType);
    }


    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        return burgerDao.findByContent(content);
    }
}
