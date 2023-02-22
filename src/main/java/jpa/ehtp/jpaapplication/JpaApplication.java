package jpa.ehtp.jpaapplication;

import jpa.ehtp.jpaapplication.entities.Patient;
import jpa.ehtp.jpaapplication.entities.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class JpaApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository ;
    public static void main(String[] args) {

        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // ajouter 100 patient
        for (int i = 0; i < 100; i++) {
            patientRepository.save(
                    new Patient(null, "Ali", new Date(), true, (int) (Math.random() * 100))
            );
        }

        // la liste des patients
        /*
        List<Patient> patients =  patientRepository.findAll();
        patients.forEach(patient -> {
            System.out.println("======================");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getDateNaissance());
            System.out.println(patient.isMalade());
            System.out.println(patient.getScore());
        }); */

        // la liste des patient par pagination

        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0, 5));

        System.out.println("le nombre de page : " + patients.getTotalPages());
        System.out.println("le nombre d'élément total : " + patients.getTotalElements());
        System.out.println("le numéro de la page : " + patients.getNumber());
        List<Patient> content = patients.getContent();
        /*
        content.forEach(patient -> {
            System.out.println("======================");
            System.out.println(patient.getId());
            System.out.println(patient.getNom());
            System.out.println(patient.getDateNaissance());
            System.out.println(patient.isMalade());
            System.out.println(patient.getScore());
        });

         */

        Patient patient = patientRepository.findById(1L).orElse(null);
        System.out.println(patient.getNom());
        patient.setScore(300);
        patientRepository.save(patient);

        // liste des patients par malade
        Page<Patient> byMalad = patientRepository.findByMalade(true, PageRequest.of(0, 10));
        byMalad.forEach(p -> {
            System.out.println("======================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
        });
    }
}
