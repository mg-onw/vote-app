package pl.onwelo.voteapp.model.voting;

import java.util.Optional;

public interface VotingRepository {
    void save(VotingEntity voting);
    Optional<VotingEntity> get();
}
