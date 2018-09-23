/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Executers;

import Facade.FacadePet;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andreas
 */
public class Execute {
    public static void main(String[] args) {
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_PetHospital_war_1.0-SNAPSHOTPU");
        
        FacadePet FP = new FacadePet(emf);
        
        FP.getPets();
        
    }
}
