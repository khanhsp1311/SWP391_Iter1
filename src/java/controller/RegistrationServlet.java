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
@WebServlet(name="RegistrationServlet", urlPatterns={"/Registration"})
public class RegistrationServlet extends HttpServlet {
   
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
            out.println("<title>Servlet RegistrationServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationServlet at " + request.getContextPath () + "</h1>");
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("pass");
        String Reupwd = request.getParameter("re_pass");
        String umobile = request.getParameter("contact");

        RequestDispatcher dispatcher = null;
        
        if (uname == null || uname.equals(" ")) {
            request.setAttribute("status", "invalidName");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if (uemail == null || uemail.equals("")) {
            request.setAttribute("status", "invalidEmail");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if (upwd == null || upwd.equals("")) {
            request.setAttribute("status", "invalidPassword");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if(!upwd.equals(Reupwd)){
             request.setAttribute("status", "invalidConfirmPassword");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if (umobile == null || umobile.equals("")) {
            request.setAttribute("status", "invalidMobile");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if (umobile.length() > 10) {
            request.setAttribute("status", "invalidMobileLength");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
       
        try {
            UserDAO ud = new UserDAO();
            
           User u = new User(uname, upwd, uemail, umobile, 1);
//           ud.insert(u);
//            dispatcher = request.getRequestDispatcher("registration.jsp");
//            HttpSession ses =  request.getSession();
//            ses.setAttribute("mes", con);


if (!upwd.equals(Reupwd)) {
//            String err = "Password is not the same repassword";
           request.setAttribute("status", "failed");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        } else {
            try {
                // sau khi parse int id ta sẽ kiểm tra thông tin trong bảng của database tên là categoryDao
                User u2 = ud.login2(uemail,umobile);
                if (u2 == null) { // không tìm thấy tức muốn cho cái này gia nhập
                    User cNew = new User(uname, upwd, uemail, umobile, 1);
                    ud.insert(cNew);
                    // khi thêm thành công nó sẽ quay về cái danh sách
                    // tao cookie
                    HttpSession session = request.getSession(); // tạo session
                    session.setAttribute("DataEmail", uemail);
                    Cookie cu = new Cookie("EmailUser", uemail);
                    cu.setMaxAge(60 * 60 * 24);
                    response.addCookie(cu);
                    request.setAttribute("status", "success");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    // tài khoản đấy đã tồn tại rồi
                    request.setAttribute("status", "failed");
//                    request.setAttribute("error", "Phone Number: " + umobile+ " or Email: " + uemail + " is existed");
                    // trả về trang mà người dùng đăng nhập
                    request.getRequestDispatcher("registration.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
