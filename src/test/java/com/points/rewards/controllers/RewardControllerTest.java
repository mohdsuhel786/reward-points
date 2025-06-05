package com.points.rewards.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    //test for valid end point
    @Test
    public void testGetRewardsEndPoint(){
        String url = "http://localhost:"+port+"/rewards";
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("customerId"));
    }
    //test for invalid end point
    @Test
    public void testInvalidEndPoint(){
        String url = "http://localhost:"+port+"/invalid";
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

}
