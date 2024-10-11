package com.media4all.timeline.entities.TimeLine;

import com.media4all.timeline.entities.Event.Event;

import java.util.List;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Event> events;

	@ElementCollection
	@CollectionTable(name = "event_types", joinColumns = @JoinColumn(name = "timeline_id"))
	@Column(name = "event_type")
	private List<String> eventTypes;
}
