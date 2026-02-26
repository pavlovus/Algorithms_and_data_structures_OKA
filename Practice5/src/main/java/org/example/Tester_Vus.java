/* Результати дослідження стійкості:
* SelectionSort - не стійкий
* InsertionSort - стійкий
* ShellSort - не стійкий
* MergeSort - стійкий
* QuickSort - не стійкий
*  */

package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Tester_Vus {
    public static void main(String[] args) {
        ArrayList<Student_Vus> students = new ArrayList<>();
        try(Scanner input = new Scanner(new File("/Users/pavlovus/Desktop/OKA/Practice5/src/main/java/org/example/students_ready.txt"))) {
            while (input.hasNextLine()) {
                String name = input.next() + " " + input.next() + " " + input.next();
                int age = input.nextInt();
                double avg = input.nextDouble();
                int group = input.nextInt();
                int year = input.nextInt();
                students.add(new Student_Vus(name, age, avg, group, year));
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }

        System.out.println("Список студентів:");
        students.forEach(System.out::println);

        Collections.sort(students);
        System.out.println("\nСортування за іменем:");
        students.forEach(System.out::println);

        students.sort(Student_Vus.nameDescendingComparator);
        System.out.println("\nСортування за іменем в зворотньому порядку:");
        students.forEach(System.out::println);

        students.sort(Student_Vus.ageAscendingComparator);
        //InsertionSort.sort(students.toArray(new Student[0]), Student.ageAscendingComparator);
        //SelectionSort.sort(students.toArray(new Student[0]), Student.ageAscendingComparator);
        //ShellSort.sort(students.toArray(new Student[0]), Student.ageAscendingComparator);
        //MergeSort.sort(students.toArray(new Student[0]), Student.ageAscendingComparator);
        //QuickSort.sort(students.toArray(new Student[0]), Student.ageAscendingComparator);
        System.out.println("\nСортування за віком за зростанням:");
        students.forEach(System.out::println);

        students.sort(Student_Vus.ageDescendingComparator);
        System.out.println("\nСортування за віком за спаданням:");
        students.forEach(System.out::println);

        students.sort(Student_Vus.gradeAscendingComparator);
        System.out.println("\nСортування за середньою оцінкою за зростанням:");
        students.forEach(System.out::println);

        students.sort(Student_Vus.gradeDescendingComparator);
        System.out.println("\nСортування за середньою оцінкою за спаданням::");
        students.forEach(System.out::println);

        students.sort(Student_Vus.yearThenGroupComparator);
        System.out.println("\nСортування за роком навчання та за групами:");
        students.forEach(System.out::println);
    }
}
