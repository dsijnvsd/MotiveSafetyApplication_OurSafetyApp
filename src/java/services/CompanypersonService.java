/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CompanypersonDB;
import domain.Company;
import domain.Companyperson;
import domain.Companypersonaddress;
import domain.Companypersonphone;
import domain.Companypositions;
import domain.Person;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class CompanypersonService.
 *
 * @author Chelsey Coughlin
 */
public class CompanypersonService {
    
    /**
     * Update.
     *
     * @param personID the person ID
     * @param email the email
     * @throws Exception the exception
     */
    public void update(Companyperson personID, String email) throws Exception {
        CompanypersonDB compPerDB = new CompanypersonDB();
        Companyperson compPersEdit = compPerDB.get(personID.getCompanyPersonID());
        compPersEdit.setEmail(email);
        compPerDB.update(compPersEdit);
    }
        
    /**
     * Insert.
     *
     * @param userAdded the user added
     * @param email the email
     * @param isEmployeeActive the is employee active
     * @param companyID the company ID
     * @param personID the person ID
     * @return the companyperson
     * @throws Exception the exception
     */
    public Companyperson insert(Integer userAdded, String email, boolean isEmployeeActive, Company companyID, Person personID) throws Exception {
        CompanypersonDB addDB = new CompanypersonDB();
        
        Date dateAdded = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String tempDate = formatter.format(dateAdded);
        /*Formats the created date*/
        dateAdded = new SimpleDateFormat("yyyy-MM-dd").parse(tempDate);

        Companyperson add = new Companyperson(dateAdded, userAdded, email, isEmployeeActive, companyID, personID);
        addDB.insert(add);
        return add;
    }

    /**
     * Link.
     *
     * @param personID the person ID
     * @param email the email
     * @throws Exception the exception
     */
    public void link(Companyperson personID, String email) throws Exception {
        CompanypersonDB compPerDB = new CompanypersonDB();
        Companyperson compPersEdit = compPerDB.get(personID.getCompanyPersonID());
        compPersEdit.setEmail(email);
        compPerDB.update(compPersEdit);
    }
}
