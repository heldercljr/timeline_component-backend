package com.media4all.timeline.dtos;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
public class EventDTO {

	private String title;
	
	private String description;
	
	private String timestamp;
	
	private String type;
	
	private String icon;
	
	private String user;
}
