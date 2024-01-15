package com.example.kotobaza.controllers;

import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.services.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/cotobase", "/"})
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;

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
}
