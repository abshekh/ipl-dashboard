package io.github.abshekh.ipldashboard.data;

import io.github.abshekh.ipldashboard.model.Match;
import io.github.abshekh.ipldashboard.model.Match.MatchBuilder;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * MatchDataProcessor
 */
@Slf4j
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  @Override
  public Match process(MatchInput matchInput) throws Exception {
    MatchBuilder match = Match.builder();
    // DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    match
      .id(Long.parseLong(matchInput.getId()))
      .city(matchInput.getCity())
      .date(LocalDate.parse(matchInput.getDate()))
      .playerOfMatch(matchInput.getPlayer_of_match())
      .venue(matchInput.getVenue())
      .neutralVenue(matchInput.getNeutral_venue())
      .tossWinner(matchInput.getToss_winner())
      .tossDecision(matchInput.getToss_decision())
      .matchWinner(matchInput.getWinner())
      .result(matchInput.getResult())
      .resultMargin(matchInput.getResult_margin())
      .umpire1(matchInput.getUmpire1())
      .umpire2(matchInput.getUmpire2());

    String firstInningsTeam;
    String secondInningsTeam;

    final String BAT = "bat";

    if (BAT.equals(matchInput.getToss_decision())) {
      firstInningsTeam = matchInput.getToss_winner();
      secondInningsTeam =
        matchInput.getTeam1().equals(firstInningsTeam)
          ? matchInput.getTeam2()
          : matchInput.getTeam1();
    } else {
      secondInningsTeam = matchInput.getToss_winner();
      firstInningsTeam =
        matchInput.getTeam1().equals(secondInningsTeam)
          ? matchInput.getTeam2()
          : matchInput.getTeam1();
    }

    match.team1(firstInningsTeam).team2(secondInningsTeam);

    log.info("Converting");
    return match.build();
  }
}
