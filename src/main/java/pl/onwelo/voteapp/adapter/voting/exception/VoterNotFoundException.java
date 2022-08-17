package pl.onwelo.voteapp.adapter.voting.exception;

public class VoterNotFoundException extends RuntimeException {

    public VoterNotFoundException(String voter) {
        super("Voter " + voter + " not found");
    }

}
