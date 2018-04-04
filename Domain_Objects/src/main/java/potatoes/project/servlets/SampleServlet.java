/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package potatoes.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 
 * @references: https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequest.html
 * https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletResponse.html
 * https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServlet.html
 */
public class SampleServlet extends HttpServlet {
    
    @Override 
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException{
        PrintWriter writer = resp.getWriter();
        writer.println("response from servlet ");
    }
}
