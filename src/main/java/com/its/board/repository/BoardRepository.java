package com.its.board.repository;

import com.its.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    //update board_table set board_hits = board_hits+1 where id=?
    @Modifying
//    @Query("update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
//    int updateView(Long id);
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id =:id")
//    @Query(value = "update board_table set board_hits=board_hits+1 where id =:id",nativeQuery = true)
    int updateHits(@Param("id") Long id);


}
