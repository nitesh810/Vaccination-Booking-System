package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.DTO.RequestDto.DoctorRequestDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.Model.Doctor;
import com.example.vaccinationbookingsystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto) {

        try{
           DoctorResponseDto doctorResponseDto = doctorService.addDoctor(doctorRequestDto);
           return new ResponseEntity(doctorResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_by_age_greater_than")
    public List<String> getByAgeGreaterThan(@RequestParam("age") int age) {
        List<String> doctors = doctorService.getByAgeGreaterThan(age);
        return doctors;
    }

    @GetMapping("/get_doctor_by_center/{center}")
    public List<Doctor> getDoctorByCenter(@PathVariable("center") String center) {

        List<Doctor> doctors = doctorService.getDoctorByCenter(center);
        return doctors;
    }

    @GetMapping("/male_doc_above_40")
    public List<String> getAllMaleDocAboveAge40() {
        List<String> doctors = doctorService.getAllMaleDocAboveAge40();
        return doctors;
    }

    @GetMapping("/doctors_above_10_app")
    public List<String> getAllDocHaveAbove10Appointment() {
        List<String> doctors = doctorService.getAllDocHaveAbove10Appointment();
        return doctors;
    }
}
