/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import domain.Item;
import domain.Itemclass;
import domain.Itemclassfields;
import domain.Company;
import javax.persistence.Query;


/**
 *
 * @author Chelsey Coughlin
 */
public class ItemDB {
    
    /**
     *
     * @param companyID takes in company person id
     * @return Item returns an item
     * @throws Exception throws an exception
     */
    public List<Item> getAll(Company companyID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Company company = em.find(Company.class, companyID.getCompanyID());
            return company.getItemList();
        } finally {
            em.close();
        }
    }
   
    /**
     *
     * @param item_ID takes in item id
     * @return Item returns an item
     * @throws Exception throws an exception
     */
    public Item get(int item_ID) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Item item = em.find(Item.class, item_ID);
            return item;
        } finally { 
            em.close();
        }
    }
    
    /**
     *
     * @param item takes in item
     * @throws Exception throws an exception
     */
    public void insert(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Company user = item.getCompanyID();
            user.getItemList().add(item);
            trans.begin();
            em.persist(item);
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param item takes in item
     * @throws Exception throws an exception
     */
    public void update(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            trans.begin();
            em.merge(item);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }

    /**
     *
     * @param item takes in item
     * @throws Exception throws an exception
     */
    public void delete(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            Company user = item.getCompanyID();
            user.getItemList().remove(item);
            trans.begin();
            em.remove(em.merge(item));
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
