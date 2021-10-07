package net.abdymazhit.mixmethod.controllers;

import net.abdymazhit.mixmethod.customs.Player;
import net.abdymazhit.mixmethod.models.PlayerModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Отвечает за работу страницы Single Rating
 *
 * @version   07.10.2021
 * @author    Islam Abdymazhit
 */
@Controller
public class SingleController {

    /** Менеджер работы базы данных */
    private final EntityManager entityManager;

    /** Последнее время обновления рейтинга */
    private Timestamp lastUpdate;

    /** Рейтинги игроков последнего обновления */
    private List<Player> players;

    /**
     * Инициализирует контролер
     * @param entityManager Менеджер работы базы данных
     */
    public SingleController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Выдает страницу Single Rating
     * @param model Модель страницы
     * @return Страница Single Rating
     */
    @GetMapping("/rating/single")
    public String single(Model model) {
        Timestamp currentTime = Timestamp.from(Instant.now());

        // Проверка, можно ли обновить рейтинг
        if(lastUpdate == null || currentTime.getTime() - lastUpdate.getTime() >= 30000) {
            Query query = entityManager.createNativeQuery("""
            SELECT p.id, @place\\:=@place+1 AS place, u.username as name, points FROM players AS p
            INNER JOIN users AS u ON u.id = p.player_id AND p.is_deleted IS NULL
            CROSS JOIN (SELECT @place\\:=0) as r
            ORDER BY points DESC LIMIT 100""", PlayerModel.class);
            List<PlayerModel> playerModels = query.getResultList();

            players = new ArrayList<>();
            for(PlayerModel playerModel : playerModels) {
                Player player = new Player(playerModel.place, playerModel.name, playerModel.points);
                players.add(player);
            }
            lastUpdate = currentTime;
        }

        model.addAttribute("players", players);
        return "single";
    }
}
