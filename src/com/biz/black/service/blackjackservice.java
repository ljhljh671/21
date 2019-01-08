package com.biz.black.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.biz.black.vo.blackjackVO;

public class blackjackservice {

	List<blackjackVO> CardList; // 만든 카드들을 저장하는 LIST
	Scanner scan;	

	public blackjackservice() {
		CardList = new ArrayList();
		scan = new Scanner(System.in);
	}

	public void MakeCard() {
		// 문양별로 13개의 숫자를 저장시키기 위하여
		// 4개의 문양을 배열로 저장하고
		// 그 배열을 for으로 돌린 후 그 안에서 또 for을 돌려
		// 1부터 13까지의 값을 각 문양에 저장한다.
		String[] strShape = { "♠", "♥", "♣", "◈" };
		for (String s : strShape) {
			for (int i = 1; i <= 13; i++) {
				blackjackVO vo = new blackjackVO();
				// 1,11,12,13은 숫자가 아닌 알파벳(A,J,Q,K)이므로 
				// 새로운 메서드를 만들어 해당하는 숫자를 알파벳으로 변환해 준다.
				String strNum = check(i);
				
				// 11, 12, 13 즉 J, Q , K 에 해당하는 값은
				// 10으로 하기로 했으므로
				// 이것 또한 새로운 메서드를 만들어 해당하는 숫자를 10으로 바꾸어 준다.
				int intNum = check2(i);
				
				// 문양별로 각 숫자와 각 값을 저장한다.
				vo.setStrShape(s);
				vo.setStrNum(strNum);
				vo.setIntNum(intNum);
				CardList.add(vo);
			}
		}

	}
	// 1~13까지의 숫자를 A~K까지의 카드 숫자로 변환하기 위한 메서드

	public String check(int i) {
		
		if (i == 1)
			return "A";
		if (i == 11)
			return "K";
		if (i == 12)
			return "Q";
		if (i == 13)
			return "J";
		// 나머지것들은 숫자 그대로 리턴
		return "" + i;

	}
	
	// 나중에 승리 패배를 가르기 위해 각 카드들을 값형태로 변환
	// 11,12,13,A 는 10, 1 값으로 변환

	public int check2(int i) {
		if (i == 11)
			return 10;
		if (i == 12)
			return 10;
		if (i == 13)
			return 10;

		return i;
	}
	
	// 카드를 섞는 method

	public void shuffle() {
		// LIST를 shuffle 한다.
		Collections.shuffle(CardList);
	}

