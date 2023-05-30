/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;
import java.sql.*;
import java.text.*;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


/**
 * @author Thomas
 */
public class courses {

    static Statement st;

    public courses() {


        try {
            String myDriver = "com.mysql.cj.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/plProject";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/plProject", "root", "plProject");


            if (conn != null) {
                System.out.println("succesfully connected");
            }
            st = conn.createStatement();


        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }


    public void updateParentCourse(String value, int id) {


        try {

            String query = String.format(" UPDATE parent_n_courses SET parent_course = '%s' WHERE parent_course_id= %d", value, id);
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }

    }

    public void deleteParentCourse(int parent_course_id) {

        try {

            String query = String.format(" delete from parent_n_courses WHERE parent_course_id= %d", parent_course_id);
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }

    public void addParentCourse(String name, int id, int course_id) {
        try {

            String query = String.format(" insert into parent_n_courses(parent_course,parent_course_id,course_id)" + "values('%s',%d,%d);", name, id, course_id);
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }

    public ResultSet getInstructorsNames() {
        try {


            String query = " SELECT inst_name,inst_id FROM courses";


            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset

//                String instrutcor = rs.getString("inst_name");
//                 String inst_id = rs.getString("inst_id");
            return rs;


            // print the results
//                  System.out.println(instrutcor);
//                  System.out.println(inst_id);


            //new connection();


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;


    }

    public ResultSet showAllStudents() {
        try {


            String query = " SELECT first_name,last_name,std_id FROM std_info";


            ResultSet rs = st.executeQuery(query);


//            while (rs.next())
//            {
//                String first_name = rs.getString("first_name");
//                 String last_name = rs.getString("last_name");
//              
//                  System.out.println(first_name+ " "+last_name );
//             
//
//            }
            return rs;

            //new connection();


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;


    }

    public String near_to_end() {
        try {
            String query = " SELECT course_name,end_date FROM courses";
            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {
//                  String current_time =getCurrentTimeStamp() ;
                String end_date = rs.getString("end_date");
                long diff = Math.abs(getDiff(end_date));
                String course_name = rs.getString("course_name");

                if (diff <= 15 && diff > 0) {


                    String s = String.format(" %s near to end in %d days ", course_name, Math.abs(getDiff(end_date)));

                    return s;
                }
            }


            //new connection();


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;


    }

    public String near_to_start() {
        try {
            String query = " SELECT course_name,start_date FROM courses";
            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {
//                  String current_time =getCurrentTimeStamp() ;
                String start_date = rs.getString("start_date");
                long diff = getDiff(start_date);
                String course_name = rs.getString("course_name");

                if (diff <= 15 && diff > 0) {
                    return String.format(" %s is near to start in %d days", course_name, getDiff(start_date));
                }


            }

            //new connection();


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;


    }

    public String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDateandTime = sdf.format(now);
        return currentDateandTime;
    }
//       public void CompareTwoDatesTest( ) throws ParseException {
//           
//         
//
////      SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
//       
//        projectpl.SimpleDateFormat sdformat = new projectpl.SimpleDateFormat("yyyy-MM-dd");
//       
//     Date current_date = sdformat.parse("2019-04-15");
//     Date start_date = sdformat.parse("2019-04-15");
//     
////         int x=current_date.compareTo(start_date) ;
////         return x;
//}

    public static long getDiff(String date2) {
        String[] arrOfStr = date2.split(" ", 2);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate d1 = LocalDate.parse(simpleDateFormat.format(date), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(arrOfStr[0], DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        return diffDays;

    }

    public ResultSet pageCourses() {
        try {

            String query = " SELECT inst_name,room,branch,price,parent_course,number_of_students,grades,start_date,days,end_date FROM courses";


            // create the java statement


            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
//            while (rs.next())
//            {
////                String inst_name = rs.getString("inst_name");
////                 String branch = rs.getString("branch");
////                 String price = rs.getString("price");
////                 String parent_course = rs.getString("parent_course");
////                 String start_date = rs.getString("start_date");
////                 String end_date = rs.getString("end_date");
////                 String days = rs.getString("days");
////                 String room = rs.getString("room");
////                 String course_name = rs.getString("course_name");
////                 String students = rs.getString("number_of_students");
//
//
//
//
//
//
//
//                // print the result
//                 System.out.println (" course_name     inst_name          room          branch          price    students         parent_course          price  start_date  end_date   days");
//                  System.out.println("  "+course_name +"          "+inst_name + "          "+room + "          "+branch+ "          "+price +"          "+ students + "          "+ "          "+ parent_course +"          " +price + "          "+start_date+ "          "+end_date+ "          "+days);
//
//
//
//            }
            return rs;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;


    }


}

       
    
        
   
    
    

