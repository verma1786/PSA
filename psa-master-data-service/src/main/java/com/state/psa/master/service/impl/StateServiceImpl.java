package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.State;
import com.state.psa.master.repository.StateRepository;
import com.state.psa.master.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    private CacheManager cacheManager;
    private List<State> states=null;
    private Cache cache =null;
    @Autowired
    public StateServiceImpl(StateRepository stateRepository,CacheManager cacheManager) {
        this.stateRepository = stateRepository;
        this.cacheManager = cacheManager;
        cache = cacheManager.getCache("stateCache");
        for(State state:findAll()){
            cache.put(state.getStateId(), state.getState());
        }
    }



    @Override
    public List<State> findAll() {return stateRepository.findAll();}

    @Override
    public Optional<State> findById(Integer stateId) {
        return stateRepository.findByStateId(stateId);
    }

    @Override
    public String getState(Integer stateId){
        System.out.println("stateId = " + stateId);
        String state= (String) cache.get(stateId).get();
        return state;
    }

    public void evictAllCaches() {
        log.info("Started current time is :: " + Calendar.getInstance().getTime());
        cacheManager.getCacheNames().stream().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        for(State state:findAll()){
            cache.put(state.getStateId(), state.getState());
        }
        log.info("End current time is :: " + Calendar.getInstance().getTime());
    }

    @Scheduled(cron = "0 0 0 * * *",zone = "Indian/Maldives")
    public void evictAllCachesAtIntervals() {
        evictAllCaches();
    }
}
