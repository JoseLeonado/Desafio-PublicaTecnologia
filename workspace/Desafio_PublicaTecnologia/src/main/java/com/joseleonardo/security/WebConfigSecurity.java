package com.joseleonardo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.joseleonardo.service.JogadoraService;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private JogadoraService jogadoraService;

	/* Configura as requisições de acesso por HTTP */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable()/* Desativa as configurações padrão de memória */
		.authorizeRequests() /* Restringir acessos  */
		.antMatchers(HttpMethod.GET, "/login").permitAll()/* Qualquer usário acessa o formulário de login */
		.and()
		.formLogin().permitAll()
        .loginPage("/login")/* Redireciona para a formulário de login com bootstrap */
        .defaultSuccessUrl("/menu")/* Após logar, redirecionar para a página de menu */
        .failureUrl("/login?error=true")/* Se o login estiver errado, redirecionar para o formulário de login */
        .and()
        .logout().logoutSuccessUrl("/login")/* Quando realizar o logout, redirecionar para o formulário de login */
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));/* Mapea a URL de logout e inválida o usuário autenticado */
	}

	/* Cria autenticação do usuário */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jogadoraService).passwordEncoder(new BCryptPasswordEncoder());
	}
	


}
