package com.dz.menu.controllers;

import com.comedor.menu.Menu;
import com.dz.menu.domain.MenuSingleton;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SomeController {

    @GetMapping("/hola")
    public void foo() {
        final MenuSingleton instance = MenuSingleton.INSTANCE;
        final List<Menu> menus = instance.menus();
        menus.forEach(System.out::println);
    }

}
