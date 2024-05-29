package com.hoperise.staff.services;

import com.hoperise.staff.dtos.CreateUserRequestDTO;
import com.hoperise.staff.models.Doctor;
import com.hoperise.staff.models.User;
import com.hoperise.staff.repositories.MedicalStaffRepository;
import com.hoperise.staff.repositories.StaffMemberRepository;
import com.hoperise.staff.repositories.UserRepository;
import com.hoperise.staff.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StaffServiceTest {

    @Mock
    private StaffMemberRepository nonMedicalStaffRepository;

    @Mock
    private MedicalStaffRepository medicalStaffRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private StaffService staffService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO();
        userRequestDTO.setName("John");
        userRequestDTO.setLastName("Doe");
        userRequestDTO.setPassword("password");

        User user = new User("John", "Doe", "password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = staffService.createUser(userRequestDTO);

        assertEquals(user, createdUser);
    }

    @Test
    public void testRegisterNonMedicalStaff() {
        CreateUserRequestDTO userRequestDTO = new CreateUserRequestDTO();
        userRequestDTO.setUsername("john_doe");
        userRequestDTO.setPassword("password");

        User user = new User("John", "Doe", "password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtTokenProvider.generateToken(userRequestDTO.getUsername(), "ADMIN")).thenReturn("token");

        String token = staffService.RegisterNonMedicalStaff(userRequestDTO);

        assertEquals("token", token);
    }

    @Test
    public void testGetUserById() {
        Long id = 1L;
        User user = new User("John", "Doe", "password");
        when(userRepository.getById(id)).thenReturn(user);

        User fetchedUser = staffService.getUserById(id);

        assertEquals(user, fetchedUser);
    }

    @Test
    public void testGetDoctorById() {
        Long id = 1L;
        Doctor doctor = new Doctor();
        when(medicalStaffRepository.getById(id)).thenReturn(doctor);

        Doctor fetchedDoctor = staffService.getDoctorById(id);

        assertEquals(doctor, fetchedDoctor);
    }

    @Test
    public void testGetDoctorByLastName() {
        String lastName = "Doe";
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());
        when(medicalStaffRepository.findByLastName(lastName)).thenReturn(doctors);

        List<Doctor> fetchedDoctors = staffService.getDoctorByLastName(lastName);

        assertEquals(doctors, fetchedDoctors);
    }

    // Add more tests for other methods as needed
}
