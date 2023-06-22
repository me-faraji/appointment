package com.blubank.doctorappointment.common;

import com.blubank.doctorappointment.controller.dto.DTODetailCourse;
import com.blubank.doctorappointment.controller.dto.DTOMasterCourse;
import com.blubank.doctorappointment.controller.dto.DTOPatient;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.model.DetailCourseModel;
import com.blubank.doctorappointment.model.MasterCourseModel;
import com.blubank.doctorappointment.model.PatientModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    DTOMasterCourse toDto(MasterCourseModel model);
    List<DTODetailCourse> toDto(List<DetailCourseModel> model);
    DTOPatient toDtoPatient(PatientModel model);
    PatientModel toModel(DTOReserve dtoReserve);

}
