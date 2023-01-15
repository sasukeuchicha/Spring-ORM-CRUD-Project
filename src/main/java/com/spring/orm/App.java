package com.spring.orm;


import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        
        StudentDao studentDao = context.getBean("StudentDao", StudentDao.class);
        
        Student student1 = new Student(1, "Jayanth K", "Tenth");
        Student student2 = new Student(2, "Himresh Boopi", "Eleventh");
        Student student3 = new Student(3, "Gautham JK", "Twelfth");
        //create
        studentDao.insert(student1);
        studentDao.insert(student2);
        //read
        studentDao.read(1);
        //read all in table
        ArrayList<Student> students = studentDao.readAll();
        
        for(Student student: students) {
        	System.out.println(student);
        }
        
        //update
        studentDao.update(studentDao, 3, student3);
        
        //delete
        studentDao.delete(student1.getStudentId());
        //verify
        students = studentDao.readAll();
        for(Student student: students) {
        	System.out.println(student);
        }
        
    }
}
