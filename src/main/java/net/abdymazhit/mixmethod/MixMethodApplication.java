package net.abdymazhit.mixmethod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс для работы сайта
 *
 * @version   07.10.2021
 * @author    Islam Abdymazhit
 */
@SpringBootApplication
public class MixMethodApplication {

    /** Запускает весь сайт */
    public static void main(String[] args) {
        SpringApplication.run(MixMethodApplication.class, args);
    }
}