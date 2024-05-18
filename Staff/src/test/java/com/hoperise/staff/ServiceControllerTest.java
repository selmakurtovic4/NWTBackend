package com.hoperise.staff;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoperise.staff.controllers.ServiceController;
import com.hoperise.staff.dtos.ServiceDTO;
import com.hoperise.staff.models.MedicalService;
import com.hoperise.staff.services.IServiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(ServiceController.class)
@AutoConfigureMockMvc
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IServiceService serviceService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getServiceById_ReturnsServiceDTO_WhenServiceExists() throws Exception {
        // Mocking service response
        ServiceDTO mockServiceDTO = new ServiceDTO(1, "Test Service", 100, "Test Department");
        when(serviceService.getServiceById(1L)).thenReturn(mockServiceDTO);

        // Performing HTTP GET request and verifying the response
        mockMvc.perform(get("/service/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Service"))
                .andExpect(jsonPath("$.price").value(100.0))
                .andExpect(jsonPath("$.departmentName").value("Test Department"));
    }

    @Test
    public void createService_ReturnsCreated() throws Exception {
        // Mocking service response
        MedicalService medicalService = new MedicalService();
        when(serviceService.createService(any(MedicalService.class))).thenReturn(medicalService);

        // Performing HTTP POST request and verifying the response
        mockMvc.perform(post("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalService)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateService_ReturnsOk() throws Exception {
        // Mocking service response
        MedicalService medicalService = new MedicalService();
        when(serviceService.updateService(any(MedicalService.class))).thenReturn(medicalService);

        // Performing HTTP PUT request and verifying the response
        mockMvc.perform(put("/service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalService)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteService_ReturnsOk() throws Exception {
        // Performing HTTP DELETE request and verifying the response
        mockMvc.perform(delete("/service/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getServicesByDepartmentId_ReturnsListOfServices() throws Exception {
        // Mocking service response
        List<MedicalService> medicalServices = new ArrayList<>();
        when(serviceService.getServicesByDepartmentId(1L)).thenReturn(medicalServices);

        // Performing HTTP GET request and verifying the response
        mockMvc.perform(get("/service/department/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
