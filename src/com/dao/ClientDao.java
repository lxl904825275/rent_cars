package com.dao;

import com.entity.Client;
import com.entity.PageBean;

import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/3/26 0026
 */
public interface ClientDao {
    public Client findOne(String username);

    public List<Client> findAll();

    public int getConut();

    public List<Client> queryPageList(PageBean pageBean);

    public int addClient(Client client);

    public int deleteClient(int clientId);

    public int updateClient(Client client);

    public Client queryOne(int clientId);

    public Client findByIdNumber(String IdNumber);

    public List<Client> findAllClient();
}
