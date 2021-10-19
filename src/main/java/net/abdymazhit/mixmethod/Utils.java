package net.abdymazhit.mixmethod;

/**
 * Инструменты для облегчения работы
 *
 * @version   20.10.2021
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
            return "/images/leagues/1.svg";
        } else if(points <= 950) {
            return "/images/leagues/2.svg";
        } else if(points <= 1100) {
            return"/images/leagues/3.svg";
        } else if(points <= 1250) {
            return"/images/leagues/4.svg";
        } else if(points <= 1400) {
            return"/images/leagues/5.svg";
        } else if(points <= 1550) {
            return"/images/leagues/6.svg";
        } else if(points <= 1700) {
            return"/images/leagues/7.svg";
        } else if(points <= 1850) {
            return"/images/leagues/8.svg";
        } else if(points <= 2000) {
            return"/images/leagues/9.svg";
        } else {
            return"/images/leagues/10.svg";
        }
    }
}
