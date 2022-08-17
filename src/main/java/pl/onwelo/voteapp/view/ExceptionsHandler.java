package pl.onwelo.voteapp.view;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.onwelo.voteapp.adapter.voting.exception.CandidateDuplicatedException;
import pl.onwelo.voteapp.adapter.voting.exception.VoterDuplicatedException;
import pl.onwelo.voteapp.adapter.voting.exception.VotingNotFoundException;
import pl.onwelo.voteapp.adapter.voting.exception.CandidateNotFoundException;
import pl.onwelo.voteapp.adapter.voting.exception.VoterAlreadyVotedException;
import pl.onwelo.voteapp.adapter.voting.exception.VoterNotFoundException;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            VotingNotFoundException.class,
            VoterNotFoundException.class,
            CandidateNotFoundException.class,
    })
    public ResponseEntity<String> handleNotFoundExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            VoterDuplicatedException.class,
            CandidateDuplicatedException.class,
            VoterAlreadyVotedException.class
    })
    public ResponseEntity<String> handleBadRequestExceptions(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
