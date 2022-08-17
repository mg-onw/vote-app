package pl.onwelo.voteapp.view.voting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteDto {

    public final String voter;

    public final String candidate;


    public VoteDto(
            @JsonProperty("voter")      String voter,
            @JsonProperty("candidate")  String candidate
    ) {
        this.voter = voter;
        this.candidate = candidate;
    }

}
