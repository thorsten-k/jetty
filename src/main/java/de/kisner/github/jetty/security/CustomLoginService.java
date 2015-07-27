package de.kisner.github.jetty.security;

import java.security.Principal;

import javax.security.auth.Subject;

import org.eclipse.jetty.security.DefaultIdentityService;
import org.eclipse.jetty.security.IdentityService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.MappedLoginService.KnownUser;
import org.eclipse.jetty.server.UserIdentity;
import org.eclipse.jetty.util.security.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLoginService implements LoginService
{
	final static Logger logger = LoggerFactory.getLogger(CustomLoginService.class);
	
	private String username;
	private String password;
	
	public CustomLoginService()
	{
		username = "my";
		password = "me";
		identityService = new DefaultIdentityService();
	}
	
	@Override
	public String getName()
	{
		logger.info("getName");
		return null;
	}

	@Override
	public UserIdentity login(String username, Object credentials)
	{
		logger.info("login("+username+","+credentials+") "+credentials.getClass().getSimpleName());
		if(username.equals(this.username) && credentials.toString().equals(this.password))
		{
			Credential credential = (credentials instanceof Credential)?(Credential)credentials:Credential.getCredential(credentials.toString());
			
			Principal userPrincipal = new KnownUser(username,credential);
            Subject subject = new Subject();
            subject.getPrincipals().add(userPrincipal);
            subject.getPrivateCredentials().add(credential);
            subject.setReadOnly();
            UserIdentity identity = identityService.newUserIdentity(subject,userPrincipal,new String[]{"user"});
            return identity;
		}
		return null;
	}

	@Override
	public boolean validate(UserIdentity user)
	{
		logger.info("validate("+user.toString()+")");
		return false;
	}

	@Override
	public void logout(UserIdentity user)
	{
		logger.info("logout()");
	}
	
	//Identity Service
	private IdentityService identityService;
	@Override public IdentityService getIdentityService() {return identityService;}
	@Override public void setIdentityService(IdentityService service){this.identityService=service;}
}
