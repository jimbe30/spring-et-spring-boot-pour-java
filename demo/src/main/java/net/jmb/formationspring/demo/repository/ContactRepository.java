package net.jmb.formationspring.demo.repository;

import net.jmb.formationspring.demo.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
