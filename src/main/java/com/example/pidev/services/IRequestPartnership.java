package com.example.pidev.services;


import com.example.pidev.Entities.RequestPartnership;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IRequestPartnership {

    List<RequestPartnership> retrieveAllRequestPartnership();

    RequestPartnership addRequestPartnership(RequestPartnership r);

    RequestPartnership updateRequestPartnership (RequestPartnership r);

    RequestPartnership retrieveRequestPartnership (Long idRequest);

    void deleteRequestPartnership( Long idRequest);
}
