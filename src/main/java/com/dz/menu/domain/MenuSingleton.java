package com.dz.menu.domain;

import com.comedor.menu.*;
import com.comedor.util.MenuUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum MenuSingleton {
    INSTANCE
    ;

     private List<Menu> menus;

    MenuSingleton() {
        this.menus = new ArrayList<>();
    }

    public void load(final String menuFilePath) throws Exception {
        try (final Workbook workbook = WorkbookFactory.create(new File(menuFilePath))) {
            final int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                final Sheet sheet = workbook.getSheetAt(3);

                final Optional<Menu> menu = MenuUtils.extractMenuFromSheet(sheet);
                menu.ifPresent(mn -> menus.add(mn));
            }
        }
    }

    // Return a copy of it:
    public List<Menu> menus() {
        return new ArrayList<>(this.menus);
    }

}
