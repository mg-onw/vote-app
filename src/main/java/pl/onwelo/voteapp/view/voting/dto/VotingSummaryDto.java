package pl.onwelo.voteapp.view.voting.dto;

import java.util.List;
import java.util.Map;

public class VotingSummaryDto {

    public final List<String> alreadyVoted;

    public final List<String> stillCanVote;

    public final Map<String,Integer> votesPerCandidate;


    public VotingSummaryDto(List<String> alreadyVoted, List<String> stillCanVote, Map<String, Integer> votesPerCandidate) {
        this.alreadyVoted = alreadyVoted;
        this.stillCanVote = stillCanVote;
        this.votesPerCandidate = votesPerCandidate;
    }

}
