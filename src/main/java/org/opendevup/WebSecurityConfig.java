package org.opendevup;

import org.opendevup.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void GlobalConfig(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ADMIN1").password("amri").roles(Role.ADMIN.getValue());
		auth.inMemoryAuthentication().withUser("MEDECIN1").password("amri").roles(Role.MEDECIN.getValue());
		auth.inMemoryAuthentication().withUser("USER1").password("amri").roles(Role.SIMPLE_USER.getValue());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().
//			antMatchers("/ressources/**", "/", "/etudiants/form").
//					permitAll().
//						anyRequest()
//							.permitAll().
//				and().
//					formLogin().
//						loginPage("/login").
//							permitAll().
//				and().
//					logout().
//					permitAll();
	http
		.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**")
					.permitAll()
						.anyRequest()
							.authenticated()
		.and()
			.formLogin()
				.loginPage("/login")
					.permitAll()
						.defaultSuccessUrl("/parabenes/form")
			.failureUrl("error.html");
	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//	}
}