package com.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Library {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int library_id;
	private String library_name;
	private int library_phone;

	@OneToOne(mappedBy = "library")
	private College college;

	public Library() {

	}

	public Library(int library_id, String library_name, int library_phone, College college) {
		super();
		this.library_id = library_id;
		this.library_name = library_name;
		this.library_phone = library_phone;
		this.college = college;
	}

	public int getLibrary_id() {
		return library_id;
	}

	public void setLibrary_id(int library_id) {
		this.library_id = library_id;
	}

	public String getLibrary_name() {
		return library_name;
	}

	public void setLibrary_name(String library_name) {
		this.library_name = library_name;
	}

	public int getLibrary_phone() {
		return library_phone;
	}

	public void setLibrary_phone(int library_phone) {
		this.library_phone = library_phone;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "Library [library_id=" + library_id + ", library_name=" + library_name + ", library_phone="
				+ library_phone + ", college=" + college + "]";
	}

}
