package org.lifebook.repositories;

import org.lifebook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String pUsername);

    User findByEmail(String pEmail);
}
