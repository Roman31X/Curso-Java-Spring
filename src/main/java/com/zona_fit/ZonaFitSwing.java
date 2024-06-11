package com.zona_fit;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.zona_fit.gui.ZonaFitForma;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {

    //Ejecutar Aquí
    public static void main(String[] args) {

        // Configuramos el modo Oscuro
        FlatDarculaLaf.setup();

        // Creamos una instancia de la fabricá de Spring
        ConfigurableApplicationContext contextoSpring =
                new SpringApplicationBuilder(ZonaFitSwing.class)
                        .headless(false).web(WebApplicationType.NONE).run(args);

        // Creamos un objeto de Swing
        // recuperaremos las dependencias dle objeto mediante las capas de Spring
        SwingUtilities.invokeLater(() -> {
            ZonaFitForma zonaFitForma = contextoSpring.getBean(ZonaFitForma.class);
            zonaFitForma.setVisible(true);
        });

    }
}
