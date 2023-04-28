package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Address extends  BaseEntity{
	@Id @GeneratedValue
	private Long id;

	private String zipcode;
	private String addr1;
	private String addr2;

	@OneToOne(mappedBy="address")
	private Member member;
}
