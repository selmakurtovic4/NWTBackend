package com.hoperise.staff.services;

import com.hoperise.staff.models.Department;
import com.hoperise.staff.models.Doctor;
import com.hoperise.staff.models.User;
import com.hoperise.staff.repositories.MedicalStaffRepository;
import com.hoperise.staff.repositories.StaffMemberRepository;
import com.hoperise.staff.repositories.UserRepository;
import com.hoperise.staff.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService implements IStaffService {
    private final StaffMemberRepository nonMedicalStaffRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final UserRepository userRepository;
   // private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public StaffService(StaffMemberRepository nonMedicalStaffRepository, MedicalStaffRepository medicalStaffRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.nonMedicalStaffRepository = nonMedicalStaffRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.userRepository = userRepository;
     //   this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    public User getUserById(Long id){
        return userRepository.getById(id);
    }

    public Doctor getDoctorById(Long id){
        return medicalStaffRepository.getById(id);
    }

    public List<Doctor> getDoctorByLastName(String lastName){
        return medicalStaffRepository.findByLastName(lastName);
    }



    public void createDoctor(User user, int departmentId, String specialization, String bio ){
        var newDepartment=new Department(departmentId, "");
        var newDoctor=new Doctor(user, newDepartment, specialization, bio);
        medicalStaffRepository.save(newDoctor);
    }

}
