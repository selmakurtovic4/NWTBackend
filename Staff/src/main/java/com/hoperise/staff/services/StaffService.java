package com.hoperise.staff.services;

import com.hoperise.staff.dtos.CreateUserRequestDTO;
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

    public User createUser(CreateUserRequestDTO userRequestDTO) {
        // Encode the password
     //   String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        // Create the user object
        User user = new User(userRequestDTO.getName(), userRequestDTO.getLastName(), userRequestDTO.getPassword());

        // Save the user to the database
        return userRepository.save(user);
    }

    public String RegisterNonMedicalStaff(CreateUserRequestDTO userRequestDTO){
     var user= createUser(userRequestDTO);

     return jwtTokenProvider.generateToken(userRequestDTO.getUsername(),"ADMIN");

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
}
