package com.example.springtest.Database;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <Users, Integer>{

    //@Query("SELECT * FROM Users WHERE email=:email")
    //Optional<Users> getByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM Users", nativeQuery = true)
    List<Users> findAllUsers();

    @Query(value = "SELECT * FROM Users WHERE email=:email", nativeQuery = true)
    Optional<Users> findByEmail(@Param("email") String email);
}
