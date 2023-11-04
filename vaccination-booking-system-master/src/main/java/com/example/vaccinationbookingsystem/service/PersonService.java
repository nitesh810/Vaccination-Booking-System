package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Model.Person;
import com.example.vaccinationbookingsystem.DTO.RequestDto.AddPersonRequestDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.AddPersonResponseDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.GetPersonResponseDto;
import com.example.vaccinationbookingsystem.exception.PersonNotFoundException;
import com.example.vaccinationbookingsystem.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public AddPersonResponseDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        // Convert Request Dto -> Enttity
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmailId(addPersonRequestDto.getEmailId());
        person.setGender(addPersonRequestDto.getGender());
//        person.setDose1Taken(false);
//        person.setDose2Taken(false);
//        person.setCertificate(null);

        Person savedPerson = personRepository.save(person);

        // saved entity -> response dto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();
        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Congrats! You have been registered");
        return addPersonResponseDto;
    }

    public GetPersonResponseDto getPerson(String emailId) {

        Person person = personRepository.findByEmailId(emailId);
        if(person == null){
            throw new PersonNotFoundException("Sorry email doesn't exist");
        }

        GetPersonResponseDto getPersonResponseDto = new GetPersonResponseDto();
        getPersonResponseDto.setName(person.getName());
        getPersonResponseDto.setAge(person.getAge());
        getPersonResponseDto.setEmailId(person.getEmailId());
        getPersonResponseDto.setGender(person.getGender());

        return getPersonResponseDto;
    }

    public String updateEmail(String oldEmail, String newEmail) {

        Person person = personRepository.findByEmailId(oldEmail);
        if(person == null){
            throw new PersonNotFoundException("Sorry email doesn't exist");
        }

        person.setEmailId(newEmail);
        personRepository.save(person);
        return "Congrats!! Your email has been updated successfully";
    }
}
