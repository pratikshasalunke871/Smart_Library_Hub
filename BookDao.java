package com.project.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entity.Book;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Repository
public class BookDao {
	@Autowired
	SessionFactory factory;

	public String insretData(Book book) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(book);
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

	public String deleteData(int book_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Book book = session.get(Book.class, book_id);
			if (book != null) {
				session.remove(book);
				transaction.commit();
				msg = "data deleted successfully";
			} else {
				msg = "data not found";
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
		return msg = "Data deleted successfully";
	}

	public String updateData(Book book, int book_id) {
		Session session = null;
		Transaction transaction = null;
		String msg = null;

		try {
			session = factory.openSession();
			transaction = session.beginTransaction();

			// Fetch book from database
			Book book1 = session.get(Book.class, book_id);

			if (book1 != null) {
				// Update book details
				book1.setBook_title(book.getBook_title());
				book1.setBook_author(book.getBook_author());
				book1.setBook_publisher(book.getBook_publisher());
				book1.setBook_year(book.getBook_year());

				session.merge(book1); // Save changes
				transaction.commit();
				msg = "Data updated successfully";
			} else {
				msg = "Book not found with ID: " + book_id;
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback(); // Rollback transaction on error
			}
			e.printStackTrace();
			msg = "Error updating book: " + e.getMessage();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;
	}

	public Book getSingleData(int book_id) {
		Session session = null;
		Transaction transaction = null;
		Book book = null;
		try {

			session = factory.openSession();
			transaction = session.beginTransaction();
			book = session.get(Book.class, book_id);
			if (book == null) {
				System.out.println("data not found with id " + book_id);
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
		return book;
	}

	public List<Book> getAllData() {
		Session session = null;
		List<Book> list = null;

		try {
			session = factory.openSession(); // Ensure factory is initialized
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

			Root<Book> root = criteriaQuery.from(Book.class); // Set the root entity
			criteriaQuery.select(root);

			Query<Book> query = session.createQuery(criteriaQuery);
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