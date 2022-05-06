package com.state.psa.lost.service.impl;

import com.state.psa.lost.common.exception.ResourceNotFoundException;
import com.state.psa.lost.model.ComplainantDetail;
import com.state.psa.lost.model.LostArticle;
import com.state.psa.lost.repository.ComplainantRepository;
import com.state.psa.lost.service.IComplainantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComplainantServiceImpl implements IComplainantService {
    @Autowired
    private ComplainantRepository complainantRepository;


    @Override
    public Optional<ComplainantDetail> findById(Long id) {
        return complainantRepository.findById(id);
    }

    @Override
    public List<ComplainantDetail> findAll(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<ComplainantDetail> pagedResult = complainantRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<ComplainantDetail>();
        }
    }

    @Override
    public ComplainantDetail save(ComplainantDetail complainantDetail) {
          complainantDetail.getArticles().forEach(d -> d.setComplainantDetail(complainantDetail));
          return complainantRepository.save(complainantDetail);
    }

    @Override
    public ComplainantDetail updateComplainantDetail(Long compId,ComplainantDetail complainantDetail) {
        Optional<ComplainantDetail> resComplainantDetail = complainantRepository.findById(compId);
        resComplainantDetail.ifPresent(complainant -> {
            complainant.setAadhaarUIDNo(complainantDetail.getAadhaarUIDNo());
            complainant.setComplainantName(complainantDetail.getComplainantName());
            complainant.setComplainantEmail(complainantDetail.getComplainantEmail());
            complainant.setComplainantMobile(complainantDetail.getComplainantMobile());
            complainant.setComplainantAddress(complainantDetail.getComplainantAddress());
            complainant.setAnyOtherDetails(complainantDetail.getAnyOtherDetails());
            complainant.setGender(complainantDetail.getGender());
            complainant.setGenderId(complainantDetail.getGenderId());
            complainant.setBhamashahCardNo(complainantDetail.getBhamashahCardNo());
            complainant.setDateOfLoss(complainantDetail.getDateOfLoss());
            complainant.setParentName(complainantDetail.getParentName());
            complainant.setPlaceOfLoss(complainantDetail.getPlaceOfLoss());
            complainant.setTimeOfLoss(complainantDetail.getTimeOfLoss());

          /*  List<LostArticle> lostArticles=complainantDetail.getArticles();
            List<LostArticle> lostArticleList=new ArrayList<>();

            LostArticle lostArticle = null;
            for (LostArticle dto : lostArticles) {
                if (dto.getArticleId() != null) {
                    lostArticle = new LostArticle();
                    lostArticle.setDescription(dto.getDescription());
                    lostArticle.setArticleId(dto.getArticleId());
                    lostArticle.setLostArticle(dto.getLostArticle());
                    lostArticleList.add(lostArticle);
                }
                complainant.setArticles(lostArticleList);
            }*/

        });

        ComplainantDetail result = resComplainantDetail.orElse(null);
        return complainantRepository.save(result);
    }

    @Override
    public void deleteComplainantDetail(Long compId) {
        ComplainantDetail complainantDetail= complainantRepository.findById(compId).orElseThrow(() -> new ResourceNotFoundException("ComplainantDetail", "id", compId));
        complainantRepository.delete(complainantDetail);
    }
}
