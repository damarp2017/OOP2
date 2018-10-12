// aaaaaaaaa
// bbbbbbbbb
package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Feedback;
/**
 *
 * @author thancook
 */
public class FeedbackController extends HttpServlet{
//    private final static String add_action = "edit";
//    private final static String delete_action = "delete";
//    private final static String edit_action = "edit";
//    private final static String list_action = "list";
//    private String message= "";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            
            System.out.println(action);
            try {
                switch(action){
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "list":
                        listFeedback(request, response);
                        break;
                    default:
                        listFeedback(request, response);
                        break;
                }
            } catch (SQLException e) {
                throw  new ServletException(e);
            }
        }
    }
    
    private void listFeedback(HttpServletRequest request, HttpServletResponse respons)
        throws SQLException, IOException, ServletException {
        Feedback f = new Feedback();
        List<Feedback> feedback = f.all();
        request.setAttribute("feedback", feedback);
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback/lists.jsp");
        dispatcher.forward(request, respons);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse respons)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedback/new.jsp");
        dispatcher.forward(request, respons);
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
        processRequest(request, response);
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
