package com.its.board.dto;

import com.its.board.entity.BoardEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardDTO {
    // boardDTO 정의
    private Long id;
    private String boardTitle;
    private String boardContents;
    private String boardWriter;
    private String boardPass;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;
    private int boardHits;

    private MultipartFile boardFile;
    private int fileAttached;
    private String originalFileName;
    private String storedFileName;

    public static BoardDTO toDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());

        // 파일 관련된 내용 추가
        if(boardEntity.getFileAttached() == 1){
            //첨부파일 있음
            boardDTO.setFileAttached(boardEntity.getFileAttached()); //1
            //첨부파일 이름 가져옴
            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFilename());
        }else {
            boardDTO.setFileAttached(boardEntity.getFileAttached()); //0
        }

        return boardDTO;
    }




}
