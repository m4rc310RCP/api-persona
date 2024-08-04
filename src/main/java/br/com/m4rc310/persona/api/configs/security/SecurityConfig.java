package br.com.m4rc310.persona.api.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.m4rc310.gql.MUserProvider;
import br.com.m4rc310.gql.dto.MUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableCaching
@EnableWebMvc
public class SecurityConfig {
	
	@Autowired
	@Lazy
	private PasswordEncoder pe;
	
	@Bean
	@Lazy
	MUserProvider loadUserProvider() {
		return new MUserProvider() {

			@Override
			public MUser authUser(String username, Object password) throws Exception {
				MUser user = getUserFromUsername(username);

				if (encoder.matches(String.valueOf(password), user.getPassword())) {
					return user;
				}

				throw new Exception("User error!");
			}

			@Override
			@Cacheable("username")
			public MUser getUserFromUsername(String username) {
				//User u = cachedUserService.getUserFromUsername(username);
				MUser u = new MUser();
				u.setUsername(username);
				u.setPassword(pe.encode("test"));
				u.setRoles("admin".split(";"));
				
				return u;
			}

			@Override
			public boolean isValidUser(MUser user) {
				log.info("isValidUser {}", user);
				return true;
			}
		};
	}

	
}
