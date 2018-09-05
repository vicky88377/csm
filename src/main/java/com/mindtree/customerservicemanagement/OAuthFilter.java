package com.mindtree.customerservicemanagement;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Order(2)
public class OAuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Initiating WebFilter >> ");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpHeaders headers = new HttpHeaders();

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;

			String accessToken = httpRequest.getHeader("Authorization");
			if(accessToken != null)
				accessToken.replaceAll("Bearer ", "");

			headers.add(HttpHeaders.AUTHORIZATION, accessToken);

			if (StringUtils.pathEquals(httpRequest.getRequestURI(),"/customers")) {
				try {
					if (!FireBaseAuthHelper.isValidToken(accessToken)) {
						httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(),
								"Unauthorised User");
						return;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		System.out.println("Destroying WebFilter >> ");
	}

}
