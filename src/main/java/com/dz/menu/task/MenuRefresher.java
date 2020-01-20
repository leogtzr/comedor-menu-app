package com.dz.menu.task;

import com.comedor.menu.Menu;
import com.dz.menu.domain.MenuSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class MenuRefresher {

    @Autowired
    private String menuFilePath;

    // TODO: adjust the schedule ...
    @Scheduled(fixedRate = 30_000)
    public void refreshMenus() throws Exception {
        final MenuSingleton menuSingleton = MenuSingleton.INSTANCE;
        final List<Menu> menus = menuSingleton.menus();
        if (menus.isEmpty()) {
            log.debug(String.format("Loading menus from: '%s'", menuFilePath));
            menuSingleton.load(menuFilePath);
        }

    }

}
