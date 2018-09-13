package com.example.smsApp;

import com.example.smsApp.models.SMS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsAppApplicationTests {

	private SMS sms;
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		if (this.mockMvc == null) {
			this.mockMvc = webAppContextSetup(webApplicationContext).build();
		}

		sms = new SMS("46843", "8W0Q6O7E3W", "+919545901024", "Hi Manish someone just used your SMS app !");

	}

	@Test
	public void testSendSms() throws Exception {
		MvcResult result = mockMvc.perform(
				put("/api/sendSMS")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(sms))
		).andReturn();

		Assert.assertEquals(200, result.getResponse().getStatus());
	}

}
