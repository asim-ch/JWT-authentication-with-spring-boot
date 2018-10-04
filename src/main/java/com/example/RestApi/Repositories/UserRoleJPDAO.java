package com.example.RestApi.Repositories;


import com.example.RestApi.Entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleJPDAO extends JpaRepository<UserRole,Long> {

}
