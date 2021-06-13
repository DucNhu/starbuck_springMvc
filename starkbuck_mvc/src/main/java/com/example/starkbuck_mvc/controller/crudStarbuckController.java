package com.example.starkbuck_mvc.controller;

import com.example.starkbuck_mvc.models.starbuck;
import com.example.starkbuck_mvc.repository.StarbuckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/admin")
public class crudStarbuckController {
    @Autowired
    private StarbuckRepository starbuckRepository;

    @GetMapping("/home-page")
    public String getAll(Model model) {
        model.addAttribute("listData", starbuckRepository.findAll());
        return "Admin/admin-home";
    }

    @GetMapping("/add-product")
    public String AddProduct(
            Model model
    ) {
        starbuck NewStaruck = new starbuck();
        model.addAttribute("the_Starbuck", NewStaruck);
        return "Admin/asd";
    }

    @GetMapping("/edit-product/{id}")
    private String loadProduct(@PathVariable(value = "id") Integer id, Model model)
            throws Exception {

        starbuck theStarbuck = starbuckRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Ko tim thay id : " + id));

        model.addAttribute("the_Starbuck", theStarbuck);

        return "Admin/edit-product";
    }

    @PostMapping("/update-product/{id}")
    public String PutProduct(
            @PathVariable(value = "id") Integer id, @ModelAttribute("the_Starbuck") starbuck starbuckDetail,
            Model model
    ) {
        starbuck theStarbuck = starbuckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ko tim thay id : " + id));

        theStarbuck.setName(starbuckDetail.getName());
        theStarbuck.setAvatar(starbuckDetail.getAvatar());
        theStarbuck.setCategory(starbuckDetail.getCategory());
        theStarbuck.setPrice(starbuckDetail.getPrice());
        theStarbuck.setSale(starbuckDetail.getSale());

        starbuckRepository.save(theStarbuck);
        model.addAttribute("listData", starbuckRepository.findAll());
        return "redirect:/admin/home-page";
    }

    @GetMapping("/delete-product/{id}")
    public String DeleteProduct(
            @PathVariable(value = "id") Integer id,
            Model model
    ) {

        starbuckRepository.deleteById(id);
        model.addAttribute("listData", starbuckRepository.findAll());
        return "redirect:/admin/home-page";
    }

    @PostMapping("/new-product")
    public String NewProduct(@ModelAttribute("the_Starbuck") starbuck starbuckDetail, Model model
    ) {
        starbuck newstarbuck = new starbuck(starbuckDetail.getName(),starbuckDetail.getCategory(), starbuckDetail.getAvatar(),
                starbuckDetail.getPrice(), starbuckDetail.getSale());

        starbuckRepository.save(newstarbuck);
        model.addAttribute("listData", starbuckRepository.findAll());
        return "redirect:/admin/home-page";
    }
}
