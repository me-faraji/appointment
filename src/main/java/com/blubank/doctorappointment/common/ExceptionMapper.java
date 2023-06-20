package com.blubank.doctorappointment.common;

import com.blubank.doctorappointment.controller.excp.ExcpController;
import com.blubank.doctorappointment.repository.excp.ExcpRepository;
import com.blubank.doctorappointment.service.excp.ExcpService;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.io.Serializable;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {
    Logger LOG = LoggerFactory.getLogger(ExceptionMapper.class);

    @ExceptionHandler(ExcpController.class)
    public ResponseEntity<Object> mapper(ExcpController err, WebRequest request) {
        LOG.error("ExcpController error occurred: ", err);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTOResponseException.builder()
                .errMessage(err.getMessage())
                .errType(err.getErrType())
                .errCode(err.getErrCode()));
    }
    @ExceptionHandler(ExcpService.class)
    public ResponseEntity<Object> mapper(ExcpService err, WebRequest request) {
        LOG.error("ExcpService error occurred: ", err);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTOResponseException.builder()
                .errMessage(err.getMessage())
                .errType(err.getErrType())
                .errCode(err.getErrCode()));
    }
    @ExceptionHandler(ExcpRepository.class)
    public ResponseEntity<Object> mapper(ExcpRepository err, WebRequest request) {
        LOG.error("ExcpRepository error occurred: ", err);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTOResponseException.builder()
                .errMessage(err.getMessage())
                .errType(err.getErrType())
                .errCode(err.getErrCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllUncaughtException(Exception err, WebRequest request) {
        LOG.error("Unknown error occurred: ", err);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DTOResponseException.builder()
                .errMessage(err.getMessage())
                .errType(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errCode(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Getter@Setter@AllArgsConstructor@NoArgsConstructor@Builder
    static class DTOResponseException implements Serializable {
        private static final long serialVersionUID = 1L;
        private Integer errCode;
        private String errType;
        private String errMessage;
    }
}
