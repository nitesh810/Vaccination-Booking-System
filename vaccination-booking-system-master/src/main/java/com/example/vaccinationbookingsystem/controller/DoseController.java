package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.Model.Dose;
import com.example.vaccinationbookingsystem.DTO.RequestDto.DoseRequestDto;
import com.example.vaccinationbookingsystem.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("/get_dose_1")
    public ResponseEntity getDose1(@RequestBody DoseRequestDto doseRequestDto){

        try{
            String doseTake = doseService.getDose1(doseRequestDto);
            return new ResponseEntity(doseTake,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/get_dose_2")
    public ResponseEntity getDose2(@RequestBody DoseRequestDto doseRequestDto){

        try{
            String doseTake = doseService.getDose2(doseRequestDto);
            return new ResponseEntity(doseTake,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
