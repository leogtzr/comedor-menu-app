package com.dz.menu.controllers;

import com.comedor.menu.Menu;
import com.dz.menu.domain.MenuSingleton;
import com.dz.menu.repository.MenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@Slf4j
public class SomeController {

    private MenuRepository menuRepository;

    public SomeController(final MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping("/hola")
    public void foo() {
        final MenuSingleton instance = MenuSingleton.INSTANCE;
        final List<Menu> menus = instance.menus();
        menus.forEach(System.out::println);
    }

    @GetMapping("/save")
    @ResponseBody
    public Mono<List<Menu>> saveShit() {
        final MenuSingleton instance = MenuSingleton.INSTANCE;
        final List<Menu> menus = instance.menus();
        return Flux.fromIterable(menus).flatMap(menu -> this.menuRepository.save(menu)).collectList();
    }

    @GetMapping("/menunames")
    @ResponseBody
    public Mono<List<String>> menuNames() {
        return this.menuRepository.findAll().map(menu -> menu.getTitle()).collectList();
    }
    
    @GetMapping("/menu/{title}")
    @ResponseBody
    public Mono<Menu> getMenuByTitle(@PathVariable String title) {
        return this.menuRepository.findByTitle(title);
    }

}
