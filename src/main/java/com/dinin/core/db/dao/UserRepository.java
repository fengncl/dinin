package com.dinin.core.db.dao;

import com.dinin.core.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

    User findByEmail(String email);

    User findByPhone(String phone);


}