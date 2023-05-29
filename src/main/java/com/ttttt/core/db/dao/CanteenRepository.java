package com.ttttt.core.db.dao;


import com.ttttt.core.db.entity.Canteen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CanteenRepository extends JpaRepository<Canteen,Integer> {

    Canteen findByPhone(String phone);

}