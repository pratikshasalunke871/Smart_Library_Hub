package com.project.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class College {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int college_id;
	private String college_name;
	private String college_address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "library_id")
	private Library library;

	public College() {
		super();
		// TODO Auto-generated constructor stub
	}

	public College(int college_id, String college_name, String college_address, Library library) {
		super();
		this.college_id = college_id;
		this.college_name = college_name;
		this.college_address = college_address;
		this.library = library;
	}

	public int getCollege_id() {
		return college_id;
	}

	public void setCollege_id(int college_id) {
		this.college_id = college_id;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

	public String getCollege_address() {
		return college_address;
	}

	public void setCollege_address(String college_address) {
		this.college_address = college_address;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	@Override
	public String toString() {
		return "College [college_id=" + college_id + ", college_name=" + college_name + ", college_address="
				+ college_address + ", library=" + library + "]";
	}

}
