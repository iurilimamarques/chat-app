package com.iurimarques.apichatproject.repository;

import com.iurimarques.apichatproject.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    @Query(value = "SELECT * FROM users c WHERE c.email = :email AND c.password = :password", nativeQuery = true)
    User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Query(value = "SELECT * FROM users c WHERE c.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
}
