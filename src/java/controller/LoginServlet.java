/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author DELL
 */
@WebServlet(name="LoginServlet", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
      String uemail = request.getParameter("username");
        String upwad = request.getParameter("password");
        String r = request.getParameter("remember-me");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (uemail == null || uemail.equals("")) {
            request.setAttribute("status", "invalidEmail");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        if (upwad == null || upwad.equals("")) {
            request.setAttribute("status", "invalidPassword");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
        
       
       
    try{
         UserDAO ud = new UserDAO();
         User a = ud.login(uemail, upwad);
       if(a == null){ // nếu chưa có
//           String err = "Password or Email is not correct";
//           request.setAttribute("err", err);

           request.setAttribute("status", "failed");
           request.getRequestDispatcher("login.jsp").forward(request, response);
       }else{ // khi có rồi tạo session
           User a1 = ud.getUserById(uemail); // a1 là thằng user khi lấy được email
          
           session.setAttribute("DataEmail", uemail);
           session.setAttribute("user", a1);
           session.setAttribute("roleName", a1.getRoleID().getName());
           // sau đó làm một cái tủ đựng đồ
           Cookie cu = new Cookie("email", uemail);
           Cookie cp = new Cookie("pass", upwad);
           Cookie cr = new Cookie("rem",r);
           // bước 2 tạo thời gian sống
           // khi người dùng ấn remember thì bắt đầu tạo cookie
           if(r == null){
               cu.setMaxAge(0);
               cp.setMaxAge(0);
               cr.setMaxAge(0);
           }else{
               cu.setMaxAge(24*24*60);
               cp.setMaxAge(24*24*60);
               cr.setMaxAge(24*24*60);
           }
           // bước 3: cho nào vào response để add vào brower
           response.addCookie(cu);
           response.addCookie(cp);
           response.addCookie(cr);
        
           
           request.getRequestDispatcher("index.jsp").forward(request, response);
       }
        } catch (Exception e) {
            e.printStackTrace();
        }
}
     

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
