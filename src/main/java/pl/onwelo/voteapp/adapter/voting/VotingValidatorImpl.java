package pl.onwelo.voteapp.adapter.voting;

import org.springframework.stereotype.Component;
import pl.onwelo.voteapp.adapter.voting.exception.CandidateNotFoundException;
import pl.onwelo.voteapp.adapter.voting.exception.VoterAlreadyVotedException;
import pl.onwelo.voteapp.adapter.voting.exception.VoterNotFoundException;
import pl.onwelo.voteapp.adapter.voting.exception.CandidateDuplicatedException;
import pl.onwelo.voteapp.adapter.voting.exception.VoterDuplicatedException;
import pl.onwelo.voteapp.model.voting.VotingEntity;
import pl.onwelo.voteapp.view.voting.dto.VoteDto;
import pl.onwelo.voteapp.view.voting.dto.VotingDto;

import java.util.HashSet;
import java.util.List;

@Component
class VotingValidatorImpl implements VotingValidator {

    @Override
    public void validate(VotingDto voting) {
        if(hasDuplicates(voting.candidates)){
            throw new CandidateDuplicatedException();
        }

        if(hasDuplicates(voting.voters)){
            throw new VoterDuplicatedException();
        }
    }

    @Override
    public void validate(VotingEntity voting, VoteDto vote) {
        if(!voting.votes.containsKey(vote.voter)){
            throw new VoterNotFoundException(vote.voter);
        }

        if(!voting.candidates.contains(vote.candidate)){
            throw new CandidateNotFoundException(vote.candidate);
        }

        if(voting.votes.get(vote.voter) != null){
            throw new VoterAlreadyVotedException(vote.voter);
        }
    }


    private boolean hasDuplicates(List<?> list){
        return new HashSet<>(list).size() != list.size();
    }

}
