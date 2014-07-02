package mvcdemo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class User {

    private String username;
    private String password;

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id) {
       
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
