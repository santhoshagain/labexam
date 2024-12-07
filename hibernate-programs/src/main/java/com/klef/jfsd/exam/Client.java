package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        try (Session session = configuration.buildSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Insert operation
            Department department = new Department();
            department.setName("Cyber Security");
            department.setLocation("Block CY");
            department.setHodName("Santhosh");

            session.save(department);
            System.out.println("Inserted department: " + department.getDeptId());
            transaction.commit();

            // Delete operation using HQL
            System.out.print("Enter Department ID to delete: ");
            int deptIdToDelete = sc.nextInt();

            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Department where deptId = :id");
            query.setParameter("id", deptIdToDelete);
            int result = query.executeUpdate();

            if (result > 0) {
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("No department found with ID: " + deptIdToDelete);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
