package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.EventCreation;
import com.creativehub.backend.services.dto.EventCreationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventCreationMapper {
	@Mapping(source = "eventId", target = "event.id")
	EventCreation eventCreationDtoToEventCreation(EventCreationDto eventCreationDto);

	@Mapping(source = "event.id", target = "eventId")
	EventCreationDto eventCreationToEventCreationDto(EventCreation eventCreation);

	@Mapping(source = "eventId", target = "event.id")
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEventCreationFromEventCreationDto(EventCreationDto eventCreationDto, @MappingTarget EventCreation eventCreation);
}
