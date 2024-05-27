package com.hoperise.staff.services;

import com.hoperise.staff.dtos.CreateUserRequestDTO;
import com.hoperise.staff.models.User;
import com.hoperise.staff.repositories.MedicalStaffRepository;
import com.hoperise.staff.repositories.NonMedicalStaffRepository;
import com.hoperise.staff.repositories.UserRepository;
import com.hoperise.staff.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements IStaffService {
    private final NonMedicalStaffRepository nonMedicalStaffRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public StaffService(NonMedicalStaffRepository nonMedicalStaffRepository, MedicalStaffRepository medicalStaffRepository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.nonMedicalStaffRepository = nonMedicalStaffRepository;
        this.medicalStaffRepository = medicalStaffRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public User createUser(CreateUserRequestDTO userRequestDTO) {
        // Encode the password
        String encodedPassword = passwordEncoder.encode(userRequestDTO.getPassword());

        // Create the user object
        User user = new User(userRequestDTO.getName(), userRequestDTO.getLastName(), encodedPassword);

        // Save the user to the database
        return userRepository.save(user);
    }

    public String RegisterNonMedicalStaff(CreateUserRequestDTO userRequestDTO){
     var user= createUser(userRequestDTO);

     return jwtTokenProvider.generateToken(userRequestDTO.getUsername(),"ADMIN");

    }

}
