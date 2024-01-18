package com.example.kotobaza.controllers;

import com.example.kotobaza.modeles.City;
import com.example.kotobaza.modeles.SuperCat;
import com.example.kotobaza.services.CatService;
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
    private final CatService catService;
    private final CityValidator cityValidator;

    @GetMapping("/all-cities")
    public String getAllCity(Model model) {
        List<City> cityList = cityService.getAllCity();
        model.addAttribute("cityList", cityList);
        return "cities/cities-all";
    }

    @GetMapping("/{id}")
    public String getCityPage(@PathVariable("id") Long id, Model model, @ModelAttribute("superCat") SuperCat superCat) {
        City city = cityService.getCityById(id);
        List<SuperCat> freeSuperCats = catService.getFreeSuperCats();
        model.addAttribute("freeCats", freeSuperCats);
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

    @GetMapping("/defense/{id}")
    public String getCatsOnDefenseCity(@PathVariable("id") Long id, @ModelAttribute("superCat") SuperCat superCat, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        return "cats/cats-on-defence-city";
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

    @PatchMapping("/add/{cityId}")
    public String addCat(@PathVariable("cityId") Long cityId, @ModelAttribute("superCat") SuperCat superCat) {
        cityService.assignCat(superCat.getId(), cityId);
        return "redirect:/cities/all-cities";
    }

    @PatchMapping("/releaseСat/{cityId}")
    public String releaseCat(@PathVariable("cityId") Long cityId, @ModelAttribute("superCat") SuperCat superCat) {
        cityService.releaseCat(superCat.getId(), cityId);
        return "redirect:/cities/all-cities";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        cityService.deleteById(id);
        return "redirect:/cities/all-cities";
    }
}
