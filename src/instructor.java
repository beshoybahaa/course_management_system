/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java .sql.*;


/**
 *
 * @author Thomas
 */
public class instructor {
      static Statement st;
        public instructor() {
    

        try{
                  String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/plProject";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plProject", "root", "plProject");

            
              if(conn!=null){
               System.out.println("succesfully connected");
                 }
                      st = conn.createStatement();
              
       
        }
        catch(Exception e){
            
       System.out.println(e);
            System.out.println("not connected");
        }
}
    
    
    public void addGrades(int std_id, int course_id,int grades)   {
        try {

            String query = "insert into course_std values('" + std_id + "','" + course_id + "','" + grades + "');";
            st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }
       public void publishGrades() {
        try
        {
          

            String query =  " SELECT std_id,course_id,grades FROM course_std";

           
            ResultSet rs = st.executeQuery(query);
             System.out.println(" std_id    course_id    grades");

          
            while (rs.next())
            {
                String std_id = rs.getString("std_id");
                 String course_id = rs.getString("course_id");
                  String grades= rs.getString("grades");
               


              
               
                  System.out.println("  "+std_id+ "            "+ course_id+ "            "+grades);
             

            }
        }
              catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
          
            //new connection();
      
           
        }
    
    
    
    
}
