package com.dinin.core.db.dao;

import com.dinin.core.db.entity.Comment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * @param cId 餐厅id
     * @param uId 用户id
     * @return
     */
    @Query(value = "SELECT * FROM comment WHERE c_id=?1 AND u_id=?2", nativeQuery = true)
    Comment findByCIdAndUId(Integer cId, Integer uId);

    @Modifying
    @Transactional
    @Query(value = "delete from Comment where u_id=?1", nativeQuery = true)
    void deleteAllByUId(Integer uid);

}
