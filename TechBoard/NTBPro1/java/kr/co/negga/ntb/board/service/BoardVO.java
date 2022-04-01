package kr.co.negga.ntb.board.service;

/**
 * @Class BoardVO.java
 * @Description 메인게시판 게시글 정보 VO class
 * @Since 2022.03.16
 * @author hsan
 * @version 1.0
 * @Modification 수정사항 없음
 */
public class BoardVO {
	
	/** 게시글 번호 */
	private int post_pnum;
	/** 게시글 등록일 */
	private String post_regdate;
	/** 게시글 제목 */
	private String post_title;
	/** 게시글 내용 */
	private String post_contents;
	/** 게시글 이미지 */
	private String post_img;
	/** 게시글 첨부파일 경로 */
	private String post_file;
	/** 게시글 첨부파일명 */
	private String post_fname;
	/** 게시글 좋아요 수 */
	private int post_like;
	/** 게시글 조회수 */
	private int post_hits;
	/** 게시글 작성자 */
	private String user_id;
	/** 카테고리명 */
	private String cat_cname;
	
	/** 댓글모음*/
	/** 댓글 */
	private String rep_contents;
	/* 댓글 날짜 */
	private String rep_regdate;
	/** 댓글 번호 */
	private int rep_rnum;
	/** 댓글단 사람 아이디*/
	private String rep_user_id;
	
	
	/** 현재페이지 */
	private int currentPage = 1;
	
	/** 페이지당 레코드 개수 */
	private int recordCountPerPage = 5;
	
	/** 페이지사이즈 */
	private int pageSize = 10;
	
	/** 첫 페이지 인덱스 */
	private int firstIndex = 1;
	
	/** 마지막페이지 인덱스 */
	private int lastIndex = 1;

	
	public int getPost_pnum() {
		return post_pnum;
	}
	public void setPost_pnum(int post_pnum) {
		this.post_pnum = post_pnum;
	}
	public String getPost_regdate() {
		return post_regdate;
	}
	public void setPost_regdate(String post_regdate) {
		this.post_regdate = post_regdate;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_contents() {
		return post_contents;
	}
	public void setPost_contents(String post_contents) {
		this.post_contents = post_contents;
	}
	public String getPost_img() {
		return post_img;
	}
	public void setPost_img(String post_img) {
		this.post_img = post_img;
	}
	public String getPost_file() {
		return post_file;
	}
	public void setPost_file(String post_file) {
		this.post_file = post_file;
	}
	public String getPost_fname() {
		return post_fname;
	}
	public void setPost_fname(String post_fname) {
		this.post_fname = post_fname;
	}
	public int getPost_like() {
		return post_like;
	}
	public void setPost_like(int post_like) {
		this.post_like = post_like;
	}
	public int getPost_hits() {
		return post_hits;
	}
	public void setPost_hits(int post_hits) {
		this.post_hits = post_hits;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCat_cname() {
		return cat_cname;
	}
	public void setCat_cname(String cat_cname) {
		this.cat_cname = cat_cname;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstIndex() {
		return firstIndex;
	}
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
	public int getLastIndex() {
		return lastIndex;
	}
	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getRep_contents() {
		return rep_contents;
	}
	public void setRep_contents(String rep_contents) {
		this.rep_contents = rep_contents;
	}
	public String getRep_regdate() {
		return rep_regdate;
	}
	public void setRep_regdate(String rep_regdate) {
		this.rep_regdate = rep_regdate;
	}
	public int getRep_rnum() {
		return rep_rnum;
	}
	public void setRep_rnum(int rep_rnum) {
		this.rep_rnum = rep_rnum;
	}
	public String getRep_user_id() {
		return rep_user_id;
	}
	public void setRep_user_id(String rep_user_id) {
		this.rep_user_id = rep_user_id;
	}

	
}
