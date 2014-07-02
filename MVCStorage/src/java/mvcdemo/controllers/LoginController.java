package mvcdemo.controllers;
 
import java.io.IOException;

 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import mvcdemo.model.Authenticator;
import mvcdemo.model.User;
 

 
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public LoginController() {
        super();
    }
    

    
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        RequestDispatcher rd = null;
 
        Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(username, password);
        if (result.equals("success")) {
            rd = request.getRequestDispatcher("/success.jsp");
            
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        User user = new User(username, password);
            request.setAttribute("user", user);
       // rd.forward(request, response);
    }
     protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;
 
        Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(username, password);
        if (result.equals("success")) {
            rd = request.getRequestDispatcher("/success.jsp");
            
        } else {
            rd = request.getRequestDispatcher("/error.jsp");
        }
        User user = new User(username, password);
        request.setAttribute("user", user);
        rd.forward(request, response);
    }
 
}