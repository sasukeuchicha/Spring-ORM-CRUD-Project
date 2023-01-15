package com.spring.orm.dao;



import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;


import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.spring.orm.entities.Student;

public class StudentDao {
	
	private HibernateTemplate hibernateTemplate;
	

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//insert student
	@Transactional
	public int insert(Student student) {
		Integer i = (Integer) this.hibernateTemplate.save(student);
		return i;
	}
	
	public void read(int primaryKey) {
		Student student = (Student)this.hibernateTemplate.get(Student.class, primaryKey);
		
		System.out.println(student);
	}

	public ArrayList<Student> readAll() {
		ArrayList<Student> students = (ArrayList<Student>) this.hibernateTemplate.loadAll(Student.class);
		return students;
	}
	
	@Transactional
	public void delete(int primaryKey) {
		Student student;
		try {
		student = hibernateTemplate.load(Student.class, primaryKey);
		this.hibernateTemplate.delete(student);
		}
		catch( IllegalArgumentException | EntityNotFoundException jae ) {
			System.out.println("The row cant be deleted as there is no row with that primary key: "+primaryKey);
			
		}
		
	}
	@Transactional
	public int update(StudentDao studentDao, int primaryKey, Student student) {
		studentDao.delete(primaryKey);
		student.setStudentId(primaryKey);
		Integer i = (Integer) studentDao.insert(student);
		return i;
	}

	
	
}
