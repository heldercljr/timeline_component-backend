package com.media4all.timeline.entities.User;

import com.media4all.timeline.entities.Event.Event;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Event> events;
}
