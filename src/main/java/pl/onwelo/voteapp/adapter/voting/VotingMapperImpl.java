package pl.onwelo.voteapp.adapter.voting;

import org.springframework.stereotype.Component;
import pl.onwelo.voteapp.model.voting.VotingEntity;
import pl.onwelo.voteapp.view.voting.dto.VotingDto;
import pl.onwelo.voteapp.view.voting.dto.VotingSummaryDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Component
class VotingMapperImpl implements VotingMapper {

    @Override
    public VotingEntity toEntity(VotingDto dto) {
        return new VotingEntity(dto.candidates, dto.voters);
    }

    @Override
    public VotingSummaryDto toVotingSummary(VotingEntity votingEntity) {
        List<String> alreadyVoted = new ArrayList<>();
        List<String> stillCanVote = new ArrayList<>();
        separateVoters(votingEntity, alreadyVoted, stillCanVote);

        Map<String, Integer> votesPerCandidate = calculateVotesPerCandidate(votingEntity);

        return new VotingSummaryDto(alreadyVoted, stillCanVote, votesPerCandidate);
    }


    private static Map<String, Integer> calculateVotesPerCandidate(VotingEntity votingEntity) {
        Map<String,Integer> votesPerCandidate = votingEntity.candidates.stream()
                .collect(Collectors.toMap(voter -> voter, voter -> 0));

        votingEntity.votes.forEach((voter, candidate) -> {
            int currentVoteCount = votesPerCandidate.getOrDefault(candidate, 0);
            votesPerCandidate.replace(candidate, currentVoteCount + 1);
        });

        return votesPerCandidate;
    }

    private static void separateVoters(VotingEntity votingEntity, List<String> alreadyVoted, List<String> stillCanVote) {
        votingEntity.votes.forEach((voter, candidate) -> {
            List<String> matchingList = isNull(candidate)
                    ? stillCanVote
                    : alreadyVoted;
            matchingList.add(voter);
        });
    }

}
