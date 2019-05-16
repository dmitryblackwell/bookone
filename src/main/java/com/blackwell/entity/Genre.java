package com.blackwell.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="genres")
public class Genre {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;

}
