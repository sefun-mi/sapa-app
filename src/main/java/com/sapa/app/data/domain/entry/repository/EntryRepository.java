package com.sapa.app.data.domain.entry.repository;

import com.sapa.app.data.domain.entry.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
}
