package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("persons", personRepository.findAll());
        model.addAttribute("pets", petRepository.findAll());
        return "index";
    }
    @GetMapping("/addPerson")
    public String PersonForm(Model model){
        model.addAttribute("person", new Person());
        return "personform";

    }
    @PostMapping("/processPerson")
    public String processpersonForm(@Valid Person person,
                                    BindingResult result){
        if (result.hasErrors()){
            return "personform";
        }
        personRepository.save(person);
        return "redirect:/personlist";
    }
    @RequestMapping("/detailPerson/{id}")
    public String showPerson(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("person", personRepository.findById(id).get());
        return "showperson";
    }
    @RequestMapping("/updatePerson/{id}")
    public String updatePerson(@PathVariable("id") long id,
                               Model model){
        model.addAttribute("person", personRepository.findById(id).get());
        model.addAttribute("pets", petRepository.findAll());
        return "personform";
    }
    @RequestMapping("/deletePerson/{id}")
    public String delPerson(@PathVariable("id") long id){
        personRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/personlist")
    public String listPerson(Model model){
        model.addAttribute("person", personRepository.findAll());
        return "personlist";
    }
    @GetMapping("/addPet")
    public String petForm(Model model){
        model.addAttribute("pet", new Pet());
        model.addAttribute("persons", personRepository.findAll());
        return "petform";
    }
    @PostMapping("/processPet")
    public String processPetForm(@Valid Pet pet,
                                 BindingResult result){
        if (result.hasErrors()){
            return "petform";
    }
        petRepository.save(pet);
        return "redirect:/petlist";
    }
    @RequestMapping("/detailPet/{id}")
    public String showPet(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("pet", petRepository.findById(id).get());
        return "showpet";
    }
    @RequestMapping("/updatePet/{id}")
    public String updatePet(@PathVariable("id") long id, Model model){
        model.addAttribute("pet", petRepository.findById(id).get());
        model.addAttribute("persons", personRepository.findAll());
        return "petform";
    }
    @RequestMapping("/deletePet/{id}")
    public String delPet(@PathVariable("id") long id){
        petRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/petlist")
    public String listPet(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "petlist";
    }

}
