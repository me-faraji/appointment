package com.blubank.doctorappointment.common;

import com.blubank.doctorappointment.controller.dto.DTOMasterCourse;
import com.blubank.doctorappointment.model.MasterCourseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MasterCourseModelMapper {
    MasterCourseModelMapper INSTANCE = Mappers.getMapper(MasterCourseModelMapper.class);

    DTOMasterCourse toDto(MasterCourseModel model);
}
