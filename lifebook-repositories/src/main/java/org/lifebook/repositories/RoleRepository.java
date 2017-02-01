package org.lifebook.repositories;

import org.lifebook.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by monju on 01-Feb-17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
