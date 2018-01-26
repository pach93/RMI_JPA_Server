/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sti3.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import stic3.mapping.Departement;
import stic3.mapping.Employe;
import stic3.mapping.User;

/**
 *
 * @author Pach
 */
public class IUserImpl extends UnicastRemoteObject implements IUtilisateur {

    private EntityManager em = Persistence.createEntityManagerFactory("RMI_JPA_serverPU").createEntityManager();

    public IUserImpl() throws RemoteException {
        super();
    }

    @Override
    public User connexion(String login, String password) throws RemoteException {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.login=:log AND u.password=:pw");
        query.setParameter("log", login);
        query.setParameter("pw", password);
        try {
            List<User> us = query.getResultList();
            if (us.size() > 0) {
                return us.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        em.createQuery("SELECT s FROM Departement d JOIN d.employeList e");
        return null;
    }

    public List<Employe> getEmpBySalDept(String nomDept, String salaire) {

        Query query = em.createQuery("SELECT d FROM Departement d JOIN d.employeList e WHERE e.sal>=:sal AND d.deptName=:nomdt ");
        query.setParameter("nomdt", nomDept);
        query.setParameter("sal", salaire);
        try {
            List<Employe> emp = query.getResultList();

            return emp;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
