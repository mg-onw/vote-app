package pl.onwelo.voteapp.adapter.voting.exception;

public class VotingNotFoundException extends RuntimeException {
    public VotingNotFoundException() {
        super("Voting not found");
    }
}
