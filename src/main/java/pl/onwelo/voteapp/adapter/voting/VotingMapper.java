package pl.onwelo.voteapp.adapter.voting;

import pl.onwelo.voteapp.model.voting.VotingEntity;
import pl.onwelo.voteapp.view.voting.dto.VotingDto;
import pl.onwelo.voteapp.view.voting.dto.VotingSummaryDto;

interface VotingMapper {
    VotingEntity toEntity(VotingDto dto);
    VotingSummaryDto toVotingSummary(VotingEntity votingEntity);
}
