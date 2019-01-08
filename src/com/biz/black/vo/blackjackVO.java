package com.biz.black.vo;

public class blackjackVO {

	private String strShape;             // ♠ ♥ ♣ ◈ 문양을 저장하는 변수
	private String strNum;				 // A~K 까지 숫자를 저장하는 변수
	private int intNum;					 // 1~10 까지 각 카드의 값을 저장하는 변수 

	public int getIntNum() {
		return intNum;
	}

	public void setIntNum(int intNum) {
		this.intNum = intNum;
	}

	public String getStrShape() {
		return strShape;
	}

	public void setStrShape(String strShape) {
		this.strShape = strShape;
	}


	public String getStrNum() {
		return strNum;
	}

	public void setStrNum(String strNum) {
		this.strNum = strNum;
	}

	@Override
	public String toString() {
		return "blackjackVO [strShape=" + strShape + ", strNum=" + strNum + ", intNum=" + intNum + "]";
	}

}
