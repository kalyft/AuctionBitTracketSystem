/**
 * 
 */
package auction;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;


/**
 * @author KALY
 *
 */
public class Auction {
	private List <User> userList;
	private List <Item> itemsList;
	private Date startDate;
	private Date stopDate;
	private BitTracker BitTracker;
	
	/**
	 * Constructor
	 */
	public Auction(List<Item> itemsList, List<User> userList) {
		this.itemsList = itemsList;
		this.userList = userList;
		setAuctionTracker(new BitTracker(this));
		this.setStartDate();
	}
	
	public static void main(String[] args) {
		// Create Items.
		List<Item> itemsList = new ArrayList<Item>();
		Item item1 = new Item("Samsung Galaxy S7 64GB Black", 500);
		Item item2 = new Item("French Régence Chess set", 300);
		Item item3 = new Item("x REVOLVE Bell Sleeve MC Jacket", 200);
		
		itemsList.add(item1);
		itemsList.add(item2);
		itemsList.add(item3);
		
		// Create users.
		List <User> userList = new ArrayList<User>();
		User user1 = new User("Calypso", "Foti", 1000);
		User user2 = new User("Dimitris", "Papadopoulos", 5000);
		User user3 = new User("Jack", "The Dark", 2000);
		
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		

		Auction auction = new Auction(itemsList, userList);
		
		/**
		 *  Users are placing their bids
		 */
		user1.placeBid(item1, 400, auction);  // reproduce exception error lower than starting price
		user2.placeBid(item1, 800, auction);
		user1.placeBid(item1, 802, auction);
		user2.placeBid(item1, 805, auction);
		user3.placeBid(item1, 1000, auction);
		
		user1.placeBid(item2, 700, auction);
		user3.placeBid(item2, 800, auction);
		user1.placeBid(item2, 802, auction);
		user2.placeBid(item2, 805, auction);
		user3.placeBid(item2, 1000, auction);
		
		
		user1.placeBid(item3, 802, auction);
		user2.placeBid(item3, 801, auction); // reproduce exception error lower than offers been placed
		user3.placeBid(item3, 1000, auction); // reproduce exception error offer higher than user balance
		
		auction.stop();
		auction.getAuctionTracker().getAllBidsForItem(item1);
		auction.getAuctionTracker().getAllBidsForItem(item2);
		auction.getAuctionTracker().getAllBidsForItem(item3);
	}
	
	private void stop() {
		for (Item item : itemsList) {
			try {
				this.sellItem(item);
			} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					item.setNextWinningBid();
					if (item.getCurrentWinningBid() != null) {
						this.sellItem(item);
					} else {
						System.out.println("Item " + item.getDescription() + " was not sold"); 
					}
			}
		}
		this.setStopDate();
	}
	
	private void sellItem(Item item) {
		User winner = item.getCurrentWinningBid().getUser();
		if (item.getCurrentWinningBid().getOffer() > winner.getBalance()) {
			throw new RuntimeException("Your Balance " + winner.getBalance() + " is lower than the offer");
		}
		
		winner.setWonItems(item);
		winner.setBalance(winner.getBalance() - item.getCurrentWinningBid().getOffer());
		item.setSold(true);
		System.out.println("***************************************************************");
		System.out.println("The item " + item.getDescription() + " has been sold ");
		System.out.println("to user " + winner.getFName() + " for " + item.getCurrentWinningBid().getOffer());
		System.out.println("***************************************************************");
		double balance = winner.getBalance();
		System.out.println("New User balance " + winner.getFName() + " new balance is  " + balance);
	}
	
	/**
	 * Getters and Setters
	 */
	public List <Item> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List <Item> itemsList) {
		this.itemsList = itemsList;
	}

	public List <User> getUserList() {
		return userList;
	}

	public void setUserList(List <User> userList) {
		this.userList = userList;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate() {
		this.startDate =  new Date();
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate() {
		this.stopDate =  new Date();
		System.out.println("End auction time" + this.stopDate );
	}

	public BitTracker getAuctionTracker() {
		return BitTracker;
	}

	public void setAuctionTracker(BitTracker BitTracker) {
		this.BitTracker = BitTracker;
	}
}
