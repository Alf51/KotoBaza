package com.example.kotobaza.controllers;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    @GetMapping("/all-cities")
    public String getAllCity(Model model) {
        List<City> cityList = cityService.getAllCity();
        model.addAttribute("cityList", cityList);
        return "cities/cities-all";
    }
}
