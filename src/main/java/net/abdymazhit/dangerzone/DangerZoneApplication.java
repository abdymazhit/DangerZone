package net.abdymazhit.dangerzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс для работы сайта
 *
 * @version   23.10.2021
 * @author    Islam Abdymazhit
 */
@SpringBootApplication
public class DangerZoneApplication {

    /** Запускает весь сайт */
    public static void main(String[] args) {
        SpringApplication.run(DangerZoneApplication.class, args);
    }
}