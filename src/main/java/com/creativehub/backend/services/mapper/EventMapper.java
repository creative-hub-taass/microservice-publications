package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.Event;
import com.creativehub.backend.services.dto.EventDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventMapper {
	Event eventDtoToEvent(EventDto eventDto);

	EventDto eventToEventDto(Event event);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEventFromEventDto(EventDto eventDto, @MappingTarget Event event);
}
