package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.entity.BoardFileEntity;
import com.its.board.repository.BoardFileRepository;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    public Long save(BoardDTO boardDTO) throws IOException {
        if(boardDTO.getBoardFile().isEmpty()){
            System.out.println("파일없음");
            return boardRepository.save(BoardEntity.toSaveEntity(boardDTO)).getId();
        }else {
            System.out.println("파일있음");
            MultipartFile boardFile = boardDTO.getBoardFile();
            String originalFileName = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis()+"-"+ originalFileName;
            String savePath = "D:\\springboot_img\\"+storedFileName;
            boardFile.transferTo(new File(savePath));

            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long savedId = boardRepository.save(boardEntity).getId();

            BoardEntity entity = boardRepository.findById(savedId).get();
            BoardFileEntity boardFileEntity = BoardFileEntity.toSaveBoardFileEntity(entity,originalFileName,storedFileName);
            boardFileRepository.save(boardFileEntity);
            return savedId;

        }

    }

    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(BoardDTO.toDTO(boardEntity));
        }
        return boardDTOList;
    }

    @Transactional //부모엔티티에서 자식엔티티를 직접 가져올때 필요
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            return BoardDTO.toDTO(optionalBoardEntity.get());
        } else {
            return null;
        }

    }

    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateHits(id);
    }

    public void update(BoardDTO boardDTO) {
        BoardEntity updateEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(updateEntity);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
