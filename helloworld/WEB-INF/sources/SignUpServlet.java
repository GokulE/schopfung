import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;

// Extend HttpServlet class
public class SignUpServlet extends HttpServlet {
 
private static final Logger signUpServletLogger = Logger.getLogger("SignUpServletLogger");;
private static FileHandler signUpServletfileHandler;
 
static{
initializeLoggerParams();
}

private static void initializeLoggerParams(){

try{
 signUpServletfileHandler = new FileHandler("./webapps/helloworld/Logs/SignupServlet.%u.%g.txt",1024 * 1024, 10, true);
 signUpServletfileHandler.setFormatter(new LoggerFormatter());
 signUpServletLogger.addHandler(signUpServletfileHandler);
}catch(IOException ex){
	//throw new IOException("test");
}

 }
 
 
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
  signUpServletLogger.info("inside signup servlet");
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
	  String title = "Using GET Method to Read Form Data";
      String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
	  String error = null;
	  String user_name1 = request.getParameter("sign_up_user_name");
				String user_name2 =request.getParameter("sign_up_password");
				String user_name3 = request.getParameter("sign_up_email");
				String user_name4 = request.getParameter("sign_up_Phone Number");
				String user_name5 = request.getParameter("sign_up_DOB");
				
				try{
				MySQLAccess obj = new MySQLAccess();
				
				 error = obj.InsertQuery(user_name1,user_name2,user_name3,user_name4,user_name5) + "inside signupserver";
				}catch(Exception e){
				error = e.toString() + "inside signupserver exception";
				}
      out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + request.getParameter("sign_up_user_name") + "\n" +
                "  <li><b>Last Name</b>: "
                + request.getParameter("sign_up_password") + "\n" +
                "</ul>\n error=" + error +
                "</body></html>");
				
				
				}
				
				
				
  }