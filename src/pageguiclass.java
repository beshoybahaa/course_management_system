/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Thomas
 */
public class pageguiclass {
   
private String branch ;
private int  price ;
private String parent_course ;
private String start_date ;
private String end_date ;
private int  days ;
private String inst_name;
private String  room;
private int  number_of_students ;
private int grades ;


    
            
            
            
            
            
 
    
    
    public pageguiclass(String inst_name, String room, String branch, int price, String parent_course, int number_of_students, int grades, String start_date, int days, String end_date) {
       
        this. branch =   branch ;
        this.price=price ;
        this.parent_course=parent_course ;
        this.start_date=start_date ;
         this.end_date=end_date ;
        this.days=days ;
           this.inst_name= inst_name ; 
           this.room= room ;
           this.number_of_students= number_of_students ;
            this.grades= grades ;
        
             
           
           
          
              
          
    }
    public String getbranch() {
        return branch;
    }
    public int getprice () {
        return price ;
    }
    public String getparent_course() {
        return parent_course;
    }
    public String getstart_date() {
        return start_date;
    }
      public String getend_date() {
        return end_date;
    }
    public int getdays() {
        return days;
    }
   
    public String getinst_name() {
        return inst_name;
    }
     public String getroom() {
        return room;
    }        
        public int getnumber_of_students() {
        return number_of_students ;
    }                 
         public int getgrades() {
        return grades ;
    }             
        
    
}
