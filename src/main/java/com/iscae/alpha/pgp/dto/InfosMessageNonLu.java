package com.iscae.alpha.pgp.dto;

public class InfosMessageNonLu {

	private int msgNonLu;
	private Long idUser;
	public InfosMessageNonLu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InfosMessageNonLu(int msgNonLu, Long idUser) {
		super();
		this.msgNonLu = msgNonLu;
		this.idUser = idUser;
	}
	public int getMsgNonLu() {
		return msgNonLu;
	}
	public void setMsgNonLu(int msgNonLu) {
		this.msgNonLu = msgNonLu;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	
}
