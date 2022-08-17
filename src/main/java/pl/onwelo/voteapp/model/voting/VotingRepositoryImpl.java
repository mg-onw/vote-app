package pl.onwelo.voteapp.model.voting;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class VotingRepositoryImpl implements VotingRepository {


    private VotingEntity voting;


    @Override
    public void save(VotingEntity voting) {
        this.voting = voting;
    }

    @Override
    public Optional<VotingEntity> get() {
        return Optional.ofNullable(voting);
    }

}
