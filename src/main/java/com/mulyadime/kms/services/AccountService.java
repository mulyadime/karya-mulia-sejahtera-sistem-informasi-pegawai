/**
 * 
 */
package com.mulyadime.kms.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mulyadime.kms.entity.Account;
import com.mulyadime.kms.entity.Role;
import com.mulyadime.kms.repository.AccountRepository;

/**
 * @author Hamid Mulyadi
 *
 */
@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findOneByUsername(username);
		if (account == null)
			throw new UsernameNotFoundException("Nama pengguna tidak ditemukan!");
		
		return creatingAccount(account);
	}

	private UserDetails creatingAccount(Account account) {
		return new User(account.getUsername(), account.getPassword(), getAuthorities(account));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Account account) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : account.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return authorities;
	}

	public Account save(Account form) {
		form.setPassword(passwordEncoder.encode(form.getPassword()));
		accountRepository.save(form);
		
		return form;
	}

	public void autoSignIn(Account form) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(form));
	}

	private Authentication authenticate(Account form) {
		return new UsernamePasswordAuthenticationToken(creatingAccount(form), null, getAuthorities(form));
	}

}
