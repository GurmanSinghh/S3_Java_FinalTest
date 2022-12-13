package com.example.test;

import java.io.Serializable;

public class Doctor implements Serializable {

    private static final long serialVersionUID = 3789909326487155148L;
    private int doctorId;
    private String doctorName;
    private double grossSalary;
    private double netSalary;

    public Doctor(int doctorId, String doctorName, double grossSalary, double netSalary) {
        super();
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.grossSalary = grossSalary;
        this.netSalary = netSalary;
    }

    public Doctor() {
        super();
    }

    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public double getGrossSalary() {
        return grossSalary;
    }
    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
    public double getNetSalary() {
        return netSalary;
    }
    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

}
