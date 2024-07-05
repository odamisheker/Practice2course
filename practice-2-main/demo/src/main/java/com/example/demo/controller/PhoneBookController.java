package com.example.demo.controller;

import com.example.demo.model.Contact;
import com.example.demo.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contact")
public class PhoneBookController {

	@Autowired
	private ContactService contactService;

	@GetMapping
	public String getAllContacts(Model model) {
		List<Contact> contacts = contactService.getAllContacts();
		model.addAttribute("contacts", contacts);
		return "phoneBook";
	}

	@GetMapping("/add")
	public String showForm() {
		return "addContact";
	}
	@PostMapping("/add")
	public String addContact(@RequestParam String name, @RequestParam String number) {
		Contact contact = new Contact();
		contact.setName(name);
		contact.setNumber(number);
		contactService.saveContact(contact);

		return "redirect:/contact";
	}
	@GetMapping("/contact/delete/{id}")
	public String deleteContact(@PathVariable("id") Long id) {
		contactService.deleteContact(id);
		return "redirect:/contact";
	}
}
