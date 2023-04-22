package com.example.pidev.Services;

import com.example.pidev.DTO.Response;
import com.example.pidev.Interfaces.IResponseService;

import com.example.pidev.Repositories.ResponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResponseService implements IResponseService {
    private ResponseRepository responseRepository;

    @Override
    public Response addResp(Response resp) {
        return responseRepository.save(resp);
    }

    @Override
    public Response editResp(Response resp) {
        return responseRepository.save(resp);
    }

    @Override
    public List<Response> selectAllResp() {
        return responseRepository.findAll();
    }

    @Override
    public Response selectRespById(int responseId) {
        return responseRepository.findById(responseId).get();
    }

    @Override
    public void deleteRespById(int ResponseId) {
        responseRepository.deleteById(ResponseId);
    }

    @Override
    public void deleteResp(Response rec) {
        responseRepository.delete(rec);
    }

    @Override
    public List<Response> addAll(List<Response> listresp) {
        return responseRepository.saveAll(listresp);
    }

    @Override
    public void deleteRespAll(List<Response> listresp) { responseRepository.deleteAll(listresp);
    }

    @Override
    public void deleteRespAll() {
        responseRepository.deleteAll();
    }

}