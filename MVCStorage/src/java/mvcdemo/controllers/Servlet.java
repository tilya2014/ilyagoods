/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcdemo.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvcdemo.controllers.CategoryController;
import mvcdemo.controllers.ItemController;
import mvcdemo.model.Category;
import mvcdemo.model.Item;

/**
 *
 * @author Andrey
 */
public class Servlet extends HttpServlet {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ItemController itemCtrl = null;
    CategoryController categoryCtrl = null;

    @Override
    public void init() throws ServletException {
        itemCtrl = new ItemController();
        categoryCtrl = new CategoryController();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        try {
            Properties connInfo = new Properties();
            connInfo.put("characterEncoding", "UTF8");
            connInfo.put("user", "root");
            connInfo.put("password", "nbuser");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mynewdatabase?zeroDateTimeBehavior=convertToNull", connInfo);
            pstmt = conn.prepareStatement("show databases;");

        } catch (SQLException ex) {

        }
    }

    @Override
    public void destroy() {
        try {

            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {

        }
    }

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
        //String username = request.getParameter("username");
        String action = "";
        String item_name = "";
        String item_description = "";
        String item_image = "";
        int category_id = -1;
        int item_id = 0;      
        int count = 0;

        Enumeration<String> params = request.getParameterNames();
        List<String> paramsList = Collections.list(params);

        for (Iterator<String> i = paramsList.iterator(); i.hasNext();) {
            String item = i.next();
            if (item.equals("category_id")) {
                category_id = Integer.parseInt(request.getParameter("category_id").replaceAll("\\D+", ""));
            }
            if (item.equals("item_id")) {
                item_id = Integer.parseInt(request.getParameter("item_id"));
            }
            if (item.equals("count")) {
                count = Integer.parseInt(request.getParameter("count"));
            }
            if (item.equals("action")) {
                action = request.getParameter("action");
            }
             if (item.equals("item_name")) {
                item_name = request.getParameter("item_name");
            }
              if (item.equals("item_description")) {
                item_description = request.getParameter("item_description");
            }
               if (item.equals("item_image")) {
                item_image = request.getParameter("item_image");
            }

        }

        ArrayList<Category> categories = categoryCtrl.getCategories(pstmt, rs);
        ArrayList<Item> items = null;
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher rd = null;

        if (action.equals("auth")) {
            
            //авторизация будет здесь
            rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
        if (action.equals("showItems")) {
            if (category_id == -1) {
                items = itemCtrl.getItems(pstmt, rs);
            } else {
                items = itemCtrl.getItems(pstmt, rs, category_id);
            }
            request.setAttribute("items", items);
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request, response);
        }
        if (action.equals("showCategories")) {
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher("/categories.jsp");
            rd.forward(request, response);
        }
        if (action.equals("showItem")) {
            Item item = itemCtrl.getItem(pstmt, rs, item_id);
            //item = new Item(1, "name", "milk.jpg", "description", item_id,1);
            request.setAttribute("item", item);
            rd = request.getRequestDispatcher("/description.jsp");
            rd.forward(request, response);
        }
        if (action.equals("deleteItem")) {
            Item item = itemCtrl.getItem(pstmt, rs, item_id);
            itemCtrl.deleteItem(pstmt, rs, item_id);

            items = itemCtrl.getItems(pstmt, rs, item.getCategoryID());

            request.setAttribute("items", items);
            request.setAttribute("item_id", item_id);
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request, response);
        }
        if (action.equals("showCategories")) {
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher("/categories.jsp");
            rd.forward(request, response);
        }

        if (action.equals("setCount")) {
            Item item = itemCtrl.getItem(pstmt, rs, item_id);
            item.setCount(count);
            itemCtrl.updateItem(pstmt, rs, item);

            if (category_id == -1) {
                items = itemCtrl.getItems(pstmt, rs);
            } else {
                items = itemCtrl.getItems(pstmt, rs, category_id);
            }
            request.setAttribute("items", items);
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request, response);
        }
        if (action.equals("updateItem")) {
            Item item = new Item(item_id, item_name, item_image, item_description, count, category_id);
            item.setCount(count);
            itemCtrl.updateItem(pstmt, rs, item);

            if (category_id == -1) {
                items = itemCtrl.getItems(pstmt, rs);
            } else {
                items = itemCtrl.getItems(pstmt, rs, category_id);
            }
            request.setAttribute("items", items);
            request.setAttribute("categories", categories);
            rd = request.getRequestDispatcher("/items.jsp");
            rd.forward(request, response);
        }
        //response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("items", items);
        request.setAttribute("categories", categories);
        rd = request.getRequestDispatcher("/success.jsp");
        // rd.forward(request, response);
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
