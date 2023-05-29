package com.ttttt.core.db.dao;


import com.ttttt.core.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByEmail(String email);

    User findByPhone(String phone);


}