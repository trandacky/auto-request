package com.dacky.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.dacky.entity.Domain;
import com.dacky.service.DomainService;

@Controller
@RequestMapping("/")
public class DomainController {
	private final Logger log = LoggerFactory.getLogger(DomainController.class);

	@Autowired
	private DomainService domainService;
	private RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/request")
	public void request() {
		while (true) {
			try {
				Thread.sleep(1500000);
				List<Domain> domains = domainService.getAllDomain();
				for (int i = 0; i < 3; i++) {
					for (Domain domain : domains) {
						try {
							restTemplate.getForObject(domain.getDomainUrl(), String.class);
							log.debug("------- request: " + domain.getDomainUrl() + " -------------");
						} catch (Exception e) {
						}

					}
				}
			} catch (InterruptedException e) {
			}
		}
	}

	@PostMapping
	public String save(HttpServletRequest request, Model model) {

		String domain = request.getParameter("domain");
		log.debug("save domain: " + domain);
		domainService.save(domain);
		List<Domain> domains = domainService.getAllDomain();
		model.addAttribute("domains", domains);
		String back = request.getHeader("Referer");
		return "redirect:" + back;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") Long id, Model model, HttpServletRequest request) {
		log.debug("delete domain: " + id);
		domainService.delete(id);
		String back = request.getHeader("Referer");
		return "redirect:" + back;
	}

	@GetMapping
	public String index(Model model) {
		log.debug("get domain \n");
		List<Domain> domains = domainService.getAllDomain();
		model.addAttribute("domains", domains);
		return "index";
	}
}
