//package com.pengfyu.zuul.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.pengfyu.zuul.constant.AuthConstant;
//import com.pengfyu.zuul.constant.Platform;
//import com.pengfyu.zuul.redisson.RedissonUtils;
//import com.pengfyu.zuul.util.JwtUtil;
//import com.xiaoleilu.hutool.util.StrUtil;
//import org.apache.commons.lang.StringUtils;
//import org.redisson.api.RBucket;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * filter
// *
// * @author Li Shijie
// * @since 1.0.0
// */
//@Component
//public class AccessFilter extends ZuulFilter implements Filter {
//
//    public static final Logger log =  LoggerFactory.getLogger(AccessFilter.class);
//
//    private static final String BEARER_TOKEN_TYPE = "bearer ";
//
//    @Override
//    public boolean shouldFilter() {
//
//
//        boolean flag = true;
//        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
//        if (StrUtil.containsAnyIgnoreCase(request.getRequestURI(),
//                AuthConstant.GOODS_OPEN_API_A,
//                AuthConstant.AUTH_PATH,
//                AuthConstant.GOODS_OPEN_API_B,
//                AuthConstant.AGREEMENT_SUPPLIER_INFO,
//                AuthConstant.AGREEMENT_REGISTER_INFO,
//                AuthConstant.REGION_FINDSELECTSERVERRE,
//                AuthConstant.MESSAGE_MESSAGESERVER_QUERYTERMINAL,
//                AuthConstant.MESSAGE_MESSAGESERVER_QUERYTERMINALL,
//                AuthConstant.PAY_CALLBACK)) {
//            flag = false;
//           log.info("flag=" + false);
//        }
//        return flag;
//    }
//
//    @Override
//    public int filterOrder() {
//        return FilterConstants.SEND_ERROR_FILTER_ORDER;
//    }
//
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
//        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
//        // 从 http 请求头中取出 token
//        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        log.info("authHeader = " + authHeader);
//        if (StringUtils.isBlank(authHeader) || !authHeader.startsWith(BEARER_TOKEN_TYPE)) {
//            doSomeThing(ctx, AuthConstant.ILLEGAL);
//            return null;
//        }
//        String token = authHeader.substring(7);
//        String flag = JwtUtil.checkToken(token);
//        log.info("flag = " + flag);
//        if (flag.equals(AuthConstant.PASS) || flag.equals(AuthConstant.REFRESH_TOKEN)) {
//            String userIdFromToken = JwtUtil.getUserIdFromToken(token) + "";
//            log.info("userIdFromToken = " + userIdFromToken);
//            String platformFromToken = JwtUtil.getPlatformFromToken(token);
//            log.info("platformFromToken = " + platformFromToken);
//            if (!Platform.CONSUMER.getStatus().equals(platformFromToken)) {
//                //TODO 从redisson里面获取对于的token   在登录的时候肯定要设置进去
//                //bucket 的用法。set  先get出来，new 然后在setvalue 可以设置超时时间，和单位。获取直接跳过key获取到bucket，然后get()就好
//                RBucket<String> rBucket = RedissonUtils.getRBucket(userIdFromToken + platformFromToken + "token");
//                String redisToken = rBucket.get();
////                String redisToken = RedissonUtils.get(userIdFromToken + platformFromToken + "token") + "";
//                log.info("redisToken = " + redisToken);
//                if (redisToken == null || "".equals(redisToken) || !token.equals(redisToken)) {
//                    doSomeThing(ctx, AuthConstant.ALREADY_LOGIN);
//                    return null;
//                }
//            }
//            ctx.addZuulRequestHeader(AuthConstant.USER_ID, userIdFromToken);
//            ctx.addZuulRequestHeader(AuthConstant.USER_ROLES, userIdFromToken);
//            // 对该请求进行路由
//            ctx.setSendZuulResponse(true);
//            // 设值，让下一个Filter看到上一个Filter的状态
//            ctx.setResponseStatusCode(200);
//            ctx.set("isSuccess", true);
//            return null;
//        }
//        doSomeThing(ctx, flag);
//        return null;
//    }
//
//    private void doSomeThing(RequestContext ctx, String flag) {
//        // 过滤该请求，不对其进行路由
//        ctx.setSendZuulResponse(false);
//        // 返回错误码
//        ctx.setResponseStatusCode(200);
//        // 返回错误内容
//        ctx.setResponseBody(packWrongMessage(flag));
//        ctx.set("isSuccess", false);
//        HttpServletResponse response = ctx.getResponse();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        response.setLocale(new java.util.Locale("zh", "CN"));
//    }
//
//    private String packWrongMessage(String flag) {
//        switch (flag) {
//            case AuthConstant.EXPIRE_TOKEN:
//                return AuthConstant.ExpireToken;
//            case AuthConstant.REFRESH_TOKEN:
//                return AuthConstant.AskRefresh;
//            case AuthConstant.ILLEGAL:
//                return AuthConstant.AuthFilterError;
//            case AuthConstant.ALREADY_LOGIN:
//                return AuthConstant.alreadyLogin;
//            default:
//                return AuthConstant.ExpireToken;
//        }
//    }
//
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
//
