package com.apps.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apps.user.entity.ConversionHistory;
import com.apps.user.entity.User;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {
	List<ConversionHistory> findByUserOrderByCreatedAtDesc(User user);
	List<ConversionHistory> findByUserAndTypeOrderByCreatedAtDesc(User user, String type);
}
