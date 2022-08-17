package pl.onwelo.voteapp.adapter.voting;

import org.springframework.stereotype.Service;
import pl.onwelo.voteapp.model.voting.VotingEntity;
import pl.onwelo.voteapp.model.voting.VotingRepository;
import pl.onwelo.voteapp.view.voting.dto.VotingDto;
import pl.onwelo.voteapp.view.voting.dto.VoteDto;
import pl.onwelo.voteapp.view.voting.dto.VotingSummaryDto;
import pl.onwelo.voteapp.adapter.voting.exception.VotingNotFoundException;

import java.util.*;

@Service
class VotingServiceImpl implements VotingService {

    private final VotingRepository votingRepository;

    private final VotingMapper mapper;

    private final VotingValidator votingValidator;


    public VotingServiceImpl(VotingRepository votingRepository, VotingMapper mapper, VotingValidator votingValidator) {
        this.votingRepository = votingRepository;
        this.mapper = mapper;
        this.votingValidator = votingValidator;
    }


    @Override
    public void createVoting(VotingDto dto){
        votingValidator.validate(dto);
        VotingEntity votingEntity = mapper.toEntity(dto);
        votingRepository.save(votingEntity);
    }

    @Override
    public void castVote(VoteDto dto) {
        VotingEntity votingEntity = votingRepository.get()
                .orElseThrow(VotingNotFoundException::new);

        votingValidator.validate(votingEntity, dto);

        votingEntity.votes.put(dto.voter, dto.candidate);
        votingRepository.save(votingEntity);
    }

    @Override
    public Optional<VotingSummaryDto> getVoting() {
        return votingRepository.get()
                .map(mapper::toVotingSummary);
    }

}
