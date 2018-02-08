package com.duy.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.duy.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();

		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastname='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			// display the students
						System.out.println("\n\nStudents who have last name of Doe");
						displayStudents(theStudents);
			
			// query students: lastName='Doe' OR firstName='Daffy'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Doe' Or s.firstName='Daffy'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Doe OR first name of Daffy");
			displayStudents(theStudents);			
			
			// query students: lastName='Doe' OR firstName='Daffy'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.email LIKE '%duy.com'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who email ends with duy.com");
			displayStudents(theStudents);	
						
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
