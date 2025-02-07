package com.ourdept.crm_software.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ourdept.crm_software.crm.domain.entities.core.Announcements;
import com.ourdept.crm_software.crm.domain.entities.core.Company;

public interface AnnouncementsRepository extends JpaRepository<Announcements, Long> {

	Optional<Announcements> findByCompany(Company company);

}
