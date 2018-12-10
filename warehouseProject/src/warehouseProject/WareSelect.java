package warehouseProject;

import java.util.Scanner;

public class WareSelect {

	public void run() {

		Scanner sc = new Scanner(System.in);
		WareDAO dao = new WareDAO();
		WareVO vo = null;

		int num, price, amount, enter;
		boolean t = true;
		String r = null;
		String prodCode, prodName,wname;

		do {
			System.out.println("1.상품 입력 \t" + "2.상품 수정\n" + "3.상품 조회 \t" 
		+ "4.거래 입력 \n" + "5.거래 조회 \t" +  "6.재고 조회 \n" + "7.종료\t");

			System.out.print("\n번호를 입력하세요 : ");
			num = sc.nextInt();

			switch (num) {
			case 1: // 상품 입력
				vo = new WareVO();
				System.out.print("상품 번호 입력 : ");
				vo.setProdCode(prodCode = sc.next());
				System.out.print("상품 이름 입력 : ");
				vo.setProdName(prodName = sc.next());
				System.out.print("상품 가격 입력 : ");
				vo.setPrice(price = sc.nextInt());
				System.out.print("상품 수량 입력 : ");
				vo.setAmount(amount = sc.nextInt());
				System.out.print("창고명 입력 : ");
				vo.setwName(wname = sc.next());

				dao.insertProduct(vo);

				dao.getFindProduct(prodCode);
				break;

			case 2: // 상품 수정
				vo = new WareVO();
				System.out.print("수정할 상품 번호 입력 : ");
				vo.setProdCode(prodCode = sc.next());
				System.out.print("변경할 상품 가격 : ");
				vo.setPrice(price = sc.nextInt());
				
				dao.updateProduct(vo);
				break;

			case 3: // 상품 조회
				System.out.println("상품 번호 입력 : ");
				prodCode = sc.next();

				dao.getFindProduct(prodCode);
				break;
				
			case 4: //거래 입력
				vo = new WareVO();
				System.out.print("상품 번호 입력 : ");
				vo.setProdCode(prodCode = sc.next());
				System.out.print("상품 이름 입력 : ");
				vo.setProdName(prodName = sc.next());
				System.out.print("입고 1, 출고 -1 : ");
				vo.setEnter(enter = sc.nextInt());
				System.out.print("변경 수량 입력 : ");
				vo.setAmount(amount = sc.nextInt());
				System.out.print("창고명 입력 : ");
				vo.setwName(wname = sc.next());

				dao.insertTrade(vo);

				dao.getTradeList(prodCode);
				
			case 5: //거래 조회
				System.out.println("상품 번호 입력 : ");
				prodCode = sc.next();

				dao.getTradeList(prodCode);
				break;

			case 6: // 창고 정보
				System.out.println("상품 번호 입력 : ");
				prodCode = sc.next();

				dao.getStockList(prodCode);
				break;
				
			case 7:
				t = false;
				break;
				
			default:
				System.out.println("번호를 다시 선택하세요 : ");
			}
		} while (t);
	}

}
