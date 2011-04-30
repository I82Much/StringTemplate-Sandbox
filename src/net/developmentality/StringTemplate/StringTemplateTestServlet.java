package net.developmentality.StringTemplate;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateErrorListener;
import org.json.JSONException;
import org.json.JSONObject;

import antlr.RecognitionException;
import antlr.TokenStreamRecognitionException;


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
		
		StringTemplate template = null;
		try {
			template = new StringTemplate(content);
		}
		catch (Exception e) {
			resp.setContentType("text/html");
			resp.getWriter().println("There's an error in your StringTemplate syntax.  Please see <a href=\"http://www.stringtemplate.org\">StringTemplate website</a> for more information");
			resp.getWriter().println("<pre>");
			e.printStackTrace(resp.getWriter());
			resp.getWriter().println("</pre>");
			return;
		}
		
		// The JSON provided by the user powers the 
		template.setArgumentContext(data);
		
		resp.getWriter().println(template.toString());
	}
	
	
	// Borrowed, modified from the STST project written by John Snyders
//	private class STErrorListener implements StringTemplateErrorListener
//    {
//        public void error(String msg, Throwable e)
//        {
//            if (e == null)
//            {
//                String format = resources.getString("Error");
//                logError(MessageFormat.format(format, msg));
//            }
//            else if (e instanceof TokenStreamRecognitionException)
//            {
//                TokenStreamRecognitionException tsr = (TokenStreamRecognitionException)e;
//                RecognitionException r = tsr.recog;
//                String format = resources.getString("RecognitionExceptionError");
//                logError(MessageFormat.format(format, msg, e.getLocalizedMessage(), 
//                        r.getLine(), r.getColumn()));
//            }
//            else if (e instanceof RecognitionException)
//            {
//                RecognitionException r = (RecognitionException)e;
//                String format = resources.getString("RecognitionExceptionError");
//                logError(MessageFormat.format(format, msg, e.getLocalizedMessage(), 
//                        r.getLine(), r.getColumn()));
//            }
//            else
//            {
//                String format = resources.getString("ExceptionError");
//                logError(MessageFormat.format(format, msg, e.getLocalizedMessage()));
//                
//            }
//        }
//
//        public void warning(String msg)
//        {
//            String format = resources.getString("Warning");
//            logError(MessageFormat.format(format, msg));
//        }
//    }
//
//    private void logError(String message)
//    {
//        System.err.println(message);
//    }
}
