package pl.onwelo.voteapp.adapter.voting.exception;

public class CandidateDuplicatedException extends RuntimeException {
    public CandidateDuplicatedException() {
        super("Found duplicated candidate");
    }
}
