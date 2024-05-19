package com.example.demo.repository;

import com.example.demo.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.function.Function;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private EntityManager em;
    ProjectRepository project_rp;
    public ProjectRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Project insert(Project project) {
        em.persist(project);
        return project;
    }

    @Override
    //@Autowired
    public List<Project> findPopularProject() {
        List<Project> allProjects = project_rp.findAll();
        Map<Integer, Integer> popular_map = new HashMap<Integer, Integer>();

        for (Project project : allProjects) {
            popular_map.put(project.getId(), (project.getLike_count() * 5 + project.getVisited_number()));
        }

        List<Integer> keySet = new ArrayList<>(popular_map.keySet());
        keySet.sort((o1, o2) -> popular_map.get(o2).compareTo(popular_map.get(o1)));

        List<Project> popular_project_list = new ArrayList<>();
        int num = 0;
        for (Integer project_id : keySet) {
            popular_project_list.add(project_rp.findByProjectId(project_id));
        }

        return popular_project_list;


    }


    @Override
    public Project findByProjectId(int project_id) {
        String sql = "select project from Project project where id = :project_id";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("project_id", project_id);
        List<Project> list = query.getResultList();
        for (Project entity : list) {
            return entity;
        }
        return null;
    }

    @Override
    public int delete(int project_id){
        Project project = findByProjectId(project_id);
        em.remove(project);
        Project removed_project = findByProjectId(project_id);
        if (removed_project == null) return 1;
        else return 0;
    }

    @Override
    public int getLike_count(int project_id) {
        Project project = findByProjectId(project_id);
        return project != null ? project.getLike_count() : 0;
    }


    public List<Project> findAllProjectList() {
        String sql = "select project from Project project";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        List<Project> list = query.getResultList();
        return list;
    }


    @Override
    public int getVisited_number(int project_id) {
        Project project = findByProjectId(project_id);
        return project != null ? project.getVisited_number() : 0;
    }

    @Override
    public List<Project> findEndProjects(String user_id){
        String sql = "select project from Project project where project = :user_id and project.is_available = :is_available";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("user_id", user_id);
        query.setParameter("is_available", "EXPIRED");
        List<Project> list = query.getResultList();
        return list;
    }

    @Override
    public List<Project> findAll() {
        String sql = "select project from Project project";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        return query.getResultList();
    }

    @Override
    public List<Project> findAll(Sort sort) {
        String sql = "select project from Project project order by " + sort.toString().replace(":", "");
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        return query.getResultList();
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        String sql = "select project from Project project";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<Project> projects = query.getResultList();

        String countSql = "select count(project) from Project project";
        TypedQuery<Long> countQuery = em.createQuery(countSql, Long.class);
        long total = countQuery.getSingleResult();

        return new PageImpl<>(projects, pageable, total);
    }

    @Override
    public List<Project> findAllById(Iterable<Integer> ids) {
        String sql = "select project from Project project where project.id in :ids";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    public long count() {
        //아직 구현 못함
        return 0;
    }

    @Override
    public void deleteById(Integer id) {
        Project project = findByProjectId(id);
        if (project != null) {
            em.remove(project);
        }
    }

    @Override
    public void delete(Project entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }


    @Override
    public void deleteAllById(Iterable<? extends Integer> ids) {
        for (Integer id : ids) {
            deleteById(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Project> entities) {

    }

    @Override
    public void deleteAll() {
        String sql = "delete from Project project";
        em.createQuery(sql).executeUpdate();
    }

    @Override
    public <S extends Project> S save(S entity) {
        if (entity == null ) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    @Override
    public <S extends Project> List<S> saveAll(Iterable<S> entities) {
        List<S> result = new ArrayList<>();
        for (S entity : entities) {
            result.add(save(entity));
        }
        return result;
    }

    @Override
    public Optional<Project> findById(Integer id) {
        return Optional.ofNullable(em.find(Project.class, id));
    }

    @Override
    public boolean existsById(Integer id) {
        return findById(id).isPresent();
    }

    @Override
    public void flush() {
        em.flush();
    }

    @Override
    public <S extends Project> S saveAndFlush(S entity) {
        S savedEntity = save(entity);
        flush();
        return savedEntity;
    }

    @Override
    public <S extends Project> List<S> saveAllAndFlush(Iterable<S> entities) {
        List<S> result = saveAll(entities);
        flush();
        return result;
    }

    @Override
    public void deleteAllInBatch(Iterable<Project> entities) {
        entities.forEach(em::remove);
        flush();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> ids) {
        ids.forEach(this::deleteById);
        flush();
    }

    @Override
    public void deleteAllInBatch() {
        deleteAll();
        flush();
    }

    @Override
    public Project getOne(Integer id) {
        return em.getReference(Project.class, id);
    }

    @Override
    public Project getById(Integer id) {
        return findById(id).orElse(null);
    }

    @Override
    public Project getReferenceById(Integer id) {
        return em.getReference(Project.class, id);
    }


    @Override
    public <S extends Project> Optional<S> findOne(Example<S> example) {
        // Implementation for Example query
        TypedQuery<S> query = em.createQuery("select project from Project project where :example", (Class<S>) example.getProbeType());
        query.setParameter("example", example.getProbe());
        return query.getResultStream().findFirst();
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example) {
        // Implementation for Example query
        TypedQuery<S> query = em.createQuery("select project from Project project where :example", (Class<S>) example.getProbeType());
        query.setParameter("example", example.getProbe());
        return query.getResultList();
    }

    @Override
    public <S extends Project> List<S> findAll(Example<S> example, Sort sort) {
        // Implementation for Example query with sort
        String jpql = "select project from Project project where :example order by " + sort.toString().replace(":", "");
        TypedQuery<S> query = em.createQuery(jpql, (Class<S>) example.getProbeType());
        query.setParameter("example", example.getProbe());
        return query.getResultList();
    }

    @Override
    public <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable) {
        // Implementation for Example query with pageable
        String jpql = "select project from Project project where :example";
        TypedQuery<S> query = em.createQuery(jpql, (Class<S>) example.getProbeType());
        query.setParameter("example", example.getProbe());
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        List<S> result = query.getResultList();

        String countJpql = "select count(project) from Project project where :example";
        TypedQuery<Long> countQuery = em.createQuery(countJpql, Long.class);
        countQuery.setParameter("example", example.getProbe());
        long total = countQuery.getSingleResult();

        return new PageImpl<>(result, pageable, total);
    }

    @Override
    public <S extends Project> long count(Example<S> example) {
        // 구현 미완
        return 0;
    }

    @Override
    public <S extends Project> boolean exists(Example<S> example) {
        // Implementation for Example query exists
        return findOne(example).isPresent();
    }

    @Override
    public <S extends Project, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {

        return null;
    }
}