package com.state.psa.community.service.impl;

import com.state.psa.community.model.State;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.repository.CLGMemberRepository;
import com.state.psa.community.service.LoadMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@PropertySource("classpath:/messages/business/uri.properties")
@Service
public class LoadMasterServiceImpl implements LoadMasterService {
    @Value("${get.states.url}")
    private String restUrl;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CLGMemberRepository clgMemberRepository;

    @Override
    public State findById(Integer stateId){
        State responseEntity= restTemplate.getForObject("http://PSA-MASTER-SERVICE/psa-master/states/"+stateId, State.class);
        return responseEntity;
    }

    @Override
    public List<CLGMemberDetails> findAllCLGMemberDetails() {
        List<CLGMemberDetails> clgMemberDetails=  clgMemberRepository.findAll();
        Map<Integer, String> stateMap= getAllStates().stream().collect(Collectors.toMap(State::getStateId, State::getState));
        clgMemberDetails.stream().forEach(memberDetails -> memberDetails.setState(stateMap.get(memberDetails.getStateId())));
        return clgMemberDetails;
    }

    List<State> getAllStates(){
        ResponseEntity<List<State>> responseEntity = restTemplate.exchange(restUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<State>>() {});
        List<State> states = responseEntity.getBody();
      return states;
    }



}
