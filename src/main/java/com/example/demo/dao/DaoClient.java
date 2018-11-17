package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Client;

public interface DaoClient extends JpaRepository<Client, String> {

}
