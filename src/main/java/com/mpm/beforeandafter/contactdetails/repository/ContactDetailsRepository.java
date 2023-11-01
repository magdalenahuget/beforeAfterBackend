package com.mpm.beforeandafter.contactdetails.repository;

import com.mpm.beforeandafter.contactdetails.model.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails,Long> {
}
