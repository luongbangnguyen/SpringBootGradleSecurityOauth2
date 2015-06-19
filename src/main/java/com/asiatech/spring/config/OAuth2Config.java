package com.asiatech.spring.config;

import com.asiatech.spring.service.impl.CustomerServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
public class OAuth2Config {

    private static final String RESOURCE_ID = "restservice";

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTokenStore getJdbcTokenStore(){
        return new JdbcTokenStore(dataSource);
    }


    @Configuration
    @EnableResourceServer
    public static   class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {



        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            // @formatter:off
            resources
                    .resourceId(RESOURCE_ID);
            // @formatter:on
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests()
                    .antMatchers("/users").hasRole("ADMIN")
                    .antMatchers("/greeting").authenticated();
            // @formatter:on
        }

    }

    @Configuration
    @EnableAuthorizationServer
    public static  class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        @Autowired
        private JdbcTokenStore jdbcTokenStore;

        @Autowired
        private DataSource dataSource;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private CustomerServiceDetail customerServiceDetail;


        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            /**
             * here you can create a table user you want for authentication Oauth2
             */
            // @formatter:off
            endpoints
                    .tokenStore(jdbcTokenStore)
                    .authenticationManager(this.authenticationManager)
                    .userDetailsService(customerServiceDetail);
            // @formatter:on
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            // @formatter:off
            clients.jdbc(dataSource)
                    .passwordEncoder(bCryptPasswordEncoder);
            /**
             * flow code run fist when app build so you can add values to table oauth_client_details by jpa
             * to add user Oauth2 security
             */

                    /*.withClient("clientapp")
				    .authorizedGrantTypes("password", "refresh_token")
				    .authorities("USER")
				    .scopes("read", "write")
				    .resourceIds(RESOURCE_ID)
				    .secret("123456");*/
            // @formatter:on
        }

        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setSupportRefreshToken(true);
            tokenServices.setTokenStore(jdbcTokenStore);
            return tokenServices;
        }
    }

}
