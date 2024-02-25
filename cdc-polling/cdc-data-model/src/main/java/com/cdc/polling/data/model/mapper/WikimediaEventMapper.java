package com.cdc.polling.data.model.mapper;

import com.cdc.polling.data.model.dtos.WikimediaEventDTO;
import com.cdc.polling.data.model.entities.WikimediaEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WikimediaEventMapper {
    WikimediaEventMapper INSTANCE = Mappers.getMapper(WikimediaEventMapper.class);

    @Mapping(target = "titleUrl", source = "title_url")
    @Mapping(target = "eventTime", source = "timestamp")
    WikimediaEvent dtoToEntity(WikimediaEventDTO eventDTO);
}
