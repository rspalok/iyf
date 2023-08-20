package net.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.user.service.UserDetailsServiceImpl;
 

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// add a reference to our user service
    //@Autowired
   // private UserService userService;
    
    @Bean
	public UserDetailsService userDetailsService() { 
		return new UserDetailsServiceImpl();
	}
	
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] resources = new String[]{
			"/assets/**","/image","/com/**","/",
			"/css/**","/icons/**","/images/**","/js/**","/iyf/**"
		};
		String[] SuperUser=new String[] {
			"/**"
		};
		String[] ADMIN=new String[] {
			"/course/**","/schedule/**","/home/**","/showNewForm/**","/page/**"
		};
		String[] REPORTOR=new String[] {
			"/report/**"
		};
		String[] EDITOR=new String[] {
				
		};
		String[] USER=new String[] {
				
		};
		String[] FOLLOWUP=new String[] {
			"/follow_up/**"
		};
        http.authorizeRequests(requests -> requests
                .antMatchers(resources).permitAll()
                .antMatchers(ADMIN).hasAnyAuthority("ADMIN")
                //.antMatchers("/**").hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
                .antMatchers(FOLLOWUP).hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN","CALLER")
                .antMatchers(REPORTOR).hasAnyAuthority("USER", "CREATOR", "EDITOR", "ADMIN")
                .antMatchers(SuperUser).hasRole("SUPERUSER"))
                .formLogin(login -> login
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(handling -> handling.accessDeniedPage("/access-denied")); 
		
	}

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    //authenticationProvider bean definition
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService()); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
		return auth;
	}
	  
}






