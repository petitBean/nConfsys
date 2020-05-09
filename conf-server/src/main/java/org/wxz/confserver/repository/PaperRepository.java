package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.paper.Paper;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/5/6 15:20
 */
@Repository
public interface PaperRepository extends JpaRepository<Paper,String> {

List<Paper> findAllByConfId(String confId);

List<Paper> findAllByConfIdAndStatus(String confId,int status);

List<Paper> findAllByConfIdAndProfessorUserNameAndFirstStatus(String confId,String professorUserName,int firstStatus);

List<Paper> findAllByConfIdAndProfessorUserNameSecondAndSecondStatus(String confId,String professorUserNameSecond,int secondStatus);

Paper findByPaperId(String paperId);

Paper findByConfIdAndAuthorUserName(String confId,String authorUserName);
}
