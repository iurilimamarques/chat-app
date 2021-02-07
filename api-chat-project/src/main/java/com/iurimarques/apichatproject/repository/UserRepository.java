package com.iurimarques.apichatproject.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Query(value = "SELECT * " +
                   "FROM users c " +
                   "WHERE (c.email LIKE CONCAT('%', :keyWord, '%') OR c.name LIKE CONCAT('%', :keyWord, '%')) " +
                   "AND NOT EXISTS((SELECT * FROM messages m WHERE (m.user_destination = c.id OR m.from_user = c.id) AND (m.user_destination = :loggedUser OR m.from_user = :loggedUser))) " +
                   "AND c.id != :loggedUser", nativeQuery = true)
    List<User> findByEmailOrName(@Param("keyWord") String keyWord,
                                 @Param("loggedUser") Long loggedUser);

    @Query(value = "SELECT b.* " +
                    "FROM messages a " +
                    "JOIN users b ON a.user_destination = b.id OR a.from_user = b.id " + 
                    "WHERE (a.from_user = :loggedUser OR a.user_destination = :loggedUser) " +
                    "AND b.id != :loggedUser " +
                    "GROUP BY b.email", nativeQuery = true)
    List<User> selectActiveChats(@Param("loggedUser") Long loggedUser);

    
    @Query(value = "SELECT max(b.created_at) AS last_interaction " +
                   "FROM messages b " +
                   "WHERE (b.from_user = :id OR b.user_destination = :id) " +
                   "AND (b.user_destination = :loggedUser OR b.from_user = :loggedUser)", nativeQuery = true)
    LocalDateTime getLastInteractionWith(@Param("loggedUser") Long loggedUser, Long id);
}
