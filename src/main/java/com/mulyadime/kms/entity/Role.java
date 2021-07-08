/**
 * 
 */
package com.mulyadime.kms.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Hamid Mulyadi
 *
 */
@Data
@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue
	@Column(name = "pk_role")
	private UUID id;
	
	@Column(length = 15)
	private String name;

}
