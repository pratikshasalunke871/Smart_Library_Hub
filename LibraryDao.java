package com.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entity.Library;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class LibraryDao {
	@Autowired
	SessionFactory factory;

	public String insertData(Library lab) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(lab);
			transaction.commit();
			msg = "Data inserted successfully";
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
		return msg;
	}

	public String deleteData(int library_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Library lab = session.get(Library.class, library_id);
			if (lab != null) {
				session.remove(lab);
				transaction.commit();
				return msg = "Data Deleted successfully";
			} else {
				msg = "Data not found";
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
		return msg = "Data is deleted successfully";
	}

	public String updateData(Library lab, int library_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Library lab1 = session.get(Library.class, library_id);
			if (lab1 != null) {
				lab1.setCollege(lab.getCollege());
				lab1.setLibrary_name(lab.getLibrary_name());
				lab1.setLibrary_phone(lab.getLibrary_phone());
				session.merge(lab1);
				transaction.commit();
				msg = "Data updated successfully";
			} else {
				msg = "Library is not found with ID:" + library_id;
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			msg = "error updating library:" + e.getMessage();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public Library getSingleData(int library_id) {
		Session session = null;
		Transaction transaction = null;
		Library lab = null;
		try {

			session = factory.openSession();
			transaction = session.beginTransaction();
			lab = session.get(Library.class, library_id);
			if (lab == null) {
				System.out.println("data not found with id " + library_id);
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
		return lab;
	}

	public List<Library> getAllData() {
		Session session = null;
		List<Library> list = null;

		try {
			session = factory.openSession(); // Ensure factory is initialized
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Library> criteriaQuery = criteriaBuilder.createQuery(Library.class);

			Root<Library> root = criteriaQuery.from(Library.class); // Set the root entity
			criteriaQuery.select(root);

			Query<Library> query = session.createQuery(criteriaQuery);
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
