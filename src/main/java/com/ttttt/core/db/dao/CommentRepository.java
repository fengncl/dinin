package com.ttttt.core.db.dao;

import com.ttttt.core.db.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * @param cId 餐厅id
     * @param uId 用户id
     * @return
     */
//    @Query(value = "SELECT * FROM comment WHERE c_id = ?1 AND u_id = ?2", nativeQuery = true)
//    Comment findByCIdAndUId(Integer cId, Integer uId);
    Comment findByCIdAndUId(Integer cId, Integer uId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Comment c WHERE c.uId = :uid")
    void deleteAllByUId(Integer uid);

    //    @Modifying
//    @Transactional
//    @Query("DELETE FROM Comment c WHERE c.uId = :uid")
    List<Comment> findByUId(Integer uid);


}
