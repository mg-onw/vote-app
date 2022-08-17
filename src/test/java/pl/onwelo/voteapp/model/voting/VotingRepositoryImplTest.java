package pl.onwelo.voteapp.model.voting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class VotingRepositoryImplTest {

    private static final VotingEntity VOTING = new VotingEntity(emptyList(),emptyList());
    private static final VotingEntity ANOTHER_VOTING = new VotingEntity(emptyList(),emptyList());


    private VotingRepositoryImpl repository;


    @BeforeEach
    void setup(){
        repository = new VotingRepositoryImpl();
    }


    @Test
    void saveAndGet_happyPath(){
        //given
        repository.save(VOTING);

        //when
        Optional<VotingEntity> savedEntity = repository.get();

        //then
        assertTrue(savedEntity.isPresent());
        assertEquals(savedEntity.get(), VOTING);
    }

    @Test
    void saveAndGet_overwrite(){
        //given
        repository.save(VOTING);

        //when
        repository.save(ANOTHER_VOTING);
        Optional<VotingEntity> savedEntity = repository.get();

        //then
        assertTrue(savedEntity.isPresent());
        assertEquals(savedEntity.get(), ANOTHER_VOTING);
    }

    @Test
    void get_entityNotFound(){
        //when
        Optional<VotingEntity> savedEntity = repository.get();

        //then
        assertFalse(savedEntity.isPresent());
    }

}