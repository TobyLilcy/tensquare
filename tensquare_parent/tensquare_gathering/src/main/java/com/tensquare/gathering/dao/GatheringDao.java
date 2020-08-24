package com.tensquare.gathering.dao;

import com.tensquare.gathering.pojo.Gathering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Toby.Li on 2020/8/24.
 */
public interface GatheringDao extends JpaRepository<Gathering, String>, JpaSpecificationExecutor<Gathering> {

    public Page findByCity(String city, Pageable pageable);
}
