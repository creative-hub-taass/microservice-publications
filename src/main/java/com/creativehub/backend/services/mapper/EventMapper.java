package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Event;
import com.creativehub.backend.models.EventCreation;
import com.creativehub.backend.services.dto.EventCreationDto;
import com.creativehub.backend.services.dto.EventDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventMapper {
	Event eventDtoToEvent(EventDto eventDto);

	EventDto eventToEventDto(Event event);

	@Mapping(source = "eventId", target = "event.id")
	EventCreation eventCreationDtoToEventCreation(EventCreationDto eventCreationDto);

	@Mapping(source = "event.id", target = "eventId")
	EventCreationDto eventCreationToEventCreationDto(EventCreation eventCreation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEventFromEventDto(EventDto eventDto, @MappingTarget Event event);
}
