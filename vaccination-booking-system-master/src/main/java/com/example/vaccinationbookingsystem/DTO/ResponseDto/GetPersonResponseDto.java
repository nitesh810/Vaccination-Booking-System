package com.example.vaccinationbookingsystem.DTO.ResponseDto;

import com.example.vaccinationbookingsystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GetPersonResponseDto {

    String name;

    int age;

    String emailId;

    Gender gender;

    boolean dose1Taken;

    boolean dose2Taken;
}
