package com.example.kotobaza.controllers;

import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.services.CatService;
import com.example.kotobaza.utils.validators.CatValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/cotobase", "/"})
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;
    private final CatValidator catValidator;

    @GetMapping
    public String getMainPage() {
        return "main";
    }

    @GetMapping("/cats-all")
    public String getAllCatsPage(Model model) {
        List<SuperCat> catList = catService.getAllSuperCat();
        model.addAttribute("catList", catList);
        return "cats/cats-all";
    }

    @GetMapping("/{id}")
    public String getCatPage(@PathVariable("id") Long id, Model model) {
        SuperCat superCat = catService.getSuperCat(id);
        model.addAttribute("cat", superCat);
        return "cats/cat";
    }

    @GetMapping("/new")
    public String getCreateCatPage(@ModelAttribute("cat") SuperCat cat) {
        return "cats/new";
    }

    @PostMapping("/new")
    public String saveCat(@ModelAttribute("cat") SuperCat superCat, BindingResult bindingResult) {

        catValidator.validate(superCat, bindingResult);
        if (bindingResult.hasErrors()) {
            return "cats/new";
        }
        catService.save(superCat);
        return "redirect:/cotobase/cats-all";
    }
}
