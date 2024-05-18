package com.example.demo.repository;

import com.example.demo.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

@Repository
public class ProjectRepositoryImpl implements ProjectRepository {
    private final EntityManager em;

    @Qualifier("projectRepository")
    @Autowired
    private ProjectRepository project_rp;

    public ProjectRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Project insert(Project project) {
        em.persist(project);
        return project;
    }

    @Override
    public List<Project> findPopularProject() {
        List<Project> allProjects = project_rp.findAll();
        Map<Integer, Integer> popularMap = new HashMap<>();

        for (Project project : allProjects) {
            popularMap.put(project.getId(), project.getLike_count() * 5 + project.getVisited_number());
        }

        List<Integer> keySet = new ArrayList<>(popularMap.keySet());
        keySet.sort((o1, o2) -> popularMap.get(o2).compareTo(popularMap.get(o1)));

        List<Project> popularProjectList = new ArrayList<>();
        for (Integer projectId : keySet) {
            popularProjectList.add(project_rp.findByProjectId(projectId));
        }

        return popularProjectList;
    }

    @Override
    public Project findByProjectId(int projectId) {
        String sql = "select project from Project project where project.id = :projectId";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("projectId", projectId);
        return query.getResultStream().findFirst().orElse(null);
    }

    @Override
    public int delete(int projectId) {
        Project project = findByProjectId(projectId);
        if (project != null) {
            em.remove(project);
            return 1;
        }
        return 0;
    }

    @Override
    public int getLike_count(int projectId) {
        Project project = findByProjectId(projectId);
        return project != null ? project.getLike_count() : 0;
    }

    @Override
    public List<Project> findAllProjectList() {
        String sql = "select project from Project project";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        return query.getResultList();
    }

    @Override
    public int getVisited_number(int projectId) {
        Project project = findByProjectId(projectId);
        return project != null ? project.getVisited_number() : 0;
    }

    @Override
    public List<Project> findEndProjects(String userId) {
        String sql = "select project from Project project where project.id = :userId and project.is_available = :isAvailable";
        TypedQuery<Project> query = em.createQuery(sql, Project.class);
        query.setParameter("userId", userId);
        query.setParameter("isAvailable", "EXPIRED");
        return query.getResultList();
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
        String sql = "select count(project) from Project project";
        TypedQuery<Long> query = em.createQuery(sql, Long.class);
        return query.getSingleResult();
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
        for (Project entity : entities) {
            delete(entity);
        }
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
        // Implementation for Example query count
        String jpql = "select count(project) from Project project where :example";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("example", example.getProbe());
        return query.getSingleResult();
    }

    @Override
    public <S extends Project> boolean exists(Example<S> example) {
        // Implementation for Example query exists
        return findOne(example).isPresent();
    }

    @Override
    public <S extends Project, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return queryFunction.apply(new FluentQuery.FetchableFluentQuery<S>() {
            @Override
            public FetchableFluentQuery<S> sortBy(Sort sort) {
                return null;
            }

            @Override
            public <R> FetchableFluentQuery<R> as(Class<R> resultType) {
                return null;
            }

            @Override
            public FetchableFluentQuery<S> project(Collection<String> properties) {
                return null;
            }

            @Override
            public Optional<S> one() {
                TypedQuery<S> query = em.createQuery("select project from Project project where :example = project", (Class<S>) example.getProbeType());
                query.setParameter("example", example.getProbe());
                return Optional.ofNullable(query.getResultStream().findFirst().orElse(null));
            }

            @Override
            public S oneValue() {
                return null;
            }

            @Override
            public S firstValue() {
                return null;
            }

            @Override
            public List<S> all() {
                TypedQuery<S> query = em.createQuery("select project from Project project where :example = project", (Class<S>) example.getProbeType());
                query.setParameter("example", example.getProbe());
                return query.getResultList();
            }

            @Override
            public long count() {
                TypedQuery<Long> query = em.createQuery("select count(project) from Project project where :example = project", Long.class);
                query.setParameter("example", example.getProbe());
                return query.getSingleResult();
            }

            @Override
            public boolean exists() {
                return !one().isEmpty();
            }

            @Override
            public Page<S> page(Pageable pageable) {
                TypedQuery<S> query = em.createQuery("select project from Project project where :example = project", (Class<S>) example.getProbeType());
                query.setParameter("example", example.getProbe());
                int totalRows = query.getResultList().size();
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
                List<S> content = query.getResultList();
                return new PageImpl<>(content, pageable, totalRows);
            }

            @Override
            public Stream<S> stream() {
                return null;
            }
        });
    }
}

