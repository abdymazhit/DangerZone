package net.abdymazhit.mixmethod;

/**
 * Инструменты для облегчения работы
 *
 * @version   07.10.2021
 * @author    Islam Abdymazhit
 */
public class Utils {

    /**
     * Получает изображение лиги
     * @param points Очки
     * @return Изображение лиги
     */
    public static String getLeagueImage(int points) {
        if(points <= 800) {
            return "/img/leagues/1.svg";
        } else if(points <= 950) {
            return "/img/leagues/2.svg";
        } else if(points <= 1100) {
            return"/img/leagues/3.svg";
        } else if(points <= 1250) {
            return"/img/leagues/4.svg";
        } else if(points <= 1400) {
            return"/img/leagues/5.svg";
        } else if(points <= 1550) {
            return"/img/leagues/6.svg";
        } else if(points <= 1700) {
            return"/img/leagues/7.svg";
        } else if(points <= 1850) {
            return"/img/leagues/8.svg";
        } else if(points <= 2000) {
            return"/img/leagues/9.svg";
        } else {
            return"/img/leagues/10.svg";
        }
    }
}
