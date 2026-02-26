package org.example;

import java.util.Comparator;

public class Student_Vus implements Comparable<Student_Vus> {
    private String name;
    private int age;
    private double averageGrade;
    private int group;
    private int year;

    public Student_Vus(String name, int age, double averageGrade, int group, int year) {
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
        this.group = group;
        this.year = year;
    }

    @Override
    public int compareTo(Student_Vus s) {
        return this.name.compareTo(s.name);
    }

    public static Comparator<Student_Vus> yearThenGroupComparator = new Comparator<>() {
        @Override
        public int compare(Student_Vus s1, Student_Vus s2) {
            if (s1.year < s2.year) return -1;
            if (s1.year > s2.year) return 1;

            if (s1.group < s2.group) return -1;
            if (s1.group > s2.group) return 1;
            return 0;
        }
    };

    public static Comparator<Student_Vus> nameDescendingComparator = new Comparator<>() {
        @Override
        public int compare(Student_Vus s1, Student_Vus s2) {
            return s2.name.compareTo(s1.name);
        }
    };

    public static Comparator<Student_Vus> ageAscendingComparator = new Comparator<>() {
        @Override
        public int compare(Student_Vus s1, Student_Vus s2) {
            return Integer.compare(s1.age, s2.age);
        }
    };

    public static Comparator<Student_Vus> ageDescendingComparator = new Comparator<>() {
        @Override
        public int compare(Student_Vus s1, Student_Vus s2) {
            return Integer.compare(s2.age, s1.age);
        }
    };

    public static Comparator<Student_Vus> gradeAscendingComparator = new Comparator<>() {
        @Override
        public int compare(Student_Vus s1, Student_Vus s2) {
            return Double.compare(s1.averageGrade, s2.averageGrade);
        }
    };

    public static Comparator<Student_Vus> gradeDescendingComparator = new Comparator<>() {
        @Override
        public int compare(Student_Vus s1, Student_Vus s2) {
            return Double.compare(s2.averageGrade, s1.averageGrade);
        }
    };

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public double getAverageGrade() { return averageGrade; }

    public void setAverageGrade(double averageGrade) { this.averageGrade = averageGrade; }

    public int getGroup() { return group; }

    public void setGroup(int group) { this.group = group; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year;}

    @Override
    public String toString() {
        return name + " : age = " + age + ", grade = " + averageGrade + ", group = " + group + ", year = " + year;
    }
}
