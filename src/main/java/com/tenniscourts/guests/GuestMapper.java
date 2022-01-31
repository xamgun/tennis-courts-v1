package com.tenniscourts.guests;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    @InheritInverseConfiguration
    GuestDTO map(Guest source);

    @Mapping(target = "id", source = "guestId")
    @Mapping(target = "name", source = "guestName")
    Guest map(GuestDTO source);

    @InheritInverseConfiguration
    List<GuestDTO> map(List<Guest> source);
}
