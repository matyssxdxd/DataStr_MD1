package model;

public class Student {

    private int id;
    private String name;
    private String surname;
    private int age;
    private static int counter = 0;

    public Student() {
        setId();
        setAge(0);
        setName("");
        setSurname("");
    }

    public Student(String name, String surname, int age) {
        setId();
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public static int getCounter() {
        return counter;
    }

    public void setId() {
        id = counter++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student " + id + " " + name + " " + surname + " " + age;
    }
}
