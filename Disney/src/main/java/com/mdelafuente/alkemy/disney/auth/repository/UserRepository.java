package com.mdelafuente.alkemy.disney.auth.repository;

import com.mdelafuente.alkemy.disney.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
