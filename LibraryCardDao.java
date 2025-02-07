package com.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entity.LibraryCard;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class LibraryCardDao {
	@Autowired
	SessionFactory factory;

	public String insertData(LibraryCard labcard) {
		Transaction transaction = null;
		String msg;
		try (Session session = factory.openSession()) { // Using try-with-resources to auto-close session
			transaction = session.beginTransaction();
			session.save(labcard);
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

	public String deleteData(int card_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			LibraryCard labcard = session.get(LibraryCard.class, card_id);
			session.remove(labcard);
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

	public String updateData(LibraryCard labcard, int card_id) {
		Session session = null;
		Transaction transaction = null;
		String msg;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();

			// Check if LibraryCard exists
			LibraryCard card = session.get(LibraryCard.class, card_id);
			if (card == null) {
				return "Library card not found with ID: " + card_id;
			}

			// Update fields
			card.setCard_duedate(labcard.getCard_duedate());
			card.setCard_expirydate(labcard.getCard_expirydate());
			card.setCard_fineammount(labcard.getCard_fineammount());
			card.setUser(labcard.getUser());

			session.update(card); // Try update first
			transaction.commit();
			msg = "Data updated successfully";

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			msg = "Error updating library card: " + e.getMessage();
			e.printStackTrace(); // Consider using a logger here
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;
	}

	public LibraryCard getSingleData(int card_id) {
		Session session = null;
		Transaction transaction = null;
		LibraryCard labcard = null;
		try {

			session = factory.openSession();
			transaction = session.beginTransaction();
			labcard = session.get(LibraryCard.class, card_id);
			if (labcard == null) {
				System.out.println("data not found with id " + card_id);
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
		return labcard;
	}

	public List<LibraryCard> getAllData() {
		Session session = null;
		List<LibraryCard> list = null;

		try {
			session = factory.openSession(); // Ensure factory is initialized
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<LibraryCard> criteriaQuery = criteriaBuilder.createQuery(LibraryCard.class);

			Root<LibraryCard> root = criteriaQuery.from(LibraryCard.class); // Set the root entity
			criteriaQuery.select(root);

			Query<LibraryCard> query = session.createQuery(criteriaQuery);
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
