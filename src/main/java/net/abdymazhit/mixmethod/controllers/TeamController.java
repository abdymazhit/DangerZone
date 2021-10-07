package net.abdymazhit.mixmethod.controllers;

import net.abdymazhit.mixmethod.customs.Team;
import net.abdymazhit.mixmethod.models.TeamModel;
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
 * Отвечает за работу страницы Team Rating
 *
 * @version   07.10.2021
 * @author    Islam Abdymazhit
 */
@Controller
public class TeamController {

    /** Менеджер работы базы данных */
    private final EntityManager entityManager;

    /** Последнее время обновления рейтинга */
    private Timestamp lastUpdate;

    /** Рейтинги команд последнего обновления */
    private List<Team> teams;

    /**
     * Инициализирует контролер
     * @param entityManager Менеджер работы базы данных
     */
    public TeamController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Выдает страницу Team Rating
     * @param model Модель страницы
     * @return Страница Team Rating
     */
    @GetMapping("/rating/team")
    public String team(Model model) {
        Timestamp currentTime = Timestamp.from(Instant.now());

        // Проверка, можно ли обновить рейтинг
        if(lastUpdate == null || currentTime.getTime() - lastUpdate.getTime() >= 30000) {
            Query query = entityManager.createNativeQuery("""
            SELECT distinct t.id, @place\\:=@place+1 AS place, name, u.username as leader_name, points FROM teams AS t
            INNER JOIN users AS u ON u.id = t.leader_id AND t.is_deleted IS NULL
            CROSS JOIN (SELECT @place\\:=0) as r
            ORDER BY points DESC LIMIT 100""", TeamModel.class);
            List<TeamModel> teamModels = query.getResultList();

            teams = new ArrayList<>();
            for(TeamModel teamModel : teamModels) {
                Team team = new Team(teamModel.place, teamModel.name, teamModel.leaderName, teamModel.points);
                teams.add(team);
            }
            lastUpdate = currentTime;
        }

        model.addAttribute("teams", teams);
        return "team";
    }
}
