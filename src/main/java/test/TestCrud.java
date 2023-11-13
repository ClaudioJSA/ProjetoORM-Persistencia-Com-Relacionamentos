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
import user.User;
import user.UserDao;


/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class TestCrud {
    Credential credential = new Credential();
    Librarian librarian = new Librarian();
    
    public static void testeA() {
        try{
            Credential credential = new Credential();
            Librarian librarian = new Librarian();
            ///Inserirndo dados no banco
            ///Carregando dados da credencial
            credential.setUsername("usertesttg5j");
            credential.setPassword("123456");
            credential.setLastAccess(LocalDate.of(0000, 1, 1));
            ///Carregando dados do usuario
            librarian.setName("Claudio Alcantara");
            librarian.setEmail("claudio@mail.com");
            librarian.setBirthDate(LocalDate.of(1999, Month.AUGUST, 25));
            librarian.setCredential(credential);
            ///Fererenciando librarian em credential
            credential.setUser(librarian);
            ///Salvando credential no banco
            credential.setId(new CredentialDao().saveOrUpdate(credential));
            if(credential.getId() != 0){
                ///Salvando librarian no banco
                librarian.setId(new UserDao().saveOrUpdate(librarian));
                ///Atualizando a referencia de librarian em credencial no banco
                new CredentialDao().saveOrUpdate(credential);
                ///Adicionando User na tabela librarian
                new LibrarianDao().saveOrUpdate(librarian);
                ///Atualizando o email
                librarian.setEmail("claudioalcantara@mail.com");
                new UserDao().saveOrUpdate(librarian);
                ///Atualizando o ultimo acesso para encerramento de sessão
                credential.setLastAccess(LocalDate.now());
                new CredentialDao().saveOrUpdate(credential);
                ///Resentando credencial e librarian
                librarian = null;
                credential = null;
                ///Recuperando do banco de dados
                credential = new Credential("usertesttg5","123456");
                librarian = new Librarian((User)new CredentialDao().authenticate(credential));
                librarian.setCredential(credential);
                System.out.println(">> "+librarian);
                System.out.println(">> "+credential);
            }
            
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
        
        
        
    
        
    
}
