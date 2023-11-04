package com.example.vaccinationbookingsystem.service;

import com.example.vaccinationbookingsystem.Enum.Gender;
import com.example.vaccinationbookingsystem.Model.Doctor;
import com.example.vaccinationbookingsystem.Model.VaccinationCenter;
import com.example.vaccinationbookingsystem.DTO.RequestDto.DoctorRequestDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.CenterResponseDto;
import com.example.vaccinationbookingsystem.DTO.ResponseDto.DoctorResponseDto;
import com.example.vaccinationbookingsystem.exception.CenterNotFoundException;
import com.example.vaccinationbookingsystem.repository.DoctorRepository;
import com.example.vaccinationbookingsystem.repository.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    VaccinationCenterRepository centerRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        Optional<VaccinationCenter> vaccinationCenterOptional = centerRepository.findById(doctorRequestDto.getCenterId());
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFoundException("Sorry! Wrong center Id");
        }

        VaccinationCenter center = vaccinationCenterOptional.get();

        // create doctor entity
        Doctor doctor = new Doctor();
        doctor.setName(doctorRequestDto.getName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setCenter(center);

        // add in center's doctor list
        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = centerRepository.save(center); // save both center and doctor

        // prepare response dto
        List<Doctor> doctors = savedCenter.getDoctors();
        Doctor latestSavedDoctor = doctors.get(doctors.size()-1);

        CenterResponseDto centerResponseDto = new CenterResponseDto();
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        centerResponseDto.setAddress(savedCenter.getAddress());
        centerResponseDto.setCenterName(savedCenter.getCenterName());

        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setName(latestSavedDoctor.getName());
        doctorResponseDto.setMessage("Congrats!! You have been registered!");
        doctorResponseDto.setCenterResponseDto(centerResponseDto);

        return doctorResponseDto;
    }

    public List<String> getByAgeGreaterThan(int age) {

        List<Doctor> doctors = doctorRepository.getByAgeGreaterThan(age);
        List<String> ans = new ArrayList<>();

        for(Doctor d: doctors)
            ans.add(d.getName());

        return ans;
    }

    public List<Doctor> getDoctorByCenter(String center) {
        List<Doctor> doctors = doctorRepository.findAll();
        for(int i=0; i<doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            VaccinationCenter vaccinationCenter = doctor.getCenter();
            if(!vaccinationCenter.getCenterName().equals(center)) {
                doctors.remove(i);
            }
        }
        return doctors;
    }

    public List<String> getAllMaleDocAboveAge40() {
        List<String> allDocs=new ArrayList<>();
        List<Doctor> docs=doctorRepository.findAll();
        for (Doctor doc:docs){
            if(doc.getAge()>40 && doc.getGender() == Gender.MALE){
                allDocs.add(doc.getName());
            }
        }
        return allDocs;
    }
    public List<String> getAllDocHaveAbove10Appointment() {
        List<String> allDocs=new ArrayList<>();
        List<Doctor> docs=doctorRepository.findAll();
        for (Doctor doc:docs){
            if(doc.getAppointments().size()>10){
                allDocs.add(doc.getName());
            }
        }
        return allDocs;
    }
}
