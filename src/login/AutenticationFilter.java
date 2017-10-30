package login;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.Priorities;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AutenticationFilter implements ContainerRequestFilter {

	public void filter(ContainerRequestContext ctx) throws IOException {
		String authHeader = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);
		if (authHeader == null) throw new NotAuthorizedException("Bearer");

		String token = parseToken(authHeader);
		if (verifyToken(token) == false) {
			throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
		}

	}

	private String parseToken(String header) {
		String token = header.substring("Bearer-".length()).trim();
		return token;
	}

	private boolean verifyToken(String token) {
		return TokenHelper.tokenValido(token);
	}

}
