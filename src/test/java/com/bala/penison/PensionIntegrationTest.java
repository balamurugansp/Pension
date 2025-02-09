package com.bala.penison;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) 
 
public class PensionIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetPension_success() {
        ResponseEntity<Double> response = restTemplate.getForEntity("/pension/Alice", Double.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5000.0, response.getBody());
    }

    @Test
    public void testGetPension_notFound() {
      ResponseEntity<Double> response = restTemplate.getForEntity("/pension/David", Double.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

     
}
