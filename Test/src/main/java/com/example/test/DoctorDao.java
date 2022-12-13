package com.example.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DoctorDao {

    public Doctor getDoctor(String doctorId) {
        Connection connection = null;
        PreparedStatement statement = null;
        Doctor doctor = null;
        try {
            connection = Database.getDBConnection();

            String query = "SELECT DocID, DocName, DocGrSal FROM Doctor WHERE DocID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(doctorId));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt(1));
                doctor.setDoctorName(resultSet.getString(2));
                double grSal = resultSet.getDouble(3);
                doctor.setGrossSalary(grSal);

                double netSal = grSal - (grSal * 0.3);
                doctor.setNetSalary(netSal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public boolean isUserExists(String doctorId) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isDoctorExists = false;
        try {
            connection = Database.getDBConnection();

            String query = "SELECT * FROM Doctor WHERE DocID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(doctorId));
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                isDoctorExists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDoctorExists;
    }

    public boolean deleteDoctor(String doctorId) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isDeleteSuccess = false;
        try {
            connection = Database.getDBConnection();

            String query = "DELETE FROM Doctor WHERE DocID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(doctorId));
            int deleteCount = statement.executeUpdate();
            if (deleteCount == 1) {
                isDeleteSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleteSuccess;
    }
}
