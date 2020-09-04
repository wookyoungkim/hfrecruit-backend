package com.hanium.hfrecruit.service.spec;

import com.hanium.hfrecruit.domain.spec.SpecRepository;
import com.hanium.hfrecruit.dto.PersonalSpecDto;
import com.hanium.hfrecruit.dto.SpecDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecService {
    private final SpecRepository specRepository;

    @Autowired
    public SpecService(SpecRepository specRepository) {
        this.specRepository = specRepository;
    }

    public List<SpecDto> findAll() {
        return specRepository.findAll().stream().map(SpecDto::new).collect(Collectors.toList());
    }
    public SpecDto findBySpecId(Long specId) {
        return new SpecDto(specRepository.findBySpecId(specId));
}

    public List<String> findAllBySpecId(List<PersonalSpecDto> personalSpecDtos){
        List<String> specNames = new ArrayList<>();
        for(int i = 0 ; i<personalSpecDtos.size(); i++)
        {
            specNames.add(specRepository.findBySpecId(personalSpecDtos.get(i).getSpec().getSpecId()).getSpecName());
        }
        return specNames;
    }

}
