package com.example.vaccinationbookingsystem.repository;

import com.example.vaccinationbookingsystem.Model.Doctor;
import com.example.vaccinationbookingsystem.Model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter,Integer> {

}
