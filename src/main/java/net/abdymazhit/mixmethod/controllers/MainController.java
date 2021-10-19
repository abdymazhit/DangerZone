package net.abdymazhit.mixmethod.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Отвечает за работу главных страниц
 *
 * @version   20.10.2021
 * @author    Islam Abdymazhit
 */
@Controller
public class MainController {

    /**
     * Выдает главную страницу
     * @return Главная страница
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Выдает страницу правил
     * @return Страница правил
     */
    @GetMapping("/rules")
    public String rules() {
        return "rules";
    }

    /**
     * Выдает страницу персонала
     * @return Страница персонала
     */
    @GetMapping("/personal")
    public String personal() {
        return "personal";
    }

    /**
     * Выдает страницу Discord
     * @return Страница Discord
     */
    @GetMapping("/discord")
    public String discord() {
        return "discord";
    }
}