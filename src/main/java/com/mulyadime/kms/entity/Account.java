/**
 * 
 */
package com.mulyadime.kms.entity;

import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Hamid Mulyadi
 *
 */
@Data
@Entity
@Table(name = "account_user")
public class Account {

	@Id
	@GeneratedValue
	@Column(name = "pk_account_user")
	private UUID id;
	
	@Column(length = 15)
	private String username;
	
	@Column(length = 45, name = "fullname")
	private String fullname;
	
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "account_roles",
			joinColumns = @JoinColumn(name = "fk_account_user", referencedColumnName = "pk_account_user"),
			inverseJoinColumns = @JoinColumn(name = "fk_role", referencedColumnName = "pk_role")
			)
	private Collection<Role> roles;

}
