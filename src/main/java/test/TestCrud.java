/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import credential.Credential;
import credential.CredentialDao;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import librarian.Librarian;
import librarian.LibrarianDao;
import reader.Reader;
import reader.ReaderDao;
import repository.DbConnection;
import role.Role;
import role.RoleDao;
import user.User;
import user.UserDao;


/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class TestCrud {
    
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
            librarian.setRole(new Role("Bibliotecario"));
            librarian.getRole().setId(new RoleDao().saveOrUpdate(librarian.getRole()));
            System.out.println(librarian.getRole().getId());
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
                credential = new Credential("usertesttg5j","123456");
                librarian = new Librarian((User)new CredentialDao().authenticate(credential));
                librarian.setCredential(credential);
                System.out.println(">> "+librarian);
                System.out.println(">> "+credential);
            }
            
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
    
    public static void testeB() {
        try{
            Credential credential = new Credential();
            Reader reader = new Reader();
            ///Inserirndo dados no banco
            ///Carregando dados da credencial
            credential.setUsername("testReader1");
            credential.setPassword("12321");
            credential.setLastAccess(LocalDate.of(0000, 1, 1));
            ///Carregando dados do usuario
            reader.setName("Ana Zaira");
            reader.setEmail("ana@mail.com");
            reader.setBirthDate(LocalDate.of(1990, Month.OCTOBER, 10));
            reader.setCredential(credential);
            reader.setRole(new Role("Leitor"));
            reader.getRole().setId(new RoleDao().saveOrUpdate(reader.getRole()));
            ///Fererenciando librarian em credential
            credential.setUser(reader);
            ///Salvando credential no banco
            credential.setId(new CredentialDao().saveOrUpdate(credential));
            if(credential.getId() != 0){
                ///Salvando librarian no banco
                reader.setId(new UserDao().saveOrUpdate(reader));
                ///Atualizando a referencia de librarian em credencial no banco
                new CredentialDao().saveOrUpdate(credential);
                ///Adicionando User na tabela librarian
                new ReaderDao().saveOrUpdate(reader);
                ///Atualizando o email
                reader.setEmail("anazaira@mail.com");
                new UserDao().saveOrUpdate(reader);
                ///Atualizando o ultimo acesso para encerramento de sessão
                credential.setLastAccess(LocalDate.now());
                new CredentialDao().saveOrUpdate(credential);
                ///Resentando credencial e librarian
                reader = null;
                credential = null;
                ///Recuperando do banco de dados
                credential = new Credential("testReader1","12321");
                reader = new Reader((User)new CredentialDao().authenticate(credential));
                reader.setCredential(credential);
                System.out.println(">> Leitor: "+reader);
                System.out.println(">> Credentcial: "+credential);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
        
    public static void testeC() {
        try{
            Credential credential = new Credential();
            Reader reader = new Reader();
            ///Inserirndo dados no banco
            ///Carregando dados da credencial
            credential.setUsername("testReader2");
            credential.setPassword("12321456");
            credential.setLastAccess(LocalDate.of(0000, 1, 1));
            ///Carregando dados do usuario
            reader.setName("Bruno Marcos");
            reader.setEmail("bruno@mail.com");
            reader.setBirthDate(LocalDate.of(200, Month.JANUARY, 17));
            reader.setCredential(credential);
            reader.setRole(new Role("Leitor"));
            reader.getRole().setId(new RoleDao().saveOrUpdate(reader.getRole()));
            ///Fererenciando librarian em credential
            credential.setUser(reader);
            ///Salvando credential no banco
            credential.setId(new CredentialDao().saveOrUpdate(credential));
            if(credential.getId() != 0){
                ///Salvando librarian no banco
                reader.setId(new UserDao().saveOrUpdate(reader));
                ///Atualizando a referencia de librarian em credencial no banco
                new CredentialDao().saveOrUpdate(credential);
                ///Adicionando User na tabela librarian
                new ReaderDao().saveOrUpdate(reader);
                ///Atualizando o email
                reader.setEmail("mascosbruno@mail.com");
                new UserDao().saveOrUpdate(reader);
                ///Atualizando o ultimo acesso para encerramento de sessão
                credential.setLastAccess(LocalDate.now());
                new CredentialDao().saveOrUpdate(credential);
                ///Resentando credencial e librarian
                reader = null;
                credential = null;
                ///Recuperando do banco de dados
                credential = new Credential("testReader2","12321456");
                reader = new Reader((User)new CredentialDao().authenticate(credential));
                reader.setCredential(credential);
                System.out.println(">> Leitor: "+reader);
                System.out.println(">> Credentcial: "+credential);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
        
    public static void testeD(){
        List<Librarian> librarians;
        
        librarians = new LibrarianDao().findAll();
        for(int i=0;i<librarians.size();i++){
            librarians.set(i, new Librarian(new UserDao().findById(librarians.get(i).getId())));
        }
        System.out.println(">> "+librarians);
    }
    
    public static void testeE(){
        List<Reader> readers;
        
        readers = new ReaderDao().findAll();
        for(int i=0;i<readers.size();i++){
            readers.set(i, new Reader(new UserDao().findById(readers.get(i).getId())));
        }
        System.out.println(">> "+readers);
    }
    
    public static void testeF(){
        
    }
}


