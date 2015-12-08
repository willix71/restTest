package w.test;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class StartServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server(9999);
		LoginService loginService = new HashLoginService("hasCode.com Secured REST Service", "src/test/resources/realm.properties");
		server.addBean(loginService);

		WebAppContext webapp = new WebAppContext("src/test/resources/rest-assured-example.war", "/");
		server.setHandler(webapp);

		server.start();
		server.join();
	}
}
