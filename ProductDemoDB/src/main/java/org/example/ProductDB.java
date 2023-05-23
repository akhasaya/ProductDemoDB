package org.example;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ProductDB {
    Connection conn = null;

    public ProductDB() {
        try{
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/telusko","postgres","0000");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    public void save(Product p) {
        String query = "insert into product (name, type, place, warranty) values (?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, p.getName());
            st.setString(2, p.getType());
            st.setString(3, p.getPlace());
            st.setInt(4, p.getWarranty());
            st.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Product> getAll() {
        List<Product> prods = new ArrayList<>();
        String query = "select name,type,place,warranty from product";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product();
                p.setName(rs.getString(1));
                p.setType(rs.getString(2));
                p.setPlace(rs.getString(3));
                p.setWarranty(rs.getInt(4));
                prods.add(p);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prods;
    }

    public Product getAProduct(String name) {
        String query = "select name,type,place,warranty from product where name = ?";
        Product p = new Product();
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                p.setName(rs.getString(1));
                p.setType(rs.getString(2));
                p.setPlace(rs.getString(3));
                p.setWarranty(rs.getInt(4));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public List<Product> getProductWithText(String text) {
        List<Product> prods = new ArrayList<>();
        String query = "select name,type,place,warranty from product where name ilike ? or type ilike ? or place ilike ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, "%" + text + "%");
            st.setString(2, "%" + text + "%");
            st.setString(3, "%" + text + "%");
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product();
                p.setName(rs.getString(1));
                p.setType(rs.getString(2));
                p.setPlace(rs.getString(3));
                p.setWarranty(rs.getInt(4));
                prods.add(p);
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prods;
    }

    public List<Product> getProductInPlace(String text) {
        List<Product> prods = new ArrayList<>();
        String query = "select name,type,place,warranty from product where place = ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, text);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product();
                p.setName(rs.getString(1));
                p.setType(rs.getString(2));
                p.setPlace(rs.getString(3));
                p.setWarranty(rs.getInt(4));
                prods.add(p);
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prods;
    }

    public List<Product> getProductsWithoutWarrenty(int year) {
        List<Product> prods = new ArrayList<>();
        String query = "select name,type,place,warranty from product where warranty < ?";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product p = new Product();
                p.setName(rs.getString(1));
                p.setType(rs.getString(2));
                p.setPlace(rs.getString(3));
                p.setWarranty(rs.getInt(4));
                prods.add(p);
            }
            rs.close();
            st.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prods;
    }
}
