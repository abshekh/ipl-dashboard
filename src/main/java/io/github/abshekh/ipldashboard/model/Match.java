package io.github.abshekh.ipldashboard.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Match
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Match {

  @Id
  private long id;

  private String city;
  private LocalDate date;
  private String playerOfMatch;
  private String venue;
  private String neutralVenue;
  private String team1;
  private String team2;
  private String tossWinner;
  private String tossDecision;
  private String matchWinner;
  private String result;
  private String resultMargin;
  private String umpire1;
  private String umpire2;
}
