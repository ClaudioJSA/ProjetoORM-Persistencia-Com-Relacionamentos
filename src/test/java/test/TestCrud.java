/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import credential.Credential;
import credential.CredentialDao;
import java.time.LocalDate;
import java.time.Month;
import librarian.Librarian;
import librarian.LibrarianDao;


/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class TestCrud {
    Credential credential = new Credential();
    Librarian librarian = new Librarian();
    
    public void testeA() {
        try{
            credential.setUsername("usertest");
            credential.setPassword("123456");
            
            librarian.setName("Claudio Alcantara");
            librarian.setEmail("claudio@mail.com");
            librarian.setBirthDate(LocalDate.of(1999, Month.AUGUST, 25));
            librarian.setCredentical(credential);
            
            credential.setUser(librarian);
            credential.setId(new CredentialDao().saveOrUpdate(credential));

            librarian.setId(new LibrarianDao().saveOrUpdate(librarian));
            
            credential.setId(new CredentialDao().saveOrUpdate(credential));
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
        
        
        
    
        
    
}
