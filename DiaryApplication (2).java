/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t22;

/**
 *
 * @author Student
 */
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DiaryApplication {
    private static final String FILE_NAME = "diary_db.txt";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
           
            switch (choice) {
                case "1":
                    addDiary(scanner);
                    break;
                case "2":
                    showAllDiaries();
                    break;
                case "3":
                    showDiaryById(scanner);
                    break;
                case "4":
                    System.out.println("Exiting application...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
   
    private static void displayMenu() {
        System.out.println("\nMENU");
        System.out.println("1. Add Diary");
        System.out.println("2. Show All Diary");
        System.out.println("3. Show Diary");
        System.out.println("4. Exit");
        System.out.print("\nType here: ");
    }
   
    private static void addDiary(Scanner scanner) {
        try {
            System.out.print("ID: ");
            String id = scanner.nextLine().trim();
           
          
            try {
                Integer.parseInt(id);
            } catch (NumberFormatException e) {
                System.out.println("Error: ID must be a number.");
                return;
            }
           
          
            if (diaryExists(id)) {
                System.out.println("Error: Diary with this ID already exists.");
                return;
            }
           
            System.out.print("Date (yyyy/MM/dd): ");
            String dateStr = scanner.nextLine().trim();
           
        
            try {
                dateFormat.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Error: Invalid date format. Please use yyyy/MM/dd.");
                return;
            }
           
            System.out.print("Diary: ");
            String content = scanner.nextLine().trim();
           
          
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
                writer.println(id);
                writer.println(dateStr);
                writer.println(content);
                System.out.println("Diary added successfully!");
            } catch (IOException e) {
                System.out.println("Error saving diary: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
   
    private static boolean diaryExists(String id) {
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            while (fileScanner.hasNextLine()) {
                String currentId = fileScanner.nextLine().trim();
                
                if (fileScanner.hasNextLine()) fileScanner.nextLine();
                if (fileScanner.hasNextLine()) fileScanner.nextLine();
               
                if (currentId.equals(id)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            
            return false;
        }
        return false;
    }
   
    private static void showAllDiaries() {
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            if (!fileScanner.hasNextLine()) {
                System.out.println("No diaries found.");
                return;
            }
           
            System.out.println("\nDIARIES");
            while (fileScanner.hasNextLine()) {
                String id = fileScanner.nextLine().trim();
                String date = fileScanner.hasNextLine() ? fileScanner.nextLine().trim() : "";
                String content = fileScanner.hasNextLine() ? fileScanner.nextLine().trim() : "";
               
                System.out.println("---");
                System.out.println("Diary " + id + " - " + date);
                System.out.println(content);
            }
            System.out.println("---");
        } catch (FileNotFoundException e) {
            System.out.println("No diaries found.");
        }
    }
   
    private static void showDiaryById(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine().trim();
       
        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            boolean found = false;
           
            while (fileScanner.hasNextLine()) {
                String currentId = fileScanner.nextLine().trim();
                String date = fileScanner.hasNextLine() ? fileScanner.nextLine().trim() : "";
                String content = fileScanner.hasNextLine() ? fileScanner.nextLine().trim() : "";
               
                if (currentId.equals(id)) {
                    System.out.println("---");
                    System.out.println("Diary " + id + " - " + date);
                    System.out.println(content);
                    System.out.println("---");
                    found = true;
                    break;
                }
            }
           
            if (!found) {
                System.out.println("Diary with ID " + id + " not found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No diaries found.");
        }
    }
}

