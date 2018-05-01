package com.pazukdev;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@SuppressWarnings("serial")
@SpringUI
public class MainUI extends UI {

    MenuBar menu = new MenuBar();
    MenuBar.MenuItem hotelItem;
    MenuBar.MenuItem categoryItem;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);

        HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();

        layout.addComponents(menu, content);
        layout.setExpandRatio(content, 1f);

        final Navigator navigator = new Navigator(this, content);

        MenuBar.Command command1 = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {

                navigator.removeView("Hotel");
                navigator.addView("Hotel", new HotelForm());
                navigator.navigateTo("Hotel");

                hotelItem.setEnabled(false);
                categoryItem.setEnabled(true);

            }
        };

        MenuBar.Command command2 = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {

                navigator.removeView("HotelCategory");
                navigator.addView("HotelCategory", new CategoryForm());
                navigator.navigateTo("HotelCategory");

                categoryItem.setEnabled(false);
                hotelItem.setEnabled(true);

            }
        };

        hotelItem = menu.addItem("Hotel", VaadinIcons.BUILDING, command1);
        hotelItem.setEnabled(false);
        categoryItem = menu.addItem("HotelCategory", VaadinIcons.ACADEMY_CAP, command2);
        menu.setStyleName(ValoTheme.MENUBAR_BORDERLESS);

        navigator.addView("Hotels", new HotelForm());
        navigator.navigateTo("Hotels");
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}