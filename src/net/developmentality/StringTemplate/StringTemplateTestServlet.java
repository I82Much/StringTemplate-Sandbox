package net.developmentality.StringTemplate;
import java.io.IOException;
import javax.servlet.http.*;

import org.antlr.stringtemplate.StringTemplate;

@SuppressWarnings("serial")
public class StringTemplateTestServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		StringTemplate template = new StringTemplate("Hello $name$");
		template.setAttribute("name", "Nick");
		
		resp.getWriter().println(template.toString());
		
	}
}
