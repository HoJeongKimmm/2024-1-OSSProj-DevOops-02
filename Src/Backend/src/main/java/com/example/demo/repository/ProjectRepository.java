package com.example.demo.repository;

import com.example.demo.domain.Project;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project insert(Project project);

    // 사용자 정의 메서드 선언
    List<Project> findPopularProject();

    Project findByProjectId(int projectId);

    int delete(int projectId);

    int getLike_count(int projectId);

    List<Project> findAllProjectList();

    int getVisited_number(int projectId);

    List<Project> findEndProjects(String userId);

    @Override
    List<Project> findAll();

    @Override
    List<Project> findAll(Sort sort);

    @Override
    Page<Project> findAll(Pageable pageable);

    @Override
    List<Project> findAllById(Iterable<Integer> ids);

    @Override
    long count();

    @Override
    void deleteById(Integer id);

    @Override
    void delete(Project entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> ids);

    @Override
    void deleteAll(Iterable<? extends Project> entities);

    @Override
    void deleteAll();

    @Override
    <S extends Project> S save(S entity);

    @Override
    <S extends Project> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Project> findById(Integer id);

    @Override
    boolean existsById(Integer id);

    @Override
    void flush();

    @Override
    <S extends Project> S saveAndFlush(S entity);

    @Override
    <S extends Project> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<Project> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Integer> ids);

    @Override
    void deleteAllInBatch();

    @Override
    Project getOne(Integer id);

    @Override
    Project getById(Integer id);

    @Override
    Project getReferenceById(Integer id);

    @Override
    <S extends Project> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Project> List<S> findAll(Example<S> example);

    @Override
    <S extends Project> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Project> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Project> long count(Example<S> example);

    @Override
    <S extends Project> boolean exists(Example<S> example);

    @Override
    <S extends Project, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}


