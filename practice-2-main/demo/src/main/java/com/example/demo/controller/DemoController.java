package com.example.demo.controller;

import com.example.demo.service.ContactService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.UserService;

@Controller
@RequestMapping("/")
public class DemoController {

	private static final Logger logger = LogManager.getLogger(DemoController.class);

	@Autowired
	private ContactService contactService;

	@GetMapping("/addContact")
	public String enterName() {
		return "addContact";
	}

//	@ExceptionHandler(IllegalArgumentException.class)
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
//		ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
//		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

//	static class Person {
//		private String firstName;
//		private String lastName;
//
//		public Person(String firstName, String lastName) {
//			this.firstName = firstName;
//			this.lastName = lastName;
//		}
//
//		public String getFirstName() {
//			return firstName;
//		}
//
//		public void setFirstName(String firstName) {
//			this.firstName = firstName;
//		}
//
//		public String getLastName() {
//			return lastName;
//		}
//
//		public void setLastName(String lastName) {
//			this.lastName = lastName;
//		}
//	}

//	static class ErrorResponse {
//		private String errorMessage;
//
//		public ErrorResponse(String errorMessage) {
//			this.errorMessage = errorMessage;
//		}
//
//		public String getErrorMessage() {
//			return errorMessage;
//		}
//
//		public void setErrorMessage(String errorMessage) {
//			this.errorMessage = errorMessage;
//		}
//	}
}
