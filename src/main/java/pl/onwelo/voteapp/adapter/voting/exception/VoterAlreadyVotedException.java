package pl.onwelo.voteapp.adapter.voting.exception;

public class VoterAlreadyVotedException extends RuntimeException {
    public VoterAlreadyVotedException(String voter) {
        super("Voter " + voter + " already voted");
    }
}
