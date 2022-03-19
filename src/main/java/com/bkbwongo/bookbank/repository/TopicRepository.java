package com.bkbwongo.bookbank.repository;

import com.bkbwongo.bookbank.jpa.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author bkaaron
 * @created 04/02/2022 - 12:56 PM
 * @project bookbank
 */
@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByName(String name);
}
