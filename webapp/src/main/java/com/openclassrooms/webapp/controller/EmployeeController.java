package com.openclassrooms.webapp.controller;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    @GetMapping("/")
    public String home(Model model) {
        Iterable<Employee> employees = service.getEmployees();
        model.addAttribute("employees", employees);
        return "home";
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
        service.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping ("/addEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        service.addEmployee(employee);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute Employee employee) {
        service.updateEmployee(employee);
        return "redirect:/";
    }

    //Envoyer les données de l'employé à la page de mise à jour
    @GetMapping("/updateForm/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        // Étape 2 : Récupérez l'employé
        Employee employee = service.getEmployee(id);

        // Étape 3 : Passez les informations au formulaire
        model.addAttribute("employee", employee);

        return "formsUpdate.html"; // Le nom de votre template Thymeleaf
    }

    //Renvoie vers le formulaire d'ajout
    @GetMapping("/formsAdd")
    public String formsAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "formsAdd.html";
    }
}
