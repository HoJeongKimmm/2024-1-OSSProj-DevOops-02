package com.example.demo.repository;

import com.example.demo.domain.Invitation;
import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class InvitationRepositoryImpl implements InvitationRepository{
    private EntityManager em;
    public InvitationRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public void insert(Project project, User user){
        Invitation invitation = Invitation.builder()
                .project(project)
                .user(user)
                .state("초대됨")
                .build();
        em.persist(invitation);
    }
    @Override
    public Invitation findById(Long invitation_id){
        String sql = "select invitation from Invitation invitation where id = :invitation_id";
        TypedQuery<Invitation> query = em.createQuery(sql, Invitation.class);
        query.setParameter("invitation_id", invitation_id);
        List<Invitation> list = query.getResultList();
        for (Invitation entity : list) {
            return entity; 
        }
        return null;
    }
    @Override
    public List<Project> findInviteProjectList(String user_id){
        String sql = "select invitation.project from Invitation invitation where user = :user_id and state=:state";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("user_id", user_id);
        query.setParameter("state","PENDING");
        List<Project> list = query.getResultList();
        return list;
    }
    @Override
    public void updateState(Long invitation_id){
        Invitation invitation = findById(invitation_id);
        invitation.setState("APPROVE");
        em.persist(invitation);
    }

    @Override
    public Long findIdByUser_id(String user_id){
        String sql = "select invitation from Invitation invitation where user = :user_id";
        TypedQuery<Invitation> query = em.createQuery(sql, Invitation.class);
        query.setParameter("user_id", user_id);
        List<Invitation> list = query.getResultList();
        for (Invitation entity : list) {
            return entity.getId();
        }
        return null;
    }

}
