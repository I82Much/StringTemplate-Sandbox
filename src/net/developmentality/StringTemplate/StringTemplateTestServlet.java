package net.developmentality.StringTemplate;
import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;
import org.json.JSONException;
import org.json.JSONObject;


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

		String content = req.getParameter("string_template_content");
		String json = req.getParameter("json_content");
		
		JSONObject data = null;
		try {
			data = new JSONObject(json);
	    }
	    
		catch (JSONException je)
	    {
			resp.setContentType("text/html");
			resp.getWriter().println("There was an error in your JSON.  Please use <a href=\"http://www.jsonlint.com/\">JSONLint</a> to validate");
			resp.getWriter().println("<pre>");
			je.printStackTrace(resp.getWriter());
			resp.getWriter().println("</pre>");
			return;
	    }
		
		StringTemplate template = new StringTemplate(content);
		template.setArgumentContext(data);
//		template.setAttribute("name", "Frank");
		
		resp.getWriter().println(template.toString());
		
		resp.getWriter().println(data);
	}
}
