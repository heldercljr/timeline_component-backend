package com.media4all.timeline.dtos;

import lombok.*;

@Data
@AllArgsConstructor
public class EventoDTO {

	@NonNull
	private Long id;

	@NonNull
	private String title;
	
	@NonNull
	private String description;
	
	@NonNull
	private String timestamp;
	
	@NonNull
	private String type;
	
	@NonNull
	private String icon;
	
	@NonNull
	private String user;
}
