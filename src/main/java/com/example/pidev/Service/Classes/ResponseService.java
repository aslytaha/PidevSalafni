package com.example.pidev.Service.Classes;

import com.example.pidev.Entities.Response;
import com.example.pidev.Repository.ResponseRepository;
import com.example.pidev.Service.Interface.IResponseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResponseService implements IResponseService {
    private ResponseRepository ResponseRepository;

    @Override
    public Response addResp(Response resp) {
        return ResponseRepository.save(resp);
    }

    @Override
    public Response editResp(Response resp) {
        return ResponseRepository.save(resp);
    }

    @Override
    public List<Response> selectAllResp() {
        return ResponseRepository.findAll();
    }

    @Override
    public Response selectRespById(int responseId) {
        return ResponseRepository.findById(responseId).get();
    }

    @Override
    public void deleteRespById(int ResponseId) {
        ResponseRepository.deleteById(ResponseId);
    }

    @Override
    public void deleteResp(Response rec) {
        ResponseRepository.delete(rec);
    }

    @Override
    public List<Response> addAll(List<Response> listresp) {
        return ResponseRepository.saveAll(listresp);
    }

    @Override
    public void deleteRespAll(List<Response> listresp) { ResponseRepository.deleteAll(listresp);
    }

    @Override
    public void deleteRespAll() {
        ResponseRepository.deleteAll();
    }

}