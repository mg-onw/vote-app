package pl.onwelo.voteapp.view.voting;

import org.springframework.web.bind.annotation.*;
import pl.onwelo.voteapp.adapter.voting.VotingService;
import pl.onwelo.voteapp.view.voting.dto.VoteDto;
import pl.onwelo.voteapp.view.voting.dto.VotingDto;
import pl.onwelo.voteapp.view.voting.dto.VotingSummaryDto;
import pl.onwelo.voteapp.adapter.voting.exception.VotingNotFoundException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("voting")
public class VotingController {

    private final VotingService votingService;


    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public void createVoting(@RequestBody VotingDto dto){
        votingService.createVoting(dto);
    }

    @GetMapping
    public VotingSummaryDto getVotingSummary(){
        return votingService.getVoting()
                .orElseThrow(VotingNotFoundException::new);
    }

    @PostMapping("vote")
    @ResponseStatus(CREATED)
    public void castVote(@RequestBody VoteDto dto){
        votingService.castVote(dto);
    }

}
