package com.want.demospringboot.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.want.demospringboot.utils.CommonConstant;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends BasicAuthenticationFilter {

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(CommonConstant.AUTH_HEADER);

		if (header != null && header.startsWith(CommonConstant.AUTH_TOKEN_PRE)) {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(CommonConstant.AUTH_HEADER);
//		System.out.println("token: " + token);
		if (token != null) {
			try {
				Claims claims = Jwts.parser().setSigningKey(CommonConstant.SECRET_KEY)
						.parseClaimsJws(token.replace(CommonConstant.AUTH_TOKEN_PRE, "")).getBody();
				String user = claims.getSubject();
				List<GrantedAuthority> auth = new ArrayList<>();
				return new UsernamePasswordAuthenticationToken(user, null, auth);
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

}
