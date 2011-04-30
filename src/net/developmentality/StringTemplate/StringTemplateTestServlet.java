package net.developmentality.StringTemplate;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;

@SuppressWarnings("serial")
public class StringTemplateTestServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(StringTemplateTestServlet.class.getName());

	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		
		StringTemplate template = new StringTemplate("Hello $name$");
		template.setAttribute("name", "Nick");
		
		resp.getWriter().println(template.toString());
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String content = req.getParameter("content");
        log.info("Content: " + content);		
//        resp.sendRedirect("/stringtemplate.html");
        
        resp.getWriter().println(content);
	}
}
