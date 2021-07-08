/**
 * 
 */
package com.mulyadime.kms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mulyadime.kms.entity.Account;

/**
 * @author Hamid Mulyadi
 *
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
	
	public Account findOneByUsername(String username);

}
