// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;


// Extend HttpServlet class
public class LoginServlet extends HttpServlet {
 
private static final Logger loginServletLogger = Logger.getLogger("LoginServletLogger");;
private static FileHandler loginServletfileHandler;

static{
initializeLoggerParams();
}

private static void initializeLoggerParams(){

try{
 loginServletfileHandler = new FileHandler("./webapps/helloworld/Logs/LoginServlet.%u.%g.txt",1024 * 1024, 10, true);
 loginServletfileHandler.setFormatter(new LoggerFormatter());
 loginServletLogger.addHandler(loginServletfileHandler);
}catch(IOException ex){
	//throw new IOException("test");
}

 }
 
 
 
 
 
 
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
	  String title = "Hello User";
	  
	  MySQLAccess dao = new MySQLAccess();
	  String test = dao.SelectQuery("User_login","*","email='" + request.getParameter("first_name") + "' and password='" + request.getParameter("password")  + "'" );
	  //Logger myLogger = Logger.getLogger("myLogger");
	//FileHandler fileHandler = new FileHandler("myapp-log.%u.%g.txt",1024 * 1024, 10, true);
     //Handler consoleHandler = new ConsoleHandler();

     //myLogger.addHandler(fileHandler); 
	  //myLogger.info("Ah Yeah Baby - that's the end of System.out.println"); 
	  loginServletLogger.info("insiden login servlet");
	  out.println(test);
      /*String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
      out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>First Name</b>: "
                + request.getParameter("first_name") + "\n" +
                "  <li><b>Last Name</b>: "
                + request.getParameter("password") + "\n" +
                "</ul>\n" +
                "</body></html>");*/
				
				
  }
}