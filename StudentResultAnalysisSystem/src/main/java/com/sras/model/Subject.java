package com.sras.model;

public class Subject {
    private String subjectId;
    private String subjectName;
    private String subjectCode;
    private int semester;

    public Subject() {}

    public Subject(String subjectId, String subjectName, String subjectCode, int semester) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.semester = semester;
    }

    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
}
