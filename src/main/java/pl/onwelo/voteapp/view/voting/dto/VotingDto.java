package pl.onwelo.voteapp.view.voting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VotingDto {

    public final List<String> candidates;

    public final List<String> voters;


    public VotingDto(
            @JsonProperty("candidates") List<String> candidates,
            @JsonProperty("voters")     List<String> voters
    ) {
        this.candidates = candidates;
        this.voters = voters;
    }

}
