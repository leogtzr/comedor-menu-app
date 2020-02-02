package com.dz.menu.domain;

import com.comedor.menu.*;
import com.comedor.util.MenuUtils;
import java.util.ArrayList;
import java.util.List;


public enum MenuSingleton {
    INSTANCE
    ;

     private List<Menu> menus;

    MenuSingleton() {
        this.menus = new ArrayList<>();
    }

    public void load(final String menuFilePath) throws Exception {
        final List<Menu> menusFromFile = MenuUtils.extractMenuFromFile(menuFilePath);
        if (!menusFromFile.isEmpty()) {
            this.menus.clear();
            this.menus.addAll(menusFromFile);
        }
    }

    // Return a copy of it:
    public List<Menu> menus() {
        return new ArrayList<>(this.menus);
    }

}
