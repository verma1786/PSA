package com.state.psa.lost.service;

import com.state.psa.lost.model.ComplainantDetail;

import java.util.List;
import java.util.Optional;

public interface IComplainantService {
    public Optional<ComplainantDetail> findById(Long id);
    public List<ComplainantDetail> findAll(Integer pageNo, Integer pageSize, String sortBy);
    public ComplainantDetail save(ComplainantDetail complainantDetail);
    public ComplainantDetail updateComplainantDetail(Long lostId, ComplainantDetail complainantDetail);
    public void deleteComplainantDetail(Long complainantId);

}
