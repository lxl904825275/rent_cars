package com.service;

import com.dao.ClientDao;
import com.dao.impl.ClientDaoImpl;
import com.entity.Client;

import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/4/1 0001
 */
public class ClientService {
    private ClientDao dao=new ClientDaoImpl();
    public Client findOne(String username){
        return dao.findOne(username);
    }
    public List<Client> findAll(){
        return  dao.findAll();
    }
    public int addClient(Client client){
        return dao.addClient(client);
    }
    public int updateClient(Client client){
        return dao.updateClient(client);
    }
    public Client findByIdNumber(String IdNumber) {
        return dao.findByIdNumber(IdNumber);
    }
    public List<Client> findAllClient(){
        return dao.findAllClient();
    }
}
