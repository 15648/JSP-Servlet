package com.perscholas.servletjsp_sba;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PoemDAO pdao;
	  
	public void init() {
		String url = getServletContext().getInitParameter("url");
	    String user = getServletContext().getInitParameter("user");
	    String password = getServletContext().getInitParameter("password");
	    pdao = new PoemDAO(url, user, password);
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println("req.getServletPath()  :  " + action);
		try {
            switch (action) {
            case "/Welcome":
                Welcome(request, response, "views/Welcome.jsp");
                break;
            case "/ShowCreatePoem":
            	showCreatePoem(request, response);
                break;
            case "/CreatePoem":
            	CreatePoem(request, response);
                break;
            case "/PoemByTitle":
                PoemByTitle(request, response);
                break;
            case "/ShowUpdatePoem":
                showUpdatePoem(request, response);
                break;
            case "/UpdatePoem":
                UpdatePoem(request, response);
                break;
            default:
            	Home(request, response);
                break;
            }
        } 
		catch (Exception e) {
            throw new ServletException(e);
        }	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static void Welcome(HttpServletRequest request, HttpServletResponse response, String string) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("views/Welcome.jsp");
		rd.forward(request, response);
	}
		
	public static void showCreatePoem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("views/CreatePoem.jsp");
		rd.forward(request, response);		
	}
	
	public static void CreatePoem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String content = request.getParameter("content");
		System.out.printf("%s, %s, %s\n", title, author, content);
		Poem poem = new Poem(title, author, content);
		PoemDAO pdao = new PoemDAO();
		pdao.insertPoem(poem);
		System.out.println(poem);
		showCreatePoem(request,response);		
	}
	
	public static void PoemByTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		System.out.println("Search Poem By Title  :  " + title);
		PoemDAO pdao = new PoemDAO();
		Poem poem = pdao.getPoemByTitle(title);
		showUpdatePoem(request,response);
	}
	
	public static void showUpdatePoem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("views/UpdatePoem.jsp");
		rd.forward(request, response);	
	}
	
	public static void UpdatePoem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String newContent = request.getParameter("newContent");
		System.out.println("New Content  :  " + newContent);
		System.out.printf("%s\n", newContent);
		Poem poem = new Poem(newContent);
		PoemDAO pdao = new PoemDAO();
		pdao.updatePoemText(poem);
		System.out.println(poem);
		request.setAttribute("updatePoemText", poem);
		showUpdatePoem(request, response);	
	}
	
	public static void Home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd = request.getRequestDispatcher("views/Welcome.jsp");
		rd.forward(request, response);	
	}
}
