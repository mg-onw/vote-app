package pl.onwelo.voteapp.adapter.voting.exception;

public class CandidateNotFoundException extends RuntimeException {
    public CandidateNotFoundException(String candidate) {
        super("Candidate " + candidate + " not found");
    }
}
