package com.example.demo.repository;

import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectLike;
import com.example.demo.domain.User;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ProjectLikeRepositoryImpl implements ProjectLikeRepository {
    private EntityManager em;
    public ProjectLikeRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    public ProjectLike insert(Project project, User user){
        ProjectLike projectLike = ProjectLike.builder()
                .project(project)
                .user(user)
                .build();
        em.persist(projectLike);
        return projectLike;
    }

    @Override
    public List<Project> findAll() {
        return em.createQuery("SELECT p FROM Project p", Project.class)
                .getResultList();
    }


    @Override
    public List<Project> findAll(Sort sort) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> query = cb.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        query.select(root);
        query.orderBy(sort.getOrderFor("name").getDirection() == Sort.Direction.ASC
                ? cb.asc(root.get("name"))
                : cb.desc(root.get("name")));
        return em.createQuery(query).getResultList();
    }


    @Override
    public Page<Project> findAll(Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> query = cb.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        query.select(root);
        TypedQuery<Project> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());
        List<Project> projects = typedQuery.getResultList();
        long total = count(); // 전체 프로젝트 개수 조회
        return new PageImpl<>(projects, pageable, total);
    }


    @Override
    public List<Project> findAllById(Iterable<Integer> integers) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Project> query = cb.createQuery(Project.class);
        Root<Project> root = query.from(Project.class);
        query.select(root);
        query.where(root.get("id").in(integers));
        return em.createQuery(query).getResultList();
    }


    @Override
    public long count() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Project> root = query.from(Project.class);
        query.select(cb.count(root));
        return em.createQuery(query).getSingleResult();
    }


    @Override
    public void deleteById(Integer integer) {
        Project project = em.find(Project.class, integer);
        if (project != null) {
            em.remove(project);
        }
    }


    @Override
    public void delete(Project entity) {
        em.remove(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {
        for (Integer id : integers) {
            deleteById(id);
        }
    }


    @Override
    public void deleteAll(Iterable<? extends Project> entities) {
        for (Project entity : entities) {
            delete(entity);
        }
    }

    @Override
    public void deleteAll() {
        Query query = em.createQuery("DELETE FROM Project");
        query.executeUpdate();
    }


    @Override
    public <S extends Project> S save(S entity) {
        if (entity == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        return entity;
    }

    @Override
    public <S extends Project> List<S> saveAll(Iterable<S> entities) {
        List<S> savedEntities = new ArrayList<>();
        for (S entity : entities) {
            savedEntities.add(save(entity));
        }
        return savedEntities;
    }

    @Override
    public Optional<Project> findById(Integer id) {
        Project project = em.find(Project.class, id);
        return project != null ? Optional.of(project) : Optional.empty();
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Project> S saveAndFlush(S entity) {
        S saved = save(entity);
        em.flush();
        return saved;
    }

    @Override
    public <S extends Project> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Project> entities) {
        for (Project entity : entities) {
            em.remove(em.contains(entity) ? entity : em.merge(entity));
        }
        em.flush();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        Query query = em.createQuery("DELETE FROM Project p WHERE p.id IN :ids");
        query.setParameter("ids", ids);
        query.executeUpdate();
    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Project getOne(Integer integer) {
        return null;
    }

    @Override
    public Project getById(Integer integer) {
        return null;
    }

    @Override
    public Project getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Project> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Project> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Project> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Project, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
