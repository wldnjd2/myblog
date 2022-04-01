package kr.co.negga.ntb.board.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.negga.ntb.board.service.BoardVO;
/**
 * @Class BoardMapper.java
 * @Description 게시판 관련 Mapper Class(mybatis)
 * @Since 2022.03.16
 * @author hsan
 * @version 1.0
 * @Modification 수정사항 없음 
 */
@Mapper("boardMapper")
public class BoardMapper extends EgovAbstractMapper {

	/**
	 * 게시판 리스트를 불러온다.
	 * @param vo 게시판 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public List<BoardVO> selectList(BoardVO vo) throws Exception {
		return selectList("selectList", vo);
	}

	/**
	 * 게시판 총 게시물 갯수를 불러온다.
	 * @param vo 게시판 정보가 담긴 BoardVO
	 * @return int 총 게시물 갯수
	 * @exception Exception
	 */
	public int selectListCnt(BoardVO vo) throws Exception {
		return selectOne("selectListCnt", vo);
	}

	/**
	 * 게시판 카테고리를 불러온다.
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return List 총 카테고리 갯수
	 * @exception Exception
	 */
	public List<BoardVO> selectCategory(BoardVO vo) throws Exception {
		return selectList("selectCategory", vo);
	}

	/**
	 * 게시글 한개를 가져온다.
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public BoardVO selectBoardDetail(BoardVO vo) throws Exception {
		return selectOne("selectBoardDetail", vo);
	}

	/**
	 * 게시판 게시글 수정
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int updateBoardDetail(BoardVO vo) throws Exception {
		return update("updateBoardDetail", vo);
	}
	
	/**
	 * 게시판 게시글 삭제
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int deleteBoardDetail(BoardVO vo) throws Exception{
		return delete("deleteBoardDetail", vo);
	}

	/**
	 * 댓글달기
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int insertBoardReply(BoardVO vo) throws Exception {
		return insert("insertBoardReply", vo);
	}
	
	/**
	 * 댓글 불러오기
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public List<BoardVO> selectBoardReplyList(BoardVO vo) throws Exception {
		return selectList("selectBoardReplyList", vo);
	}

	/**
	 * 댓글 수정한다
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int updateBoardReplyList(BoardVO vo) throws Exception {
		return update("updateBoardReplyList", vo);
	}

	/**
	 * 댓글 삭제한다
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int deleteBoardReplyList(BoardVO vo) throws Exception {
		return delete("deleteBoardReplyList", vo);
	}
	
	/**
	 * 좋아요 시작
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int replyLikeAdd(BoardVO vo) throws Exception {
		return insert("replyLikeAdd", vo);
	}


	/**
	 * 좋아요 취소
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int replyLikeCancel(BoardVO vo) throws Exception {
		return insert("replyLikeCancel", vo);
	}

	/**
	 * 좋아요 불러오기
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public BoardVO replyLikeShow(BoardVO vo) throws Exception {
		return selectOne("replyLikeShow", vo);
	}
	
	/**
	 * 좋아요 개수 불러오기
	 * @param vo 게시판 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int selectReplyLikeCount(BoardVO vo) {
		return selectOne("selectReplyLikeCount", vo);
	}
	
	/**
	 * 싫어요 시작
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int replyHateAdd(BoardVO vo) throws Exception {
		return insert("replyHateAdd", vo);
	}


	/**
	 * 싫어요 취소
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int replyHateCancel(BoardVO vo) throws Exception {
		return insert("replyHateCancel", vo);
	}

	/**
	 * 싫어요 불러오기
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public BoardVO replyHateShow(BoardVO vo) throws Exception {
		return selectOne("replyHateShow", vo);
	}
	
	/**
	 * 싫어요 개수 불러오기
	 * @param vo 게시판 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int selectReplyHateCount(BoardVO vo) {
		return selectOne("selectReplyHateCount", vo);
	}

	/**
	 * 댓글 총 갯수 불러오기
	 * @param vo 카테고리 정보가 담긴 BoardVO
	 * @return 게시판 정보 BoardVO
	 * @exception Exception
	 */
	public int selectBoardReplyCount(BoardVO vo) throws Exception{
		return selectOne("selectBoardReplyCount", vo);
	}



	/**
	 * 게시글을 작성한다.
	 * @param vo 게시판 정보가 담긴 BoardVO
	 * @return 작성 결과 int
	 * @exception Exception
	 */
	public int insertTechList(BoardVO vo) throws Exception{
		return insert("insertTechList", vo);
	}

	


}
