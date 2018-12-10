package warehouseProject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WareDAO {
	PreparedStatement psmt;
	ResultSet rs;
	Connection conn = DBConnection.getConnection();
	
	public void insertProduct(WareVO vo) { // insert product, stock
		String sql = "insert into product(prod_code, prod_name, price) values (?, ?, ?)";
		String sql2 = "insert into stock(w_name, prod_code, prod_name, amount) values (?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getProdCode());
			psmt.setString(2, vo.getProdName());
			psmt.setInt(3, vo.getPrice());
			int pt = psmt.executeUpdate();
			System.out.println("product table에 " + pt + "건의 내용이 입력되었습니다.");

			psmt = conn.prepareStatement(sql2);
			psmt.setString(1, vo.getwName());
			psmt.setString(2, vo.getProdCode());
			psmt.setString(3, vo.getProdName());
			psmt.setInt(4, vo.getAmount());
			int st = psmt.executeUpdate();
			System.out.println("stock table에 " + st + "건의 내용이 입력되었습니다");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateProduct(WareVO vo) { // update
		String sql = "update product set price = ? where prod_code = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getPrice());
			psmt.setString(2, vo.getProdCode());
			
			int r = psmt.executeUpdate();
			System.out.println("수정 되었습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getFindProduct(String searchCondition) { // 상품 번호로 찾기
		WareVO wvo = null;

		try {
			String sql = "select * from product where prod_code = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, searchCondition);

			rs = psmt.executeQuery();

			if (rs.next()) {
				do {
					wvo = new WareVO();

					wvo.setProdCode(rs.getString("prod_code"));
					wvo.setProdName(rs.getString("prod_name"));
					wvo.setPrice(rs.getInt("price"));

					System.out.println(wvo.toString());

				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertTrade(WareVO vo) { // insert trade
		CallableStatement cs;

		String sql = "insert into trade(prod_code, prod_name, enter, changes, w_name) values (?, ?, ?, ?, ?)";
		try {

			cs = conn.prepareCall("{call INSERT_TRADE(?,?,?,?,?)}");
			cs.setString(1, vo.getProdCode());
			cs.setString(2, vo.getProdName());
			cs.setInt(3, vo.getEnter());
			cs.setInt(4, vo.getChanges());
			cs.setString(5, vo.getwName());

			cs.execute();
			System.out.println("프로시저를 성공적으로 호출했습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getStockList(String searchCondition) { // 재고 정보
		WareVO wvo = new WareVO();

		try {
			String sql = "select * from stock where prod_code = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, searchCondition);
			
			rs = psmt.executeQuery();

			if (rs.next()) {
				do {
					wvo.setwName(rs.getString("w_name"));
					wvo.setProdCode(rs.getString("prod_code"));
					wvo.setProdName(rs.getString("prod_name"));
					wvo.setAmount(rs.getInt("amount"));

					System.out.println("w_name : " + wvo.getwName() + "\t prod_code : " + wvo.getProdCode()
							+ "\t prod_name: " + wvo.getProdName() + "\t amount : " + wvo.getAmount());

				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getTradeList(String searchCondition) { // 입출고 정보
		WareVO wvo = new WareVO();

		try {
			String sql = "select * from trade where prod_code = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, searchCondition);
			
			rs = psmt.executeQuery();

			if (rs.next()) {
				do {
					wvo.setProdCode(rs.getString("prod_code"));
					wvo.setProdName(rs.getString("prod_name"));
					wvo.setEnter(rs.getInt("enter"));
					wvo.setPrice(rs.getInt("changes"));
					wvo.setwName(rs.getString("w_name"));

					System.out.println("prod_code : " + wvo.getProdCode() + "\t prod_name: " + wvo.getProdName() + "\t enter : " + wvo.getEnter()
							+ "\t changes : " + wvo.getChanges() + "\t w_name : " + wvo.getwName());
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
