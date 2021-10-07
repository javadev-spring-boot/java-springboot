package com.example.demo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class DemoController {

	@Value("${msg.title}")
	private String title;

	@Autowired
	private ContactService contactService;

	@GetMapping(value = {"/", "/index"})
	public String index(Model model) {
		model.addAttribute("title", title);
		return "index";
	}

	@GetMapping(value = {"/contacts/add"})
	public String showAddContact(Model model) {
		Contact contact = new Contact();
		model.addAttribute("add", true);
		model.addAttribute("contact", contact);

		return "contact-edit";
	}

	@PostMapping(value = "/contacts/add")
	public String addContact(Model model,
							 @ModelAttribute("contact") Contact contact) {
		try {
			contact.setId(UUID.randomUUID());
			Contact newContact = contactService.save(contact);
			return "redirect:/contacts/" + newContact.getId();
		} catch (Exception ex) {
			// log exception first,
			// then show error
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);

			//model.addAttribute("contact", contact);
			model.addAttribute("add", true);
			return "contact-edit";
		}
	}

	@GetMapping(value = {"/contacts/{contactId}/edit"})
	public String showEditContact(Model model, @PathVariable UUID contactId) {
		Contact contact = null;
		try {
			contact = contactService.findById(contactId);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", "Contact not found");
		}
		model.addAttribute("add", false);
		model.addAttribute("contact", contact);
		return "contact-edit";
	}

	@GetMapping(value = "/contacts")
	public String getContacts(Model model,
							  @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
		List<Contact> contacts = contactService.findAll();

		long count = contactService.count();
		boolean hasPrev = pageNumber > 1;
		model.addAttribute("contacts", contacts);
		model.addAttribute("hasPrev", hasPrev);
		model.addAttribute("prev", pageNumber - 1);
		model.addAttribute("hasNext", true);
		model.addAttribute("next", pageNumber + 1);
		return "contact-list";
	}

	@PostMapping(value = {"/contacts/{contactId}/edit"})
	public String updateContact(Model model,
								@PathVariable UUID contactId,
								@ModelAttribute("contact") Contact contact) {
		try {
			contact.setId(contactId);
			contactService.update(contact);
			return "redirect:/contacts/" + String.valueOf(contact.getId());
		} catch (Exception ex) {
			// log exception first,
			// then show error
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);

			model.addAttribute("add", false);
			return "contact-edit";
		}
	}

	@GetMapping(value = {"/contacts/{contactId}/delete"})
	public String showDeleteContactById(
			Model model, @PathVariable UUID contactId) {
		Contact contact = null;
		try {
			contact = contactService.findById(contactId);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", "Contact not found");
		}
		model.addAttribute("allowDelete", true);
		model.addAttribute("contact", contact);
		return "contact";
	}

	@PostMapping(value = {"/contacts/{contactId}/delete"})
	public String deleteContactById(
			Model model, @PathVariable UUID contactId) {
		try {
			contactService.deleteById(contactId);
			return "redirect:/contacts";
		} catch (Exception ex) {
			String errorMessage = ex.getMessage();
			model.addAttribute("errorMessage", errorMessage);
			return "contact";
		}
	}

	@GetMapping(value = "/contacts/{contactId}")
	public String getContactById(Model model, @PathVariable UUID contactId) {
		Contact contact = null;
		try {
			contact = contactService.findById(contactId);
		} catch (Exception ex) {
			model.addAttribute("errorMessage", "Contact not found");
		}
		model.addAttribute("contact", contact);
		return "contact";
	}
}
