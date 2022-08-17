package pl.onwelo.voteapp.adapter.voting.exception;

public class VoterDuplicatedException extends RuntimeException {
    public VoterDuplicatedException() {
        super("Found duplicated voter");
    }
}
