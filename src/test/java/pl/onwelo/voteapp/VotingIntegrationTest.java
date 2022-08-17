package pl.onwelo.voteapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import pl.onwelo.voteapp.model.voting.VotingEntity;
import pl.onwelo.voteapp.model.voting.VotingRepository;
import pl.onwelo.voteapp.view.voting.dto.VoteDto;
import pl.onwelo.voteapp.view.voting.dto.VotingDto;

import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VotingIntegrationTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String CANDIDATE_1 = "candidate1";
    private static final String CANDIDATE_2 = "candidate2";
    private static final String INVALID_CANDIDATE = "invalid candidate";
    private static final String VOTER_1 = "voter1";
    private static final String VOTER_2 = "voter2";
    private static final String INVALID_VOTER = "invalid voter";


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotingRepository votingRepository;


    @Test
    void createVoting_happyPath() throws Exception {
        //given
        VotingDto voting = new VotingDto(asList(VOTER_1, VOTER_2), asList(CANDIDATE_1, CANDIDATE_2));

        //when-then
        mockMvc.perform(postWithBody("/voting", voting))
                .andExpect(status().isCreated());
    }

    @Test
    void createVoting_voterDuplicated() throws Exception {
        //given
        VotingDto voting = new VotingDto(asList(VOTER_1, VOTER_1), asList(CANDIDATE_1, CANDIDATE_2));

        //when-then
        mockMvc.perform(postWithBody("/voting", voting))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createVoting_candidateDuplicated() throws Exception {
        //given
        VotingDto voting = new VotingDto(asList(VOTER_1, VOTER_2), asList(CANDIDATE_1, CANDIDATE_1));

        //when-then
        mockMvc.perform(postWithBody("/voting", voting))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getVotingSummary_happyPath() throws Exception {
        //given
        votingExists();

        //when-then
        mockMvc.perform(get("/voting"))
                .andExpect(status().isOk());
    }

    @Test
    void getVotingSummary_votingNotExist() throws Exception {
        //given
        votingExists(false);

        //when-then
        mockMvc.perform(get("/voting"))
                .andExpect(status().isNotFound());
    }


    @Test
    void castVote_happyPath() throws Exception {
        //given
        votingExists();
        VoteDto vote = new VoteDto(VOTER_1, CANDIDATE_1);

        //when-then
        mockMvc.perform(postWithBody("/voting/vote", vote))
                .andExpect(status().isCreated());
    }

    @Test
    void castVote_invalidVoter() throws Exception {
        //given
        votingExists();
        VoteDto vote = new VoteDto(INVALID_VOTER, CANDIDATE_1);

        //when-then
        mockMvc.perform(postWithBody("/voting/vote", vote))
                .andExpect(status().isNotFound());
    }

    @Test
    void castVote_invalidCandidate() throws Exception {
        //given
        votingExists();
        VoteDto vote = new VoteDto(VOTER_1, INVALID_CANDIDATE);

        //when-then
        mockMvc.perform(postWithBody("/voting/vote", vote))
                .andExpect(status().isNotFound());
    }

    @Test
    void castVote_voteTwice() throws Exception {
        //given
        votingExists();
        VoteDto vote = new VoteDto(VOTER_1, CANDIDATE_1);

        //when-then
        mockMvc.perform(postWithBody("/voting/vote", vote))
                .andExpect(status().isCreated());
        mockMvc.perform(postWithBody("/voting/vote", vote))
                .andExpect(status().isBadRequest());
    }


    private MockHttpServletRequestBuilder postWithBody(String url, Object body) throws JsonProcessingException {
        return post(url)
                .content(MAPPER.writeValueAsString(body))
                .contentType(APPLICATION_JSON);
    }

    private void votingExists(){
        votingExists(true);
    }

    private void votingExists(boolean exists){
        Optional<VotingEntity> repositoryResponse = exists
                ? Optional.of(new VotingEntity(asList(CANDIDATE_1,CANDIDATE_2),asList(VOTER_1,VOTER_2)))
                : Optional.empty();
        when(votingRepository.get()).thenReturn(repositoryResponse);
    }

}
