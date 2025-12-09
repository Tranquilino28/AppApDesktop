/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.Utils;

/**
 *
 * @author usuario
 */
import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {

    public static Logger getLogger(String name, String fileName) {
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false); // evita duplicados

        // Limpia handlers anteriores si existen
        for (Handler h : logger.getHandlers()) {
            logger.removeHandler(h);
        }

        try {
            // Handler para archivo
            FileHandler fh = new FileHandler(fileName, true); // append = true
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);

            // Handler para consola
            ConsoleHandler ch = new ConsoleHandler();
            ch.setFormatter(new SimpleFormatter());
            logger.addHandler(ch);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return logger;
    }
}

