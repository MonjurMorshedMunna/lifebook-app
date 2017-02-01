package org.lifebook.repositories;

import org.lifebook.models.User;
import org.lifebook.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by monju on 01-Feb-17.
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findByUser(User pUser);
}
