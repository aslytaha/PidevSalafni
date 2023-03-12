package com.example.pidev.Service.Interface;
import com.example.pidev.Entities.Transaction;
import java.util.List;


public interface ITransaction {
    void depot(Integer IDClient, float Amount)throws Exception;
    void retrait(Integer IDClient, float Amount)throws Exception;

}
