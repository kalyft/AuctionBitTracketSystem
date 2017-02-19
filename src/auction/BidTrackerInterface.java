/**
 * 
 */
package auction;

import java.util.List;

/**
 * @author KALY
 *
 */
public interface BidTrackerInterface {
	
	/**
	 * Record each bid 
	 */
	public void recordUserBid(Bid bid);
	
	/**
	 * Get all bids for an item. 
	 */
	public List<Bid> getAllBidsForItem(Item item);

}
