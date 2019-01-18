package com.pengfyu.zuul.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengfyu.zuul.dto.SysRole;
import com.pengfyu.zuul.dto.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/***
 * 扩展用户
 * 实现security接口 兼容tk自动生成的user不做改变
 */
public class AuthenUser extends User implements UserDetails {

    // ~ 用户登录成功之后保存到session中，默认是username作为key
    // ~ 扩展 之后是以用户作为key 。session最大控制的时候会取到key作比较，但是对象作为key，导致虽然是同一个对象但是判断是不同的。
    // ~ 覆写hashcode
    // ~ sessionInformation 结构  SessionInformation(Object principal, String sessionId, Date lastRequest) {}
    // =================================================================================
    private List<SysRole> roles;

    Collection<? extends GrantedAuthority> authorities;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenUser that = (AuthenUser) o;
        return Objects.equals(super.getUserName(), that.getUsername()) &&
                Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUsername());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /***
     * 是否禁用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return super.getStatus() == 0? false:true;
    }


}
