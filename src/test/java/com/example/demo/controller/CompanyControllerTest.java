package com.example.demo.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.models.Company;
import com.example.demo.service.CompanyService;

@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CompanyService compserv;
	
    private Company company;

	@Test
	void getAllCompanies() throws Exception {
		List<Object> complist =  Arrays.asList(
								new Company(1L,"Savera Press Comps Pvt. Ltd")
					);
		
		when(compserv.getAllCompanies()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/company/"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void getCompanyByIdTest()throws Exception{
		when(compserv.getCompanyById(1L)).thenReturn(company);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/company/{id}" ,1L))
				.andExpect(MockMvcResultMatchers.status().isOk());
			
		;
	}

}
