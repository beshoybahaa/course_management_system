import java.io.*;
import java.util.* ;
import java .sql.*;


public class std {




    /**
     *
     * @author DELL
     */
        static Statement st ;
public int stdID;


       public std() {

           try {
               Scanner scanner = new Scanner(new File("F:\\CS\\second year\\first term\\pl2\\project\\idcode.txt"));
               while(scanner.hasNext())
               {
                  String stdIDss =scanner.next();
                   System.out.println(stdIDss);
                   stdID= Integer.parseInt(stdIDss);
               }
               scanner.close();
           } catch (FileNotFoundException e) {
               System.out.println("An error occurred.");
               e.printStackTrace();
           }

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
                    //update();
                    break;
                case 2:
                    System.out.println("enter your id :");
                    int std_id = scn.nextInt();
                    System.out.println("enter course id :");
                    int course_id=scn.nextInt();
                    //seeMyGrade( std_id, course_id);
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
                    //getCourses(id);
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
        public void update(String field,String value){

            Scanner scn =new Scanner (System.in);

            String table_name = "std_inf";
            updateDataWithStdID(table_name,field ,value , stdID);

        }



        public String seeMyGrade(int course_id) {
            try
            {



                String query=   String.format(" select grades from course_std where std_id=%d ;" , stdID);



                ResultSet rs=st.executeQuery(query);

                //System.out.println("course_name      grade ");


                while (rs.next())
                {
                    //String course_name = rs.getString("course_name");
                    String grades = rs.getString("grades");
                    //System.out.println(course_name);
                    System.out.println(grades);





                    // print the results
                    return grades;


                }

                //new connection();


            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            return null;
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






        public StringBuilder getCourses (){

            try
            {
                System.out.println(stdID);
                String query = String.format( " SELECT course_id ,course_name FROM course_std inner join courses on course_std.course_id = courses.courses_id  WHERE std_id=%d ", stdID);


                // create the java statement

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                System.out.println("course_name"+"    "+"course_id" );
                // iterate through the java resultset
                StringBuilder stringBuilder = new StringBuilder();
                while (rs.next())
                {

                    String course_name= rs.getString("course_name");
                    String course_id= rs.getString("course_id");
                    System.out.println(course_name);
                    System.out.println(course_id);
                    String s = course_name + " = " + course_id +"\n";
                    stringBuilder.append(s);


                    // print the result



                }
                return stringBuilder;
            }
            catch (Exception e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());

            }
            return null;
        }
}


