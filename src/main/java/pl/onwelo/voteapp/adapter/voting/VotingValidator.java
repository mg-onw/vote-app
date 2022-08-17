package pl.onwelo.voteapp.adapter.voting;

import pl.onwelo.voteapp.model.voting.VotingEntity;
import pl.onwelo.voteapp.view.voting.dto.VoteDto;
import pl.onwelo.voteapp.view.voting.dto.VotingDto;

interface VotingValidator {
    void validate(VotingDto dto);
    void validate(VotingEntity voting, VoteDto vote);
}
