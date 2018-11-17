package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Paiement;

public interface DaoPaiement extends JpaRepository<Paiement ,String> {

}
