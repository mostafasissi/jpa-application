package jpa.ehtp.jpaapplication.entities.repositories;

import jpa.ehtp.jpaapplication.entities.Patient;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public List<Patient> findByMalade(boolean m);
    public Page<Patient> findByMalade(boolean m , Pageable pageable);

    public List<Patient> findByMaladeIsTrueAndScoreGreaterThan(int score);
    public List<Patient> findByMaladeIsTrueAndDateNaissanceBetweenOrNomLike(Date d1 , Date d2 , String n);
    //une autre maniere de déclarer ces methodes (En  utilisant HQL)
    @Query("select p from Patient p where p.nom like :x and p.score>:y")
    public  List<Patient> recherchePatient(@Param("x")String nom ,@Param("y") int scoreMin );
}
