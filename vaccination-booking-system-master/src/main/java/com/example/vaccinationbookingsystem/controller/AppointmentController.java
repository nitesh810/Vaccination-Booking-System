package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.DTO.RequestDto.BookAppointmentRequestDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.BookAppointmentResponseDto;
import com.example.vaccinationbookingsystem.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(
            @RequestBody BookAppointmentRequestDto bookAppointmentRequestDto){

        try{
           BookAppointmentResponseDto bookAppointmentResponseDto = appointmentService.bookAppointment(bookAppointmentRequestDto);
           return new ResponseEntity(bookAppointmentResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity cancelAppointment(@RequestParam("id") int id) {

        try {
            String done = appointmentService.cancelAppointment(id);
            return new ResponseEntity<>(done, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
