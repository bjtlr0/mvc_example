package com.ksh.vo.work;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data // @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor 을 한번에 선언하는 어노테이션
public class MemberVO implements UserDetails {
	private long id;
	private String userId;
	private String userPwd;
	private Date createTime;
	private Date modifyTime;
	private List<MemberRoleVO> roles; 	// 이게 이런식으로 하는게 뭔 의미가 있을까?
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(int i=0; i< this.roles.size(); i++){
			String role = this.roles.get(i).getAuthority();
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	public void setAuthorities(List<MemberRoleVO> roles){
		this.roles = roles;
	}
	
	@Override
	public String getPassword() {
		return this.userPwd;
	}
	@Override
	public String getUsername() {
		return this.userId;
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
	@Override
	public boolean isEnabled() {
		return true;
	}
}