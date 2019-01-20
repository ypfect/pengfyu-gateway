//package com.pengfyu.zuul.security;
//
//import com.pengfyu.zuul.filter.PreAuthFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
//
//@EnableWebSecurity
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private CustomizeUserService customizeUserService;
//
//
//    /**
//     * 配置自定义过滤器拦截 --- 封装处理的类
//     * 定义token
//     * @return
//     */
//    @Bean
//    public AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> authenticationUserDetailsService() {
//        return new CustomizeUserService();
//    }
//
//
//    /**
//     * 配置过滤器 --- 过滤器拦截请求生成token --- 交给对应的provider处理（provider 实现的 authenticationManager）
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AbstractPreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter()
//            throws Exception {
//        PreAuthFilter filter = new PreAuthFilter();
//        //调用父类的方法设置
//        filter.setAuthenticationManager(authenticationManager());
//        return filter;
//    }
//
//    @Bean
//    public AuthenticationManager manager() throws Exception {
//       return authenticationManager();
//    }
//
//    /**
//     * 配置provider调用的userService
//     *         普通就是 userDetails
//     *         社交登录 socialUserDetails
//     * 其实是一一对应的关系。
//     */
//    @Bean
//    public PreAuthenticatedAuthenticationProvider configProvider(){
//        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
//        provider.setPreAuthenticatedUserDetailsService(customizeUserService);
//        //每个provider适配一个token类型
//        provider.supports(PreAuthenticatedAuthenticationToken.class);
//        //配置检查 是否过期 锁住..
//        provider.setUserDetailsChecker(new AccountStatusUserDetailsChecker());
//        return provider;
//    }
//
//
//    /**
//     * 配置自定义的预处理过滤器
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(configProvider());
//    }
//
//    /***设置不拦截规则*/
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/druid/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//             http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**/goods/sku/user/**",
//                        "/**/goods/GoodsClassificationFront/user/**",
//                        "/**/agreement/supplier/info","/**/agreement/register/info",
//                        "/**/region/findSelectServerRe/**",
//                        "/**/message/messageserver/queryTerminal/**",
//                        "/**/message/messageserver/queryTerminall/**",
//                        "/**/payBac/**","/**/auth/**")
//                .permitAll();
////                .anyRequest().authenticated() //任何请求,登录后可以访问
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .defaultSuccessUrl("/")
////                .failureUrl("/login?error")
////                .permitAll() //登录页面用户任意访问
////                .and()
////                .logout().permitAll(); //注销行为任意访问
//        http.addFilterBefore(preAuthenticatedProcessingFilter(), PreAuthFilter.class);
////                .addFilterAfter(myFilterSecurityInterceptor,
////                        FilterSecurityInterceptor
////                                .class)
////        ;
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//}
