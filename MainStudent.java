

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class MainStudent implements Serializable {
    public static ArrayList<Student> studentsList;
    public static final String MAIN_DB_FILE = "database/myDb.txt";
    static class Student implements Serializable{
        private String name;
        private int age;
        private int year;
        private String studentNum;
        private boolean isPresent = false;


        public Student(String name, int age, int year, String studentNum) {
            this.name = name;
            this.age = age;
            this.year = year;
            this.studentNum = studentNum;
        }

        // Setters and getters (Name, Age, Year and Student Number)

        public String getName() // name
        {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() // age
        {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getYear() // year
        {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getstudentNum() // studentNum
        {
            return studentNum;
        }

        public void setStudentNum(String studentNum) {
            this.studentNum = studentNum;
        }

        public boolean isPresent() {
            return isPresent;
        }

        public void setPresent(boolean present) {
            isPresent = present;
        }
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {

        }
    }

    public static void insertStudent() throws IOException {
        String sName, studentNum;
        int age, year;
        clearScreen();

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);


        System.out.println("Enter Student Name:");
        sName = in.readLine();

        System.out.println("Enter Student Age (Integer):");
        age = Integer.valueOf(in.readLine());

        System.out.println("Enter Student Year (Integer):");
        year = Integer.valueOf(in.readLine());

        System.out.println("Enter Student Student Number:");
        studentNum = in.readLine();

        Student student = new Student(sName, age, year, studentNum);
        studentsList.add(student); // add student
    }

    public static void showStudent() {
        clearScreen();

        System.out.println("\n........................Students........................");
        for (int j = 0; j < studentsList.size(); j++) {
            Student st = studentsList.get(j);
            System.out.println("Student : " + (j + 1));
            System.out.println("Name: " + st.getName() + " - Age: " + st.getAge() + " - Year: " + st.getYear() + " - Student Number: " + st.getstudentNum());
            System.out.println("...........................................................................");
        }

        //for get char
        try {
            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);
            in.readLine();
        } catch (IOException exception) {

        }
    }

    private static void makePresentsForAllStudent() {
        clearScreen();

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        try {
            System.out.println("\n........................Students........................");
            for (int j = 0; j < studentsList.size(); j++) {
                Student st = studentsList.get(j);
                System.out.println("Name: " + st.getName() + " - Age: " + st.getAge() + " - Year: " + st.getYear() + " - Student Number: " + st.getstudentNum());
                System.out.println("Is Presented then ENTER \'p\' else ENTER \'n\'=>");
                String readChar = in.readLine();
                if (readChar.equalsIgnoreCase("p")) {
                    st.setPresent(true);
                    System.out.println("" + st.getName() + " Present today...");
                } else {
                    st.setPresent(false);
                    System.out.println("" + st.getName() + " Absent today...");
                }
                System.out.println("...........................................................................");
            }

            in.readLine();
        } catch (IOException exception) {

        }
    }

    private static void showAttendance() {
        clearScreen();

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        try {
            System.out.println("\n........................Students........................");
            for (int j = 0; j < studentsList.size(); j++) {
                Student st = studentsList.get(j);
                System.out.println("Name: " + st.getName() + " - Age: " + st.getAge() + " - Year: " + st.getYear() + " - Student Number: " + st.getstudentNum());
                System.out.println("Is Presented then ENTER \'p\' else ENTER \'n\'=>");
                String readChar = in.readLine();
                if (st.isPresent()) {
                    System.out.println("" + st.getName() + " Present today...");
                } else {
                    System.out.println("" + st.getName() + " Absent today...");
                }
                System.out.println("...........................................................................");
            }

            in.readLine();
        } catch (IOException exception) {

        }
    }

    public static void exportToFile(){

        try{  // Catch errors in I/O if necessary.
            // Open a file to write to, named SavedObj.sav.
            FileOutputStream saveFile=new FileOutputStream(MAIN_DB_FILE);

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(studentsList);

            // Close the file.
            save.close(); // This also closes saveFile.
        }
        catch(Exception exc){
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    public static void importFromFile(){

        try{
            // Open file to read from, named SavedObj.sav.
            FileInputStream saveFile = new FileInputStream(MAIN_DB_FILE);

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            // Now we do the restore.
            // readObject() returns a generic Object, we cast those back
            // into their original class type.

            studentsList = (ArrayList<Student>) save.readObject();

            // Close the file.
            save.close(); // This also closes saveFile.
        }
        catch(Exception exc){
            exc.printStackTrace(); // If there was an error, print the info.
        }

        // Print the values, to see that they've been recovered.
        if (studentsList!=null&&studentsList.size()>0)
            System.out.println("\t\t" + "Loaded old data...");
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("============" + "=================");
        System.out.println("Students Personal Details");
        System.out.println("============" + "=================");
        studentsList = new ArrayList<Student>();

        String strChoice;


        try {
            File mainDbFile = new File(MAIN_DB_FILE);
            mainDbFile.mkdirs();
            importFromFile();
        } catch (Exception e) {

        }

        boolean isRun = true;
        while (isRun) {
            clearScreen();

            System.out.println("Select your choice.... ");
            System.out.println("INSERT STUDENT ENTER               1");
            System.out.println("GET ALL STUDENT ENTER              2 ");
            System.out.println("SET ALL STUDENT ATTENDANCE ENTER   3");
            System.out.println("GET ALL STUDENT ATTENDANCE ENTER   4");
            System.out.println("SAVE DATA ENTER                    5");
            System.out.println("EXIT 0");

            strChoice = "";
            try {
                InputStreamReader converter = new InputStreamReader(System.in);
                BufferedReader in = new BufferedReader(converter);

                System.out.println("Enter Choice=>");
                strChoice = in.readLine();
            } catch (IOException exception) {

            }

            switch (strChoice) {
                case "1":
                    try {
                        insertStudent();
                    } catch (IOException exception) {

                    }
                    break;

                case "2":
                    showStudent();
                    break;
                case "3":
                    makePresentsForAllStudent();
                    break;
                case "4":
                    showAttendance();
                    break;
                case "5":
                    exportToFile();
                    break;
                default:
                    isRun = false;
                    break;
            }

        }

    }
}
