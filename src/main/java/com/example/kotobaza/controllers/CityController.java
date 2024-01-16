package com.example.kotobaza.controllers;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.services.CityService;
import com.example.kotobaza.utils.validators.CityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final CityValidator cityValidator;

    @GetMapping("/all-cities")
    public String getAllCity(Model model) {
        List<City> cityList = cityService.getAllCity();
        model.addAttribute("cityList", cityList);
        return "cities/cities-all";
    }

    @GetMapping("/{id}")
    public String getCityPage(@PathVariable("id") Long id, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        return "cities/city";
    }

    @GetMapping("/new")
    public String getCityCreatePage(@ModelAttribute("city") City city) {
        return "cities/new";
    }

    @GetMapping("/edit/{id}")
    public String getEditCityPage(@PathVariable("id") Long id, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        return "cities/edit";
    }

    @PostMapping("/new")
    public String saveCity(@ModelAttribute("city") City city, BindingResult bindingResult) {
        cityValidator.validate(city, bindingResult);
        if (bindingResult.hasErrors()) {
            return "cities/new";
        }
        cityService.save(city);
        return "redirect:/cities/all-cities";
    }

    @PatchMapping("/edit/{id}")
    public String editCity(@ModelAttribute("city") City city, BindingResult bindingResult) {
        cityValidator.validate(city, bindingResult);
        if (bindingResult.hasErrors()) {
            return "cities/edit";
        }
        cityService.save(city);
        return "redirect:/cities/all-cities";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        System.out.println();
        cityService.deleteById(id);
        return "redirect:/cities/all-cities";
    }
}
