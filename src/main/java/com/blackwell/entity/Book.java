package com.blackwell.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="books")
public class Book {
	
	@Id
	@Column(name="isbn")
	private long isbn;
	
	@Column(name="author")
	private String author;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private float price;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="genre")
	private Genre genre;
	
	@Column(name="description")
	private String description;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Comment> comments;

}
