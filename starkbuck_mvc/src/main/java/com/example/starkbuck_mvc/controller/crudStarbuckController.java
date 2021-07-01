package com.example.starkbuck_mvc.controller;

import com.example.starkbuck_mvc.models.starbuck;
import com.example.starkbuck_mvc.repository.StarbuckRepository;
import com.example.starkbuck_mvc.service.StarbuckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/admin")
public class crudStarbuckController {
    private StarbuckService starbuckService;

    public crudStarbuckController(StarbuckService theStarbuckService) {
        starbuckService = theStarbuckService;
    }

    @GetMapping("/home-page")
    public String getAll(Model model) {
        List<starbuck> starbuckList = starbuckService.findAll();
        model.addAttribute("listData",starbuckList);
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

        starbuck theStarbuck = starbuckService.findById(id);

        model.addAttribute("the_Starbuck", theStarbuck);

        return "Admin/edit-product";
    }

    @PostMapping("/update-product/{id}")
    public String PutProduct(
            @PathVariable(value = "id") Integer id, @ModelAttribute("the_Starbuck") starbuck starbuckDetail,
            Model model
    ) {
        starbuck theStarbuck = starbuckService.findById(id);
//        starbuck theStarbuck = starbuckService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ko tim thay id : " + id));
        theStarbuck.setName(starbuckDetail.getName());
        theStarbuck.setAvatar(starbuckDetail.getAvatar());
        theStarbuck.setCategory(starbuckDetail.getCategory());
        theStarbuck.setPrice(starbuckDetail.getPrice());
        theStarbuck.setSale(starbuckDetail.getSale());

        starbuckService.save(theStarbuck);
        model.addAttribute("listData", starbuckService.findAll());
        return "redirect:/admin/home-page";
    }

    @GetMapping("/delete-product/{id}")
    public String DeleteProduct(
            @PathVariable(value = "id") Integer id,
            Model model
    ) {

        starbuckService.deleteById(id);
        model.addAttribute("listData", starbuckService.findAll());
        return "redirect:/admin/home-page";
    }

    @PostMapping("/new-product")
    public String NewProduct(@ModelAttribute("the_Starbuck") starbuck starbuckDetail, Model model
    ) {
        starbuck newstarbuck = new starbuck(starbuckDetail.getName(),starbuckDetail.getCategory(), starbuckDetail.getAvatar(),
                starbuckDetail.getPrice(), starbuckDetail.getSale());

        starbuckService.save(newstarbuck);
        model.addAttribute("listData", starbuckService.findAll());
        return "redirect:/admin/home-page";
    }
}
