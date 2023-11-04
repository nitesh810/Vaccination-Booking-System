package com.example.vaccinationbookingsystem.controller;

import com.example.vaccinationbookingsystem.DTO.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.GetPersonResponseDto;
import com.example.vaccinationbookingsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try{
            AddPersonResponseDto personResponse = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(personResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity("Email already exists",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_person")
    public ResponseEntity getPerson(@RequestParam("email") String emailId) {

        try {
            GetPersonResponseDto getPersonResponseDto = personService.getPerson(emailId);
            return new ResponseEntity<>(getPersonResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update_email")
    public ResponseEntity updateEmail(@RequestParam("oldEmail") String oldEmail,
                                      @RequestParam("newEmail") String newEmail){

        try{
          String response = personService.updateEmail(oldEmail,newEmail);
          return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
