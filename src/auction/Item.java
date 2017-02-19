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
public class Item {
	private String     description;
	private double     startingPrice;
	private Boolean    sold = false;
	private Bid        winningBid;
	private List <Bid> winningBidsPriorityList = new ArrayList <Bid>();
	
	/**
	 * Constructor
	 */
	public Item(String description, double startingPrice) {
		this.description = description;
		this.startingPrice = startingPrice;
	}
	
	/**
	 * Remove the last bid placed and make 
	 * the previous the winning one
	 */
	public void setNextWinningBid() {
		int index = winningBidsPriorityList.size() - 1;
		Bid fraudBid = winningBidsPriorityList.get(index);
		fraudBid.setFraud(true);
		winningBidsPriorityList.remove(fraudBid);
		
		// get bid's list size after removing the invalid one
		index = winningBidsPriorityList.size() - 1;
		System.out.println("Fraud " + fraudBid.getUser().getFName());
		if (index > -1) {
			this.setCurrentWinningBid(winningBidsPriorityList.get(index));
		} else {
			this.setCurrentWinningBid(null);
			System.out.println("No more bids for the item ");
		}
	} 
	
	/**
	 * Getters and Setters
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(double startingPrice) {
		this.startingPrice = startingPrice;
	}
	
	public Bid getCurrentWinningBid() {
		return winningBid;
	}
	public void setCurrentWinningBid(Bid winningBid) {
		this.winningBid = winningBid;
		this.setWinningBidsPriorityList(winningBid);
	}

	public Boolean getSold() {
		return sold;
	}
	public void setSold(Boolean sold) {
		this.sold = sold;
	}

	public List <Bid> getWinningBidsPriorityList() {
		return winningBidsPriorityList;
	}

	public void setWinningBidsPriorityList(Bid winningBid) {
		this.winningBidsPriorityList.add(winningBid);
	}
}
