package com.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entity.College;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class CollegeDao {
	@Autowired
	SessionFactory factory;

	public String insertData(College college) {
		Transaction transaction = null;
		String msg;
		try (Session session = factory.openSession()) { // Using try-with-resources to auto-close session
			transaction = session.beginTransaction();
			session.save(college);
			transaction.commit();
			msg = "Data is inserted successfully";
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			msg = "Error inserting data: " + e.getMessage();
		}
		return msg;
	}

	public String deleteData(int college_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			College college = session.get(College.class, college_id);
			session.remove(college);
			;
			transaction.commit();
			session.close();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg = "Data is deleted successfully";
	}

	public String updateData(College college, int college_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			College c1 = session.get(College.class, college_id);
			if (c1 != null) {
				c1.setCollege_address(college.getCollege_address());
				c1.setCollege_name(college.getCollege_name());
				c1.setLibrary(college.getLibrary());
				session.merge(c1);
				transaction.commit();
				session.close();
				msg = "Data is updated successfully";
			} else {
				msg = "Book not found with ID :" + college_id;
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			msg = "Error updating book:" + e.getMessage();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg = "Data is updated successfully";
	}

	public College getSingleData(int college_id) {
		Session session = null;
		Transaction transaction = null;
		College college = null;
		try {

			session = factory.openSession();
			transaction = session.beginTransaction();
			college = session.get(College.class, college_id);
			if (college == null) {
				System.out.println("data not found with id " + college_id);
			} else {
				System.out.println("data fetched successfully.");
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return college;
	}

	public List<College> getAllData() {
		Session session = null;
		List<College> list = null;

		try {
			session = factory.openSession(); // Ensure factory is initialized
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<College> criteriaQuery = criteriaBuilder.createQuery(College.class);

			Root<College> root = criteriaQuery.from(College.class); // Set the root entity
			criteriaQuery.select(root);

			Query<College> query = session.createQuery(criteriaQuery);
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

}
