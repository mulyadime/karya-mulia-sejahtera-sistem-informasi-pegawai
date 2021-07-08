/**
 * 
 */
package com.mulyadime.kms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mulyadime.kms.entity.Role;

/**
 * @author Hamid Mulyadi
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

	Role findByName(String name);

}
