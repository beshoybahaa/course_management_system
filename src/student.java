/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.* ;
import java .sql.*;
import java.text.*;
/**
 *
 * @author DELL
 */
public class student {
    static Statement st ;
     
       
       
    
    public void con() {
    

       try{
             String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/plProject";
             Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plProject", "root", "3032003");
          /*Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/plProject", "root", "3032003") ;*/
              if(conn!=null){
               System.out.println("succesfully connected");
                 }
                    
                    
                      st=conn.createStatement();
              
       
        }
        catch(Exception e){
            
       System.out.println(e);
            System.out.println("not connected");
        }
       
}   
    public void Student1(){
        System.out.print("1)Update Your Information"
              + "\n 2)Get Grade Of Spacific Course "
              + "\n  3)Make Survey About Spacific Course "
               + "\n 4)See All Courses");
      Scanner scn =new Scanner (System.in);
      int e;
      e=scn.nextInt();
      switch(e){
          case 1:
              update();
              break;
          case 2:
              System.out.println("enter your id :");
              int std_id = scn.nextInt();
              System.out.println("enter course id :");
              int course_id=scn.nextInt();
              seeMyGrade( std_id, course_id);
              break;
          case 3:
             String survey =scn.next();
              String std_name =scn.next();
               String course_name =scn.next();
             makeSurvey(survey,std_name,course_name );
              break;
          case 4:
              System.out.println("enter your id :");
              int id=scn.nextInt();           
              getCourses(id);
              break;
      
      }
  }

    public void updateDataWithStdID( String table_name,String field , String value ,int id  ){

         

                   try{

                String query=   String.format(" UPDATE %s SET %s = '%s' WHERE std_id= %d", table_name,field,value,id);
                   st.executeUpdate(query);

              }
 
        catch(Exception e){

       System.out.println(e);
            System.out.println("not connected");
        }
    }
     public void update(){  
        
         Scanner scn =new Scanner (System.in);
         
          String table_name = "std_inf";
          String field = scn.next();
          String value= scn.next();
          int id = scn.nextInt();
          updateDataWithStdID(table_name,field ,value ,id );
            
     }
    
          
    
    public void seeMyGrade(int std_id,int course_id) {
        try
        {
           

                   
            String query=   String.format(" select grades,course_name from course_std  INNER JOIN courses ON course_std.std_id=courses.courses_id"+" WHERE std_id=%d  AND  course_id=%d ;  ",std_id,course_id);   
             
    
            
               ResultSet rs=st.executeQuery(query);
               
             System.out.println("course_name      grade ");

          
            while (rs.next())
            {
                String course_name = rs.getString("course_name");
                String grades = rs.getString("grades");
                
                 
               


                // print the results
                 System.out.println(course_name + "             "+grades);
             

            }
          
            //new connection();
      
           
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
       }
    
    
    
    
    
  public void makeSurvey(String survey,String std_name, String course_name){

                        try{

                String query= String.format(" insert into surveys(survey,std_name,course_name)"+ "values('%s','%s','%s');",survey,std_name,course_name);
                   st.executeUpdate(query);

              }
 
        catch(Exception e){

       System.out.println(e);
            System.out.println("not connected");
        }





        }

    
    
    
    
    
    public int getCourses (int id ){
    
           try
        {

           String query = String.format( " SELECT course_id ,course_name FROM course_std inner join courses on course_std.course_id = courses.courses_id  WHERE std_id=%d ",id);


            // create the java statement

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            System.out.println("course_name"+"    "+"course_id" );
            // iterate through the java resultset
            while (rs.next())
            {
                
                 String course_name= rs.getString("course_name");
                 String course_id= rs.getString("course_id");

                  System.out.println(course_name+"            "+course_id );

                // print the result
                


            }
        }
              catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
          
        }
           return 0;
    }
}

    
    
    

   
