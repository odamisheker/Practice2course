package com.example.demo;

import com.example.demo.controller.DemoController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DemoControllerTest {

    @InjectMocks
    private DemoController demoController;

    @Test
    public void testHello() {
        ResponseEntity<String> response = demoController.hello();
        assertEquals("Hello, World!", response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

//    @Test
//    public void testMultiply() {
//        ResponseEntity<String> response = demoController.multiply();
//        assertEquals("Multiply", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//    }

//    @Test
//    public void testEnterNumber() {
//        ResponseEntity<String> response = demoController.enterNumber();
//        assertEquals("Enter number", response.getBody());
//        assertEquals(200, response.getStatusCodeValue());
//    }

//    @Test
//    public void testResult() {
//        ResponseEntity<DemoController.Result> response = demoController.result("2", "3");
//        assertEquals(200, response.getStatusCodeValue());
//        assertNotNull(response.getBody());
//        assertEquals(6, response.getBody().getResult());
//    }

    @Test
    public void testResultInvalidInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            demoController.result("invalid", "3");
        });

        assertEquals("Invalid input", exception.getMessage());
    }

    @Test
    public void testHandleIllegalArgumentException() {
        IllegalArgumentException e = new IllegalArgumentException("Invalid input");
        ResponseEntity<DemoController.ErrorResponse> response = demoController.handleIllegalArgumentException(e);
        assertEquals(500, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("Invalid input", response.getBody().getErrorMessage());
    }
}
