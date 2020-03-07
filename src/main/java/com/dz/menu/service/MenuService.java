package com.dz.menu.service;

import com.dz.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public Mono<List<String>> titles() {
        return this.menuRepository.findAll().map(menu -> menu.getTitle()).collectList();
    }

}
