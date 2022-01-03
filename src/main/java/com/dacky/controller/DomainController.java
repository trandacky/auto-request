package com.dacky.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DomainController {
//	private final Logger log = LoggerFactory.getLogger(DomainController.class);

    @Autowired
    private DomainService domainService;
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private Attacker attacker;

    @GetMapping("/request")
    public void request() throws Exception {
        List<Domain> domains = domainService.getAllDomain();
        for (Domain domain : domains) {
            if (domain.getDomainUrl().equalsIgnoreCase("https://giaidau.vcsb.vn/")) {
                try {
                    attacker.ddos(domain.getDomainUrl());
                } catch (Exception exception) {
                    log.info("======from controller======");
                    log.info(exception.toString());
                }
            }
        }
        while (true) {
            try {
                for (int i = 0; i < 3; i++) {
                    for (Domain domain : domains) {
                        try {
                            restTemplate.getForObject(domain.getDomainUrl(), String.class);
                            log.info("------- request: " + domain.getDomainUrl() + " -------------");
                            System.out.println("sout:------- request: " + domain.getDomainUrl() + " -------------");
                        } catch (Exception e) {
                            log.info(e.toString());
                        }
                    }
                }
				Thread.sleep(1500000);
            } catch (Exception e) {
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
