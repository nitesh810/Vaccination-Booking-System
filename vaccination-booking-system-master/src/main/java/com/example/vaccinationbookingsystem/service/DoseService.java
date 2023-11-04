package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Model.Dose;
import com.example.vaccinationbookingsystem.Model.Person;
import com.example.vaccinationbookingsystem.DTO.RequestDto.DoseRequestDto;
import com.example.vaccinationbookingsystem.exception.Dose1NotTakenException;
import com.example.vaccinationbookingsystem.exception.DoseAlreadyTakenException;
import com.example.vaccinationbookingsystem.exception.PersonNotFoundException;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {
    @Autowired
    PersonRepository personRepository;

    public String getDose1(DoseRequestDto doseRequestDto) {

        Person person = personRepository.findByEmailId(doseRequestDto.getPersonEmail());

        // check if person exists or not
        if(person == null){
            throw new PersonNotFoundException("Invalid PersonId");
        }

        // check if dose 1 is already taken
        if(person.isDose1Taken()){
            throw new DoseAlreadyTakenException("Dose 1 already taken");
        }

        // Create a Dose from RequestDto
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(doseRequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose1Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        return "Dose 1 taken successfull";
    }
    public String getDose2(DoseRequestDto doseRequestDto) {

        Person person = personRepository.findByEmailId(doseRequestDto.getPersonEmail());

        // check if person exists or not
        if(person == null){
            throw new PersonNotFoundException("Invalid PersonId");
        }

        // check if dose 1 is taken
        if(!person.isDose1Taken()){
            throw new Dose1NotTakenException("Dose 1 not taken");
        }

        // Create a Dose from RequestDto
        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(doseRequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose2Taken(true);
        person.getDosesTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        return "Dose 2 taken successfull";
    }
}
