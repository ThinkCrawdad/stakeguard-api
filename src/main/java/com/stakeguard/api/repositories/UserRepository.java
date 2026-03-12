package com.stakeguard.api.repositories;

import com.stakeguard.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
