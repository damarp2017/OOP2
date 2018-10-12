/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author thancook
 */
public class Feedback extends MyConnection{
    private final String tableName = "feedback";
    
    public Feedback(){
        super();
    }
    
    public String comment;
    public int rating;
    public int productId;
    public int orderId;
    public int userId;
    public String created_at;
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getComment(){
        return comment;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public int getRating(){
        return rating;
    }
    
    public void setRating(int rating){
        this.rating = rating;
    }
    
    public int getProductId(){
        return productId;
    }
    
    public void setProductId(int productId){
        this.productId = productId;
    }
    
    public int getOrderId(){
        return orderId;
    }
    
    public void setOrderId(int orderId){
        this.orderId = orderId;
    }
    
    public int getUserId(){
        return userId;
    }
    
    public void setUserId(int userId){
        this.userId = userId;
    }
    
    public String getCreatedAt(){
        return created_at;
    }
    
    public void setCreatedAt(String created_at){
        this.created_at = created_at;
    }
    
    public boolean create(){
        String query = "INSERT INTO "+ tableName 
                + "(product_id, user_id, order_id, rating, comment, date) values('" 
                + this.productId + "', '" 
                + this.userId + "', '" 
                + this.orderId + "', '" 
                + this.rating + "', '" 
                + this.comment + "', '" 
                + this.created_at + "')";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query)>0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
//    private boolean validate(){
//        return !"".equals(this.comment);
//    }
    
//    public boolean update(){
//        if(!validate()){
//            return false;
//        }
//        
//        String query = "UPDATE" + tableName + " SET product_id ='" + this.productId 
//                + "', user_id='" + this.userId 
//                + "', order_id='" + this.orderId 
//                + "', rating='" + this.rating 
//                + "', comment='" + this.comment 
//                + "' WHERE id= " + this.id + " ";
//        
//        try {
//            Statement stmt = this.conn().createStatement();
//            return stmt.executeUpdate(query) > 0;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }
    
    public boolean delete(){
        String query = "DELETE FROM " + tableName + " WHERE id = " + this.id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            return stmt.executeUpdate(query) > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Feedback find(int id){
        Feedback feedback = new Feedback();
        String query = "SELECT * FROM " + tableName + " WHERE id = " + id + " ";
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                feedback.setProductId(res.getInt("product_id"));
                feedback.setUserId(res.getInt("user_id"));
                feedback.setOrderId(res.getInt("order_id"));
                feedback.setRating(res.getInt("rating"));
                feedback.setComment(res.getString("comment"));
                feedback.setCreatedAt(res.getString("created_at"));
                feedback.setId(res.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return feedback;
    }
    
    public ArrayList<Feedback> all(){
        String query = "SELECT * FROM " + tableName;
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        try {
            Statement stmt = this.conn().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                Feedback feedback = new Feedback();
                feedback.setProductId(res.getInt("product_id"));
                feedback.setUserId(res.getInt("user_id"));
                feedback.setOrderId(res.getInt("order_id"));
                feedback.setRating(res.getInt("rating"));
                feedback.setComment(res.getString("comment"));
                feedback.setCreatedAt(res.getString("created_at"));
                feedback.setId(res.getInt("id"));
                feedbacks.add(feedback);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return feedbacks;
    }
}
