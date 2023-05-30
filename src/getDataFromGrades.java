public class getDataFromGrades {
    private int std_id;
    private int course_id;
    private int grades;
    public getDataFromGrades(int std_id, int course_id, int grades) {
        this.std_id = std_id;
        this.course_id = course_id;
        this.grades = grades;
    }
    public int getStd_id() {
        return std_id;
    }
    public int getCourse_id() {
        return course_id;
    }
    public int getGrades() {
        return grades;
    }
}
