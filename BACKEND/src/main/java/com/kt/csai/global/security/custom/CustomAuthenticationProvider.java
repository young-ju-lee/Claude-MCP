package com.kt.csai.global.security.custom;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.kt.csai.domain.authentication.model.Role;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		Set<Role> roles = new HashSet<>();
//		roles.add(Role.ROLE_ADMIN);
//		roles.add(Role.ROLE_MANAGER);
		roles.add(Role.ROLE_USER);

		Collection<? extends GrantedAuthority> authorities = roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(username, null, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
