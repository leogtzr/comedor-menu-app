package com.dz.menu.controllers;

import com.dz.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class IndexController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public String index(final Model model) {
        final IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(menuService.titles().flux(), 1);

        model.addAttribute("titles", reactiveDataDrivenMode);

        return "index";
    }

}
