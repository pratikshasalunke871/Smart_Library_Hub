package com.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class LibraryCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int card_id;
	private double card_fineammount;
	private String card_duedate;
	private String card_expirydate;
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id ", referencedColumnName = "user_id")
	private User user;

	public LibraryCard() {

	}

	public LibraryCard(int card_Id, double card_fineammount, String card_duedate, String card_expirydate, User user) {
		super();
		this.card_id = card_Id;
		this.card_fineammount = card_fineammount;
		this.card_duedate = card_duedate;
		this.card_expirydate = card_expirydate;
		this.user = user;
	}

	public int getCard_Id() {
		return card_id;
	}

	public void setCard_Id(int card_Id) {
		this.card_id = card_Id;
	}

	public double getCard_fineammount() {
		return card_fineammount;
	}

	public void setCard_fineammount(double card_fineammount) {
		this.card_fineammount = card_fineammount;
	}

	public String getCard_duedate() {
		return card_duedate;
	}

	public void setCard_duedate(String card_duedate) {
		this.card_duedate = card_duedate;
	}

	public String getCard_expirydate() {
		return card_expirydate;
	}

	public void setCard_expirydate(String card_expirydate) {
		this.card_expirydate = card_expirydate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LibraryCard [card_Id=" + card_id + ", card_fineammount=" + card_fineammount + ", card_duedate="
				+ card_duedate + ", card_expirydate=" + card_expirydate + ", user=" + user + "]";
	}

}
