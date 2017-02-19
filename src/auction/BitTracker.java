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
public class BitTracker implements BidTrackerInterface {
	private Auction auction;
	private List<Bid> userBids = new ArrayList <Bid>();
	private List<Bid> itemBids = new ArrayList <Bid>();
	/**
	 * Constructor
	 */
	public BitTracker(Auction auction) {
		this.auction = auction;
	}
	/**
	 * Record User's bid on an Item.
	 */
	public void recordUserBid(Bid bid) {
		this.userBids.add(bid);
	}

	/**
	 * Implementation getAllItemBids
	 * Get all the bids for an Item. 
	 */
	public List<Bid> getAllBidsForItem(Item item) {
		System.out.println("--------------" + item.getDescription() +"-----------------");
		for (int i = 0; i < userBids.size(); i++) {
			Bid bid = userBids.get(i);
			if (bid.getItem() == item && !bid.isFraud()){
				itemBids.add(bid);
				System.out.println("Bid from user " + bid.getUser().getFName() + " for " + bid.getOffer());
			}
		}
		return itemBids;
	}
	
	/**
	 * Getters and Setters
	 */
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}

}
