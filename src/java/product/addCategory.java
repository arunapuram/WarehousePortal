/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import database.DB_Conn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Karthik Arunapuram
 */
public class addCategory extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addCategory</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addCategory at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String categoryName = request.getParameter("categoryName");
        PrintWriter out = response.getWriter();
        String message = addCategory(categoryName);
        out.print(message);
    }
    
    private  String addCategory (String categoryName){
        String message;
        
        if (categoryName.equals("")){
            //out.println("Please enter in All the Values");
            message ="Please enter in a Category Name";
        }
        else {
            try {
                //out.println("Done!!");
                Connection con;
                DB_Conn conn = new DB_Conn();
                con = conn.getConnection();
                String sqlInsertCompany ="INSERT INTO  `WarehousePortal`.`category` (`category_id` ,`category_name`)VALUES (NULL ,  '"+categoryName+"');"; 
               // "INSERT INTO  `WarehousePortal`.`product-company` (`company-id` ,`company-name`)VALUES (NULL ,  '"+companyName+"');";                
                //String sqlInsertProduct = "INSERT INTO  `WarehousePortal`.`category` (`category_id` ,`category_name`)VALUES (NULL ,  '"+companyName+"'); ";
                Statement st = con.createStatement();
                int rows = st.executeUpdate(sqlInsertCompany);
                
                if (rows != 1){
                   // out.println("Company not inserted");
                    message = "category insertion failed.";
                }else {
                    //out.println(companyName+" Company Inserted");
                    message = categoryName+" category inserted";
                }
                st.close();
                con.close();
            } 
            catch (SQLSyntaxErrorException ex){
                message = ("Please provide names without quotes");
            }
            catch (SQLIntegrityConstraintViolationException ex){
                //out.println("A comany name with the same name exists in your database... Try being specific.");
                message = "A category name with the same name exists in your database... Try being specific.";
            }
            catch (SQLException ex) {
                //out.println("There was a problem in Connectiong DB <br/> Exception has occoured "+ex);
                message = "There was a problem in Connectiong DB <br/> Exception has occoured "+ex;
           
            } catch (ClassNotFoundException ex) {
                //out.println("Application Cannot find the Class <br/> Exception has occoured "+ex);
                message = "Application Cannot find the Class <br/> Exception has occoured "+ex;
            }
        }
        return message;
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
