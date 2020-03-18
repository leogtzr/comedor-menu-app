package com.dz.menu.controllers;

import com.comedor.menu.Day;
import com.comedor.menu.DayMeal;
import com.comedor.menu.Food;
import com.comedor.menu.Menu;
import com.dz.menu.domain.MenuSingleton;
import com.dz.menu.repository.MenuRepository;
import com.dz.menu.service.MenuService;
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
public class MenuController {

    private MenuRepository menuRepository;
    private MenuService menuService;

    public MenuController(final MenuRepository menuRepository, final MenuService menuService) {
        this.menuRepository = menuRepository;
        this.menuService = menuService;
    }

    @GetMapping("/save")
    @ResponseBody
    public Mono<List<Menu>> saveShit() {
        final MenuSingleton instance = MenuSingleton.INSTANCE;
        final List<Menu> menus = instance.menus();
        return Flux.fromIterable(menus).flatMap(menu -> this.menuRepository.save(menu)).collectList();
    }

    @GetMapping("/menus/{title}")
    @ResponseBody
    public Mono<Menu> getMenuByTitle(@PathVariable String title) {
        return this.menuRepository.findByTitle(title);
    }

    @GetMapping("/menus")
    @ResponseBody
    public Mono<List<String>> titles() {
        return this.menuService.titles();
    }

    @GetMapping("/menus/{day}/{title}")
    @ResponseBody
    public Mono<DayMeal> foodByDay(@PathVariable String day, @PathVariable String title) {
        final Mono<Menu> byTitle = menuRepository.findByTitle(title);
        final Day dayName = Day.valueOf(day.toUpperCase());
        return byTitle.map(m -> m.getMenu().get(dayName));
    }

    @GetMapping("/menus/br/{day}/{title}")
    @ResponseBody
    public Mono<Food> breakfastByDay(@PathVariable String day, @PathVariable String title) {
        final Mono<Menu> byTitle = menuRepository.findByTitle(title);
        final Day dayName = Day.valueOf(day.toUpperCase());
        return byTitle.map(m -> m.getMenu().get(dayName).getBreakfast());
    }

    @GetMapping("/menus/salads/{day}/{title}")
    @ResponseBody
    public Mono<List<String>> saladsByDay(@PathVariable String day, @PathVariable String title) {
        final Mono<Menu> byTitle = menuRepository.findByTitle(title);
        final Day dayName = Day.valueOf(day.toUpperCase());
        return byTitle.map(m -> m.getMenu().get(dayName).getSalads());
    }

    @GetMapping("/menus/lunch/{day}/{title}")
    @ResponseBody
    public Mono<List<Food>> lunchByDay(@PathVariable String day, @PathVariable String title) {
        final Mono<Menu> byTitle = menuRepository.findByTitle(title);
        final Day dayName = Day.valueOf(day.toUpperCase());
        return byTitle.map(m -> m.getMenu().get(dayName).getLunchDinnerFoodAlternatives());
    }

}
