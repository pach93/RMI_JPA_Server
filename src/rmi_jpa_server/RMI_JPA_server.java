/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_jpa_server;

import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import sti3.rmi.IUserImpl;
import sti3.rmi.IUtilisateur;

/**
 *
 * @author Pach
 */
public class RMI_JPA_server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {

        try {
            System.setSecurityManager(new SecurityManager());
            Registry registry = LocateRegistry.createRegistry(1069);
            IUtilisateur iuser= new IUserImpl();
            registry.bind("remoteUser", iuser);
           System.out.println("Objet distant déjà enregistré ");
           System.out.println("Serveur démarré en attente de connexion ");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }

    }

}
