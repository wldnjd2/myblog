package kr.co.negga.ntb.account.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.negga.ntb.account.service.AccountService;
import kr.co.negga.ntb.account.service.AccountVO;

/**
 * @Class AccountServiceImpl.java
 * @Description 회원 관련 서비스 구현 Class
 * @Since 2022.03.17
 * @author jshan
 * @version 1.0
 * @Modification 수정사항 없음 
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Resource(name="accountMapper")
	public AccountMapper accountMapper;
	
	/**
	 * 회원 가입시 insert를 함
	 * @param vo 회원 정보가 담긴 AccountVO
	 * @exception Exception
	 */
	@Override
	public int insertAccount(AccountVO avo) throws Exception {

		return accountMapper.insertAccount(avo);
	}

	/*
	 * 중복아이디 체크
	 */
	@Override
	public int selectIdCheck(String user_id) throws Exception {
		
		return accountMapper.selectIdCheck(user_id);
	}

	/*
	 * 회원정보 출력
	 */
	@Override
	public AccountVO selectAccount(String user_id) throws Exception {
		
		return accountMapper.selectAccount(user_id);
	}

	/*
	 * 회원정보 수정
	 */
	@Override
	public int updateAccount(AccountVO avo) throws Exception {
		
		return accountMapper.updateAccount(avo);
	}

	/*
	 * 회원정보 수정 비밀번호 확인
	 */
	@Override
	public int selectAccountPass(String user_pw) throws Exception {
		
		return accountMapper.selectAccountPass(user_pw);
	}

	
}
