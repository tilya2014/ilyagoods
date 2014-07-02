/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcdemo.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mvcdemo.model.Category;

/**
 *
 * @author Andrey
 */
public class CategoryController {

    public CategoryController() {

    }

    public ArrayList<Category> getCategories(PreparedStatement pstmt, ResultSet rs) {
        ArrayList<Category> categories = new ArrayList<Category>();
        try {
            rs = pstmt.executeQuery("select * from Category");
            while (rs.next()) {
                Category category = new Category(rs.getString("name"), rs.getString("description"), rs.getInt("category_id"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            return null;
        }
        return categories;
    }

    public void addCategory(PreparedStatement pstmt, ResultSet rs, Category category) {
        try {
            rs = pstmt.executeQuery("INSERT INTO Item (description, name) VALUES('"
                    + category.getDescription() + "', '" + category.getName() + "');");

        } catch (SQLException ex) {
            //error here
        }
    }

    public void updateCategory(Connection conn, PreparedStatement pstmt, ResultSet rs, Category category) {
        try {
            rs = pstmt.executeQuery("UPDATE Item SET description = '" + category.getDescription() + "' "
                    + "SET name = '" + category.getName() + "';");

        } catch (SQLException ex) {
            //error here
        }
    }

    public void deleteCategory(Connection conn, PreparedStatement pstmt, ResultSet rs, Category category) {
        try {
            rs = pstmt.executeQuery("DELETE FROM Category WHERE item_id = '" + category.getId() + "';");
        } catch (SQLException ex) {
            //error here
        }
    }
}
