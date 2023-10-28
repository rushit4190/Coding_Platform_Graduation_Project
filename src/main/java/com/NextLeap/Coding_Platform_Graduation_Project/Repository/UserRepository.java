package com.NextLeap.Coding_Platform_Graduation_Project.Repository;

import com.NextLeap.Coding_Platform_Graduation_Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findBySessionToken(String sessionToken);

}