	public void Start() {
		// 게임을 계속 실행시키기 위하여 while문을 이용해 무한 반복한다.
		while (true) {
			System.out.println("======================================");
			System.out.println("게임을 시작합니다");
			System.out.println("======================================");
			System.out.print("1. 시작    0. 종료 ==> ");
			// 시작할 것인지 종료할 것인지 scanner를 통해 입력받고
			String strgostop = scan.nextLine();
			System.out.println();
			
			// 시작 한다는 숫자인 1을 입력을 받으면 게임을 시작한다.

			if (strgostop.equals("1")) {
				
				// 위에서 만들었던 shuffle 메서드를 이용해 카드를 섞고
				shuffle();
				
				// 차례차례 카드를 뽑아야 되니 딜러는 0,2번째
				// 게이머는 1,3번째 카드를 뽑는다.

				String strdealerCard1 = CardList.get(0).getStrShape() + CardList.get(0).getStrNum();
				String strdealerCard2 = CardList.get(2).getStrShape() + CardList.get(2).getStrNum();
				System.out.print("딜러 : ");
				// 딜러가 뽑은 카드를 콘솔에 출력해준다.
				System.out.print(strdealerCard1);
				System.out.println(strdealerCard2);

				String strgamerCard1 = CardList.get(1).getStrShape() + CardList.get(1).getStrNum();
				String strgamerCard2 = CardList.get(3).getStrShape() + CardList.get(3).getStrNum();
				System.out.print("게이머 : ");
				
				// 게이머가 뽑은 카드를 콘솔에 출력해준다.
				System.out.print(strgamerCard1);
				System.out.println(strgamerCard2);
				
				// 뽑은 각 카드 값을 계산하기 위하여 CardList에 저장했던 각 카드 값들을 꺼내와
				// 뽑은 카드의 덧셈을 수행 해 본다.

				int intdealerSum = CardList.get(0).getIntNum() + CardList.get(2).getIntNum();
				int intgamerSum = CardList.get(1).getIntNum() + CardList.get(3).getIntNum();

				while (true) {
					
					// 딜러가 뽑은 카드값의 합이 16 이하이면 추가 카드를 뽑아야 한다.
					// 게이머가 4번째로 카드를 뽑았기 때문에 딜러는 다음 순서인
					// 5번째에 바로 카드를 뽑을 수 있다.
					// 딜러 차례이기 때문에
					// 딜러의 카드 숫자 합이 16 이하일 경우 바로 카드를 뽑는다.

					if (intdealerSum <= 16) {
						String strdealerCard3 = CardList.get(4).getStrShape() + CardList.get(4).getStrNum();
						System.out.print("딜러 추가 카드 : ");
						// 딜러의 추가카드를 보여주고
						System.out.println(strdealerCard3);
						// 추가 카드를 뽑았을 경우 뽑은 카드를 딜러의 원래 카드 합에 또 합산 한다.
					
						intdealerSum += CardList.get(4).getIntNum();
						
						// 뽑은 카드는 제거하여 중복값이 없도록 한다.
						CardList.remove(4);
						
						// 추가 카드를 뽑기 전 딜러 카드값의 합이 16 초과이면
						// while문을 멈춘다.

					} else {
						break;
					}
					
					// 딜러가 추가 카드를 뽑고 게이머가 추가 카드를 뽑는 경우에 이 곳을
					// 지나가게 된다.
					// 게이머는 카드를 더 뽑을지 질문을 받게 되고
					

					System.out.println("카드를 더 뽑으시겠습니까?");
					System.out.print("YES or NO ==> ");
					String strScan = scan.nextLine();
					
					// 카드를 더 뽑는다고 답을 하면

					if (strScan.equals("YES")) {
						// 3번째 카드를 뽑고 
						String strgamerCard3 = CardList.get(4).getStrShape() + CardList.get(4).getStrNum();
						// 3번쨰 뽑은 카드를 원래 있던 2장의 카드 합에 더해준다.
						intgamerSum += CardList.get(4).getIntNum();
						// 3번째 뽑은 카드를 보여주고
						System.out.print("게이머 추가 카드 : ");
						System.out.println(strgamerCard3);
						// 중복값을 피하기 위해 추가로 뽑은 카드는 지워준다. 
						CardList.remove(4);
						
						// 만약 게이머가 NO를 선택했을 경우 break로 while문을 중지한다.
					} else {

						break;

					}
				}
				// 위의 while문이 끝났지만 딜러가 16 초과이지만 게이머가 카드를 더 뽑고싶은 경우,
				// 딜러가 추가 카드를 뽑고, 게이머가 NO를 선택했지만 딜러가 추가로 또 카드를 뽑아야 하는 경우(딜러가 AAA 또는 222 등을
				// 뽑았을 경우)에는 카드를 계속해서 또 뽑아야 하기 때문에
				// 새로운 while문을 각각 만들어 준다.
				
				
				// 게이머는 NO를 했지만 딜러가 추가 카드를 계속해서 뽑아야 하는 경우이다.
				while (true) {
					
					// 위의 while문과 동일하며
					if (intdealerSum <= 16) {
						String strdealerCard3 = CardList.get(4).getStrShape() + CardList.get(4).getStrNum();
						System.out.print("딜러 추가 카드 : ");
						System.out.println(strdealerCard3);
						// 추가로 또 뽑은 카드를 원래 있던 카드합에 합산하고
						intdealerSum += CardList.get(4).getIntNum();
						// 마찬가지로 추가로 뽑은 카드는 제거하여 중복값이 없도록 한다.
						CardList.remove(4);
						
						// 어떤 경우에든(딜러가 2장을 뽑았는데 합이 16 이상, 딜러가 3장이상
						// 을 뽑았는데 딜러의 카드합이 16 초과인 경우) 이  while문은
						// 중지되고 다음 명령으로 넘어가게 된다.

					} else {
						break;
					}
				}
				
				// 딜러의 조건은 다 만족이 되고  게이머는 카드를 더 뽑고싶을 때 이 곳을 지나가게 된다.
				// 게이머는 계속해서 카드를 추가로 뽑을 수 있고
				while (true) {
					System.out.println("카드를 더 뽑으시겠습니까?");
					System.out.print("YES or NO ==> ");
					String strScan = scan.nextLine();
					if (strScan.equals("YES")) {
						String strgamerCard3 = CardList.get(4).getStrShape() + CardList.get(4).getStrNum();
						System.out.print("게이머 추가 카드 : ");
						System.out.println(strgamerCard3);
						intgamerSum += CardList.get(4).getIntNum();
						CardList.remove(4);
						
						// 마침내 카드를 더이상 뽑지 않는다고 할 때
						// 승패를 가른다.
					} else {
						
						// 딜러가 뽑은 카드의 총 합이 21이 넘으면 탈락이므로
						// 0으로 숫자중 가장 낮은 값을 갖게 하고

						if (intdealerSum > 21) {
							intdealerSum = 0;
						}
						
						// 게이머도 같은 방법으로 세팅해준다.
						
						if (intgamerSum > 21) {
							intgamerSum = 0;
						}
						
						// 게이머와 딜러 모두 21을 넘지 않는 수에서
						// 더  큰 수를 가진 자가 승리하게 되는 식
						
						// 딜러가 게이머보다 더 큰 수를 가질 때 게이머는 패배하게된다. 

						if (intdealerSum > intgamerSum) {
							System.out.println("-------------------");
							System.out.println("패배하였습니다....");
							System.out.println("-------------------");
							
							// 딜러와 게이머가 얻은 카드의 총합을 마지막에 보여준다. 
							System.out.println("딜러 점수 : " + intdealerSum);
							System.out.println("게이머 점수 : " + intgamerSum);
							// 게임을 끝내고 새로운 게임을 시작한다.
							break;
						
						// 딜러가 게이머보다 더 작은 수를 가질 때 게이머는 승리하게 된다.
						} else if (intdealerSum < intgamerSum) {
							System.out.println("-------------------");
							System.out.println("승리하였습니다!!!!");
							System.out.println("-------------------");
							
							System.out.println("딜러 점수 : " + intdealerSum);
							System.out.println("게이머 점수 : " + intgamerSum);
							break;
						
						// 딜러와 게이머가 같은 값을 가질 때 무승부가 된다.
						// 이때 딜러와 게이머 모두 21을 넘는 값을 가질 때
						// (둘 다 0일 떄)에도 무승부가 된다.
						} else if (intdealerSum == intgamerSum) {
							System.out.println("-------------------");
							System.out.println("무승부입니다");
							System.out.println("-------------------");
							
							System.out.println("딜러 점수 : " + intdealerSum);
							System.out.println("게이머 점수 : " + intgamerSum);
							break;
						}
					}
				}
				
				// 게이머가 0. 종료 를 선택했을 경우
				// 가장 바깥쪽에 있는 while문을 멈추며
				// 인사와 함께 게임을 끝낸다.

			} else {
				System.out.println("감사합니다");
				break;
			}
		}
	}
}

