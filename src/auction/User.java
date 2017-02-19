/**
 * 
 */
package auction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KALY
 *
 */
public class User {
	private String fname;
	private String lname;
	private double balance;
	private List <Item> wonItems  = new ArrayList<Item>();
	
	/**
	 * Constructor
	 */
	public User (String fname, String lname, double balance) {
		this.fname   = fname;
		this.lname   = lname;
		this.balance = balance;
	}
	
	/**
	 * A bid for an Item is placed.
	 */
	public void placeBid(Item item, double offer, Auction auction) {
		try {
			Bid bid = new Bid(this, item , offer);
			auction.getAuctionTracker().recordUserBid(bid);
			//auction.getAuctionTracker().recordLatestBidForItem(item, bid);
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Getters and Setters
	 */
	public String getFName() {
		return fname;
	}
	public void setFName(String fname) {
		this.fname = fname;
	}
	public String getLName() {
		return lname;
	}
	public void setLName(String lname) {
		this.lname = lname;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public List <Item> getWonItems() {
		return wonItems;
	}
	public void setWonItems(Item wonItem) {
		this.wonItems.add(wonItem);
	}
}
