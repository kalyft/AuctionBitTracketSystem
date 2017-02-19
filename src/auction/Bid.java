/**
 * 
 */
package auction;
/**
 * @author KALY
 *
 */
public class Bid {

	private User user;
	private Item item;
	private double offer;
	private boolean isFraud = false;
	
	/**
	 * Constructor
	 */
	public Bid (User user, Item item, double offer) {
		if (offer < item.getStartingPrice()) {
			throw new RuntimeException("The offer is lower than the item's starting price " + item.getStartingPrice());
		} else if ((item.getCurrentWinningBid() != null) && (offer < item.getCurrentWinningBid().getOffer())){
			throw new RuntimeException("The Offer is lower that the last winning one " + item.getCurrentWinningBid().getOffer());
		} else {
			System.out.println("Bid from user : " + user.getFName() + " for " + item.getDescription() + " offering " + offer);
			this.setItem(item);
			this.setUser(user);
			this.setOffer(offer);
			item.setCurrentWinningBid(this);
		}
	}
	
	/**
	 * Getters and Setters
	 */
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public double getOffer() {
		return offer;
	}
	public void setOffer(double offer) {
		this.offer = offer;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public boolean isFraud() {
		return isFraud;
	}
	public void setFraud(boolean isFraud) {
		this.isFraud = isFraud;
	}
}
