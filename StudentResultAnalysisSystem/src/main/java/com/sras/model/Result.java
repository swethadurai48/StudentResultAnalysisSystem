package com.sras.model;

public class Result {
    private Student student;
    private Subject subject;
    private Mark mark;
    private double percentage;
    private String grade;
    private String status;

    public Result() {}

    public Result(Student student, Subject subject, Mark mark, double percentage, String grade, String status) {
        this.student = student;
        this.subject = subject;
        this.mark = mark;
        this.percentage = percentage;
        this.grade = grade;
        this.status = status;
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }
    public Mark getMark() { return mark; }
    public void setMark(Mark mark) { this.mark = mark; }
    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
