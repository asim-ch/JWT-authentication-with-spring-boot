package com.example.RestApi.Repositories;

import com.example.RestApi.Entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoginJPDAO extends JpaRepository<Login,Long> {


    Login findByUserName(String userName);
    boolean existsByUserName(String userName);
}
