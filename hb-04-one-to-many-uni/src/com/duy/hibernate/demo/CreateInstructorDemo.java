package com.duy.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.duy.hibernate.demo.entity.Course;
import com.duy.hibernate.demo.entity.Instructor;
import com.duy.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

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
					
		//create the objects
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");
		
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.yputube.com",
						"Video Game");
		
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		
		// start a transaction
		session.beginTransaction();
					
		// save the instructor
		//
		// Note: this will ALSO save the details object
		// because of CascadeType.ALL
		//
		session.save(tempInstructor);
		
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
