package com.creativehub.backend.services.mapper;

import com.creativehub.backend.models.EventCreation;
import com.creativehub.backend.services.dto.EventCreationDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface EventCreationMapper {
	EventCreation eventCreationDtoToEventCreation(EventCreationDto eventCreationDto);

	EventCreationDto eventCreationToEventCreationDto(EventCreation eventCreation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	void updateEventCreationFromEventCreationDto(EventCreationDto eventCreationDto, @MappingTarget EventCreation eventCreation);
}
