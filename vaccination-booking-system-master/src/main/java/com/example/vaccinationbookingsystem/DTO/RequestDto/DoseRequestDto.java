package com.example.vaccinationbookingsystem.DTO.RequestDto;

import com.example.vaccinationbookingsystem.Enum.DoseType;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoseRequestDto {

    String personEmail;

    DoseType doseType;
}
