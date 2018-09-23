/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Pet;
import Entities.PetDTO;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andreas
 */
public class FacadePet {

    EntityManagerFactory emf;

    public FacadePet(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public List<Pet> getPets() {
        EntityManager em = emf.createEntityManager();

        List<Pet> pets = null;

        try {

            em.getTransaction().begin();
            TypedQuery<Pet> query = em.createNamedQuery("Pet.findAll", Pet.class);
            pets = query.getResultList();
            em.getTransaction().commit();
            return pets;

        } finally {
            em.close();
        }

    }

    public List<PetDTO> getPetsAliveDTO() {
        EntityManager em = emf.createEntityManager();

        List<PetDTO> pets = null;

        try {

            em.getTransaction().begin();
            Query q = em.createNamedQuery("Pet.findByDeath", Pet.class);
            q.setParameter("death", null);
            pets = q.getResultList();

            em.getTransaction().commit();
            return pets;
        } finally {
            em.close();

        }
    }

    public List<PetDTO> getPetsDTO() {
        EntityManager em = emf.createEntityManager();

        List<PetDTO> pets = null;
        try {

            em.getTransaction().begin();
            TypedQuery<PetDTO> query = em.createQuery("SELECT new Entities.PetDTO(p.name, p.birth, p.species, p.ownerId.firstName, p.ownerId.lastName) FROM Pet p", PetDTO.class);
            pets = query.getResultList();

            em.getTransaction().commit();
            return pets;

        } finally {
            em.clear();

        }

    }
}
