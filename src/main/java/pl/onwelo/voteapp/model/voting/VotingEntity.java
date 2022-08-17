package pl.onwelo.voteapp.model.voting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotingEntity {

    public final List<String> candidates;

    public final Map<String,String> votes;


    public VotingEntity(List<String> candidates, List<String> voters) {
        this.candidates = candidates;
        votes = new HashMap<>();
        voters.forEach(voter -> votes.put(voter,null));
    }

}
