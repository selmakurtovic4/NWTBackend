package com.hoperise.staff.repositories;

import com.hoperise.staff.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
