package com.sras.model;

import java.sql.Date;

public class Student {
    private String studentId;
    private String name;
    private String gender;
    private Date dob;
    private String department;
    private int semester;
    private String email;
    private String phone;

    public Student() {}

    public Student(String studentId, String name, String gender, Date dob, String department, int semester, String email, String phone) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.department = department;
        this.semester = semester;
        this.email = email;
        this.phone = phone;
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
