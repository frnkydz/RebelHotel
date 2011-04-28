// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.unlv.cs.rebelhotel.domain;

import edu.unlv.cs.rebelhotel.domain.UserAccount;
import java.lang.String;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect UserAccount_Roo_Finder {
    
    public static TypedQuery<UserAccount> UserAccount.findUserAccountsByUserId(String userId) {
        if (userId == null || userId.length() == 0) throw new IllegalArgumentException("The userId argument is required");
        EntityManager em = UserAccount.entityManager();
        TypedQuery<UserAccount> q = em.createQuery("SELECT UserAccount FROM UserAccount AS useraccount WHERE useraccount.userId = :userId", UserAccount.class);
        q.setParameter("userId", userId);
        return q;
    }
    
    public static TypedQuery<UserAccount> UserAccount.findUserAccountsByEmailLike(String email) {
        if (email == null || email.length() == 0) throw new IllegalArgumentException("The email argument is required");
        email = email.replace('*', '%');
        if (email.charAt(0) != '%') {
            email = "%" + email;
        }
        if (email.charAt(email.length() -1) != '%') {
            email = email + "%";
        }
        EntityManager em = UserAccount.entityManager();
        TypedQuery<UserAccount> q = em.createQuery("SELECT UserAccount FROM UserAccount AS useraccount WHERE LOWER(useraccount.email) LIKE LOWER(:email)", UserAccount.class);
        q.setParameter("email", email);
        return q;
    }
    
    public static TypedQuery<UserAccount> UserAccount.findUserAccountsByUserIdEquals(String userId) {
        if (userId == null || userId.length() == 0) throw new IllegalArgumentException("The userId argument is required");
        EntityManager em = UserAccount.entityManager();
        TypedQuery<UserAccount> q = em.createQuery("SELECT UserAccount FROM UserAccount AS useraccount WHERE useraccount.userId = :userId", UserAccount.class);
        q.setParameter("userId", userId);
        return q;
    }
    
}
