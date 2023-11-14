/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import user.User;

/**
 *
 * @author Claudio Alcantara &lt;claudio.alcantara at ifnmg.edi.br&gt;
 */
public class Reader extends User{

    public Reader() {
    }
    
    public Reader(User user){
        try{
            setName(user.getName());
            setEmail(user.getEmail());
            setBirthDate(user.getBirthDate());
            setCredential(user.getCredentical());
            setRole(user.getRole());
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
