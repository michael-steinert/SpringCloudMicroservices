package com.company.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFraudCheckHistoryRepository extends JpaRepository<FraudCheckHistory, Integer> {
}
