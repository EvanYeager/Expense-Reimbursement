package project1;

public class TestReimbursement {
	private int amount;
	private String notes;

	public TestReimbursement(int amount, String notes) {
		super();
		this.amount = amount;
		this.notes = notes;
	}
	
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
