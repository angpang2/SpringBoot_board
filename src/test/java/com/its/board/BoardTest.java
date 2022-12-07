package com.its.board;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService boardService;
    @Test
    @Transactional
    @Rollback
    @DisplayName("글작성 테스트")
    public void boardSaveTest(){
        BoardDTO boardDTO = newBoard();
        Long saveId=boardService.save(boardDTO);
        BoardDTO boardDTO1 = boardService.findById(saveId);
        assertThat(boardDTO.getBoardTitle()).isEqualTo(boardDTO1.getBoardTitle());
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("글목록 테스트")
    public void boardListTest(){
        BoardDTO boardDTO = newBoard();
        boardService.save(boardDTO);
        List<BoardDTO> boardDTOList = boardService.findAll();
        assertThat(boardDTOList.size()).isEqualTo(boardDTOList.size());
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("글상세보기 테스트")
    public void boardDetailTest(){
        BoardDTO boardDTO = newBoard();
        Long saveId=boardService.save(boardDTO);
        BoardDTO boardDTO1 = boardService.findById(saveId);
        assertThat(boardDTO.getBoardTitle()).isEqualTo(boardDTO1.getBoardTitle());

    }
    private BoardDTO newBoard() {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("testTitle");
        boardDTO.setBoardContents("testContents");
        boardDTO.setBoardWriter("testWriter");
        boardDTO.setBoardPass("testPass");
        return boardDTO;
    }

//    private BoardDTO newBoard(int i) {
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setBoardTitle("testTitle"+i);
//        boardDTO.setBoardContents("testContents"+i);
//        boardDTO.setBoardWriter("testWriter"+i);
//        boardDTO.setBoardPass("testPass"+i);
//        return boardDTO;
//    }

//    @Test
//    @Transactional
//    @Rollback
//    @DisplayName("글상세보기 테스트")
//    public void saveList(){
//      for(int i=1; i<=20; i++){
//          boardService.save(newBoard(i));
//        }
//        IntStream.rangeClosed(21,40).forEach(i -> {
//            boardService.save(newBoard(i));
//        });
//
//    }

}
