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
import java.util.EmptyStackException;
import mvcdemo.model.Category;
import mvcdemo.model.Item;

/**
 *
 * @author Andrey
 */
public class ItemController {

    public ItemController() {

    }

    public ArrayList<Item> getItems(PreparedStatement pstmt, ResultSet rs) {
        ArrayList<Item> items = new ArrayList<Item>(); //список товаров
        try {
            rs = pstmt.executeQuery("select * from Item");
            while (rs.next()) {
                Item item = new Item(rs.getInt("item_id"), rs.getString("name"), rs.getString("image_name"), rs.getString("description"), rs.getInt("count"), rs.getInt("category_idfk"));
                items.add(item);
            }
        } catch (SQLException ex) {
            return null;
        }
        return items;
    }

    public ArrayList<Item> getItems(PreparedStatement pstmt, ResultSet rs, int category_id) {
        ArrayList<Item> items = new ArrayList<Item>(); //список товаров
        try {
            rs = pstmt.executeQuery("SELECT * FROM Item WHERE category_idfk='" + category_id + "'");
            while (rs.next()) {
                Item item = new Item(rs.getInt("item_id"), rs.getString("name"), rs.getString("image_name"), rs.getString("description"), rs.getInt("count"), rs.getInt("category_idfk"));
                items.add(item);
            }
        } catch (SQLException ex) {
            return null;
        }
        return items;
    }
    public ArrayList<Item> getItems(PreparedStatement pstmt, ResultSet rs, int category_id, int page, int pageSize) {
        ArrayList<Item> items = new ArrayList<Item>(); //список товаров
        try {
            rs = pstmt.executeQuery("SELECT * FROM Item WHERE category_idfk='" + category_id + "' LIMIT " + page*pageSize + ", " + pageSize + ";");
            while (rs.next()) {
                Item item = new Item(rs.getInt("item_id"), rs.getString("name"), rs.getString("image_name"), rs.getString("description"), rs.getInt("count"), rs.getInt("category_idfk"));
                items.add(item);
            }
        } catch (SQLException ex) {
            return null;
        }
        return items;
    }
    
    public Item getItem(PreparedStatement pstmt, ResultSet rs, int item_id) {
        Item item = new Item(); //список товаров
        try {
            rs = pstmt.executeQuery("SELECT * FROM Item WHERE item_id='" + item_id + "'");
            rs.next();
            item = new Item(rs.getInt("item_id"), rs.getString("name"), rs.getString("image_name"), rs.getString("description"), rs.getInt("count"), rs.getInt("category_idfk"));
        } catch (SQLException ex) {
            return null;
        }
        return item;
    }

    public void addItem(PreparedStatement pstmt, ResultSet rs, Item item) {
        try {
            rs = pstmt.executeQuery("INSERT INTO Item (category_idfk, image_name, description, name, count)"
                    + "       VALUES  	('" + item.getCategoryID() + "', '" + item.getImgName() + "', '" + item.getDescription() + "', '" + item.getName() + "','" + item.getCount() + "');");

        } catch (SQLException ex) {
            //error here
        }
    }

    public void updateItem(PreparedStatement pstmt, ResultSet rs, Item item) {
        try {
            pstmt.executeUpdate("UPDATE Item SET category_idfk = '" + item.getCategoryID() + "' "
                    + ", image_name = '" + item.getImgName() + "' "
                    + ", description = '" + item.getDescription() + "' "
                    + ", name = '" + item.getName() + "' "
                    + ", count = '" + item.getCount() + "' "
                    + "WHERE item_id = '" + item.getId() + "';");

        } catch (SQLException ex) {
            throw new EmptyStackException();
            
        }
    }

    public void deleteItem(PreparedStatement pstmt, ResultSet rs, int item_id){
        try {
            pstmt.executeUpdate("DELETE FROM Item WHERE item_id = '" + item_id + "';");
        } catch (SQLException ex) {
            //error here
        }
    }
}
