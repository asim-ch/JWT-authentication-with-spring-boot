package com.example.RestApi.Repositories;

import com.example.RestApi.Entities.RoleDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDefinitionJPDAO extends JpaRepository<RoleDefinition,Long> {

}
