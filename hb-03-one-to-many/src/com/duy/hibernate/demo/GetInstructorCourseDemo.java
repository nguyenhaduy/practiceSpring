package com.duy.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.duy.hibernate.demo.entity.Course;
import com.duy.hibernate.demo.entity.Instructor;
import com.duy.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();

		try {
						
			// start a transaction
			session.beginTransaction();
						
			// get the instructor form db
			int theId = 2;
			Instructor tempInstructor =  session.get(Instructor.class,  theId);
			
			System.out.println("Instructor: " + tempInstructor);
					
			// get course for the instructor
			System.out.println("Course: " + tempInstructor.getCourses());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			
			session.close();
			
			factory.close();
		}
		
	}

}
