package com.ims.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ims.model.DAOServiceImpl;


@WebServlet("/verifylogin")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public loginController() {
        super();
    }

	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	String email = request.getParameter("email");
            String password = request.getParameter("password");
            DAOServiceImpl service = new DAOServiceImpl();
            service.connectDb();
            boolean status = service.verifyLogin(email, password);
            if(status) {
            	 HttpSession session=request.getSession(true);
            	 session.setAttribute("email", email);
            	
            	 session.setMaxInactiveInterval(10);
            	 
            	 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/add_inquiry.jsp");
                 rd.forward(request, response);
            }else {
               request.setAttribute("error", "Invalid username/password");
               RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
               rd.forward(request, response);
            }
		} catch (Exception e) {
			request.setAttribute("error", "Session TimedOut !!!");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
		}
	}

}
