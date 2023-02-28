package com.example.pidev.services;

import com.example.pidev.Entities.RequestPartnership;
import com.example.pidev.Repositories.RequestPartnershipRepository;


import java.util.List;

public class RequestPartnershipService implements IRequestPartnership{
    RequestPartnershipRepository requestPartnershipRepository;

    @Override
    public List<RequestPartnership> retrieveAllRequestPartnership() {
        return requestPartnershipRepository.findAll();
    }

    @Override
    public RequestPartnership addRequestPartnership(RequestPartnership r) {
        return requestPartnershipRepository.save(r);
    }

    @Override
    public RequestPartnership updateRequestPartnership(RequestPartnership r) {
        return requestPartnershipRepository.save(r);
    }

    @Override
    public RequestPartnership retrieveRequestPartnership(Long idRequest) {
        return requestPartnershipRepository.findById(idRequest).get();
    }

    @Override
    public void deleteRequestPartnership(Long idRequest) {
        requestPartnershipRepository.deleteById(idRequest);

    }
}
