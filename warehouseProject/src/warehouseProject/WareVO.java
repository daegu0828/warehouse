package warehouseProject;

public class WareVO {

	String prodCode;
	String prodName;
	int price;
	int enter;
	int changes;
	String wName;
	int amount;
	
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}	
	public int getEnter() {
		return enter;
	}
	public void setEnter(int enter) {
		this.enter = enter;
	}
	public int getChanges() {
		return changes;
	}
	public void setChanges(int changes) {
		this.changes = changes;
	}
	public String getwName() {
		return wName;
	}
	public void setwName(String wName) {
		this.wName = wName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "wareVO [prodCode=" + prodCode + ", prodName=" + prodName + ", price=" + price + "]";
	}
}
