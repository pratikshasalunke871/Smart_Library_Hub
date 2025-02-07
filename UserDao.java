package com.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entity.User;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class UserDao {
	@Autowired
	SessionFactory factory;

	public String insertData(User user) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
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
		return msg = "Data inserted successfully";
	}

	public String deleteData(int user_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			User user = session.get(User.class, user_id);
			if (user != null) {
				session.remove(user);
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

	public String updatedata(User user, int user_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();

			User user1 = session.get(User.class, user_id);
			if (user1 == null) {
				msg = "User not found";
			} else {
				user1.setLibrarycard(user.getLibrarycard());
				user1.setUser_address(user.getUser_address());
				user1.setUser_email(user.getUser_email());
				user1.setUser_name(user.getUser_name());
				user1.setUser_password(user.getUser_password());
				user1.setUser_phone(user.getUser_phone());

				session.update(user1);
				transaction.commit();
				msg = "Data updated successfully";
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback instead of commit
			}
			msg = "Error updating data: " + e.getMessage();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg; // Return the correct message
	}

	public User getSingleData(int user_id) {
		Session session = null;
		Transaction transaction = null;
		User user = null;
		try {

			session = factory.openSession();
			transaction = session.beginTransaction();
			user = session.get(User.class, user_id);
			if (user == null) {
				System.out.println("data not found with id " + user_id);
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
		return user;
	}

	public List<User> getAllData() {
		Session session = null;
		List<User> list = null;

		try {
			session = factory.openSession(); // Ensure factory is initialized
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

			Root<User> root = criteriaQuery.from(User.class); // Set the root entity
			criteriaQuery.select(root);

			Query<User> query = session.createQuery(criteriaQuery);
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