import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class connection {
    static Statement st;

    connection() {
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

        //putData(email,password,st);

    
    }
    public int getDataForLogin(String email_login, String password_login, String table_name) {
        try {
            String query = "SELECT * FROM " + table_name;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                if (email.equals(email_login) && password.equals(password_login)) {
                    System.out.println("\n-----------------------------------------------------------------\nsucessfully login\n-----------------------------------------------------------------\n");
                    if (table_name == "admin_login")
                        return 1;
                    return getid(table_name, email_login);
                }

            }
            JOptionPane.showMessageDialog(null, "wrong email or password\ntry again");
            System.out.println("wrong email or password");
            //getData(email_login, password_login, table_name);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return 000;
    }

    /* get id of the login user if std or inst and if admin return 0*/
    private int getid(String table_name, String email) {
        //getData("courses","inst_id");
        String col;
        if (table_name == "inst_login") {
            //table_name = "courses";
            col = "inst_id";
        } else if (table_name == "std_login") {
            //table_name = "std_info";
            col = "std_id";
        } else {
            return 0;
        }
        try {

            String query = "SELECT " + col + " FROM " + table_name + " where email =  '"+email+"' " ;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                return Integer.parseInt(rs.getString(col));
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            return 0;
        }
        return 0;
    }

    /* get any data from database */
    public boolean getData(String table_name, String... values) {
        try {
            String col = convertStringArrayToString(values);
            String query = "SELECT " + col + " FROM " + table_name;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                for (String s : values) {
                    String result = rs.getString(s);
                    System.out.println(s + " : " + result);
                }
                System.out.println("-----------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return true;
    }

    public ResultSet getDataAllResult(String table_name, String... values) {
        try {
            String col = convertStringArrayToString(values);
            String query = "SELECT " + col + " FROM " + table_name;
            ResultSet rs = st.executeQuery(query);
                return rs;

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean getDataWhere(String table_name, String condition, String... values) {
        try {
            String col = convertStringArrayToString(values);
            String query = "SELECT " + col + " FROM " + table_name + " where " + condition;
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                for (String s : values) {
                    String result = rs.getString(s);
                    System.out.println(s + " : " + result);
                }
                System.out.println("-----------------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        return true;
    }

    private static String convertStringArrayToString(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr)
            sb.append(str).append(",");
        return sb.substring(0, sb.length() - 1);
    }

    /* put data into std and inst login table*/
    public void putData(String tableName, String email, String password, String id) {
        try {

            String query = "insert into " + tableName + " values('" + email + "','" + password + "','" + id + "');";
            st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    /* put data into admin login table*/
    public void putData(String email, String password) {
        try {

            String query = "insert into admin_login values('" + email + "','" + password + "');";
            st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    /* put data into create std_info table*/
    public void putData(String std_id, String fn, String ln, String adress, int phone, String gender, String birthDate) {
        try {

            String query = "insert into std_info values('" + std_id + "','" + fn + "','" + ln + "','" + adress + "','" + phone + "','" + gender + "','" + birthDate + "');";
            st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    /* put data into create course table*/
    public void putData(String course_name, String courses_id, String start_date,
                        String end_date, int days, String room, int price,
                        String parent_course, String branch, String inst_name, int inst_id,
                        int number_of_students, int grades_of_course) {
        try {

            String query = "insert into courses values('" + course_name + "','" + courses_id + "','"
                    + start_date + "','" + end_date + "','" + days + "','" + room + "','"
                    + price + "','" + parent_course + "','" + branch + "','" + inst_name + "','"
                    + inst_id + "','" + number_of_students + "','" + grades_of_course + "');";
            st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    /* put data into create course_std table*/
    public void putData(int std_id, int course_id, int grades) {
        try {

            String query = "insert into course_std values('" + std_id + "','" + course_id + "','" + grades + "');";
            st.executeUpdate(query);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    /* update data in database with stdID*/
    public void updateDataWithStdID(String table_name, String field, String value, int id) {


        try {

            String query = String.format(" UPDATE %s SET %s = '%s' WHERE std_id= %d", table_name, field, value, id);
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }

    /* update data in database with instructor ID*/
    public void updateDataWithInstID(String table_name, String field, String value, int id) {


        try {

            String query = String.format(" UPDATE %s SET %s = '%s' WHERE inst_id= %d", table_name, field, value, id);
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }

    /* update data in database of login with email*/
    public void updateDataWithEmail(String table_name, String field, String value, String searchEmail) {


        try {

            String query = String.format(" UPDATE %s SET %s = %s WHERE email= '%s'", table_name, field, value, searchEmail);
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }

    /* update data in database with courseID*/
    public void updateDataWithCourseId(String table_name, String field, String value, int id) {


        try {

            String query = String.format(" UPDATE %s SET %s = '%s' WHERE courses_id= %d", table_name, field, value, id);
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }

    /* update data in database with parentCourse*/
    public void updateParentCourse(String value, int id) {

        updateDataWithCourseId("courses", "parent_course", value, id);

    }

    /* general delete data */
    public void deleteData(String table_name, String condition) {


        try {

            String query = String.format(" DELETE FROM " + table_name + " WHERE  " + condition +";");
            st.executeUpdate(query);

        } catch (Exception e) {

            System.out.println(e);
            System.out.println("not connected");
        }
    }


    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currentDateandTime = sdf.format(now);
        return currentDateandTime;
    }

    public void seeGrade(int id) {
        try {


            String query = String.format(" select course_name , course_id from courses INNER JOIN course_std ON  course_std.course_id=courses.courses_id WHERE course_std.std_id= '%d';", id);


            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {
                String course_name = rs.getString("course_name");
                String course_id = rs.getString("course_id");


                // print the results
                System.out.println(course_id + " " + course_name);


            }

            //new connection();


        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }


    }

    public void near_to_start() {
        try {
            String query = " SELECT course_name,start_date FROM courses";
            ResultSet rs = st.executeQuery(query);


            while (rs.next()) {
                //String current_time = getCurrentTimeStamp();
                String start_date = rs.getString("start_date");
                long diff = getDiff(start_date);
                String course_name = rs.getString("course_name");

                if (diff <= 15 && diff > 0)
                    System.out.println(course_name);
                else
                    System.out.println("no courses about to start");


            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

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
}



