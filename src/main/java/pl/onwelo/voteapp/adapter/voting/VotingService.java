package pl.onwelo.voteapp.adapter.voting;

import pl.onwelo.voteapp.view.voting.dto.VotingDto;
import pl.onwelo.voteapp.view.voting.dto.VoteDto;
import pl.onwelo.voteapp.view.voting.dto.VotingSummaryDto;

import java.util.Optional;

public interface VotingService {
    void createVoting(VotingDto dto);
    void castVote(VoteDto dto);
    Optional<VotingSummaryDto> getVoting();
}
