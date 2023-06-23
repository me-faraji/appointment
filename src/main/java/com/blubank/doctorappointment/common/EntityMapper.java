package com.blubank.doctorappointment.common;

import com.blubank.doctorappointment.controller.dto.DTODetailAppointment;
import com.blubank.doctorappointment.controller.dto.DTOMasterAppointment;
import com.blubank.doctorappointment.controller.dto.DTOPatient;
import com.blubank.doctorappointment.controller.dto.DTOReserve;
import com.blubank.doctorappointment.model.DetailAppointmentModel;
import com.blubank.doctorappointment.model.MasterAppointmentModel;
import com.blubank.doctorappointment.model.PatientModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    DTOMasterAppointment toDto(MasterAppointmentModel model);

    List<DTODetailAppointment> toDto(List<DetailAppointmentModel> model);

    DTOPatient toDtoPatient(PatientModel model);

    PatientModel toModel(DTOReserve dtoReserve);

}
