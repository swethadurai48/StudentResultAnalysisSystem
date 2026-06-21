package com.sras.model;

public class Mark {
    private int markId;
    private String studentId;
    private String subjectId;
    private double internalMarks;
    private double externalMarks;
    private double totalMarks;

    public Mark() {}

    public Mark(int markId, String studentId, String subjectId, double internalMarks, double externalMarks, double totalMarks) {
        this.markId = markId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.internalMarks = internalMarks;
        this.externalMarks = externalMarks;
        this.totalMarks = totalMarks;
    }

    public int getMarkId() { return markId; }
    public void setMarkId(int markId) { this.markId = markId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getSubjectId() { return subjectId; }
    public void setSubjectId(String subjectId) { this.subjectId = subjectId; }
    public double getInternalMarks() { return internalMarks; }
    public void setInternalMarks(double internalMarks) { this.internalMarks = internalMarks; }
    public double getExternalMarks() { return externalMarks; }
    public void setExternalMarks(double externalMarks) { this.externalMarks = externalMarks; }
    public double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(double totalMarks) { this.totalMarks = totalMarks; }
}
