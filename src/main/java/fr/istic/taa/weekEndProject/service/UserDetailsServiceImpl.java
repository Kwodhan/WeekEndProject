package fr.istic.taa.weekEndProject.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.istic.taa.weekEndProject.model.User;
import fr.istic.taa.weekEndProject.model.Role;
import fr.istic.taa.weekEndProject.repository.UserRepository;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
		System.out.println(pseudo);
		List<User> users = userRepository.findByPseudo(pseudo);
		

		if (users.size() == 0) {
			throw new UsernameNotFoundException(pseudo);
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (Role role : users.get(0).getRoles()) {

			grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
		}

		return new org.springframework.security.core.userdetails.User(users.get(0).getPseudo(), users.get(0).getPassword(),
				grantedAuthorities);
	}
}
