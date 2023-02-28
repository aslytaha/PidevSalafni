package com.example.pidev.services;


import com.example.pidev.Entities.RequestPartnership;

import java.util.List;

public interface IRequestPartnership {

    List<RequestPartnership> retrieveAllRequestPartnership();

    RequestPartnership addRequestPartnership(RequestPartnership r);

    RequestPartnership updateRequestPartnership (RequestPartnership r);

    RequestPartnership retrieveRequestPartnership (Long idRequest);

    void deleteRequestPartnership( Long idRequest);
}
