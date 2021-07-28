package project1;

public class Reimbursement 
{
	private String issuerName;
	private int expenseAmount = 0;
	private String notes;
	private String status;
	
	public Reimbursement()
	{
		super();
	}
	
	public Reimbursement(String issuerName, int expenseAmount, String notes, String status) {
		super();
		this.issuerName = issuerName;
		this.expenseAmount = expenseAmount;
		this.notes = notes;
		this.status = status;
	}

	public String getIssuerName() {
		return issuerName;
	}
	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}
	public int getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(int expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reimbursement [issuerName=" + issuerName + ", expenseAmount=" + expenseAmount + ", notes=" + notes
				+ ", status=" + status + "]";
	}
}