//	List<blackjackVO> intNumList = new ArrayList()
//
//
//	for(int i = 0 ; i < 13 ; i++) {
//		intNumList.set(i, i + 1);
//		if (i >= 9) {
//			intNumList.set(i, 10);
//		}
//	}for(
//	int i = 13;i<26;i++)
//	{
//		intNumList.set(i, i - 12);
//		if (i >= 22) {
//			intNumList.set(i, 10);
//		}
//
//	}for(
//	int i = 26;i<39;i++)
//	{
//		intNumList.set(i, i - 25);
//		if (i >= 35) {
//			intNumList.set(i, 10);
//		}
//
//	}for(
//	int i = 39;i<52;i++)
//	{
//		intNumList.set(i, i - 38);
//		if (i >= 48) {
//			intNumList.set(i, 10);
//		}
//	}
//
//	Collections.shuffle(intNumList);
//
//	int intNum = 0;for(
//	int i = 0;i<52;i++)
//	{
//		System.out.println(intNumList.get(i));
//		intNum = intNumList.get(i);
//		intNum += intNum;
//		if (intNum >= 17)
//			break;
//	}
//
//	System.out.println(intNum);
//
//	System.out.print(intNumList.get(0)+" ");System.out.print(intNumList.get(2)+" ");
//	int intNum = intNumList.size();while(true)
//	{
//		if (intNumList.get(0) + intNumList.get(2) <= 16) {
//			for (int i = 4; i < intNum; i++) {
//				System.out.print(intNumList.get(i));
//
//			}
//		}
//	}
//}

////		2
////		3
////		4
////		5
////		6
////		7
////		8
//		9
//		10 -- 3개
//
//	}
