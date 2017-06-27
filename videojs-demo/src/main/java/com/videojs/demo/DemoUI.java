package com.videojs.demo;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.videojs.VideoJS;

@Theme("demo")
@Title("VideoJS Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final VideoJS component = new VideoJS();
        File f = new File("/home/radek/Pobrane/afterglow_local_hd.mp4");
        component.appendSource(f, "video/mp4");
        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.addComponent(component);
        layout.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        Button b = new Button("append");
        b.addClickListener(e->{
            File f2 = new File("/home/radek/Pobrane/afterglow_local_hd.mp4");
            component.appendSource(f2, "type/mp4");
            component.appendSubtitles(f2, "en", "English");
        });
        layout.addComponent(b);
        setContent(layout);
    }
}
