package importCSVAutomatedMail;

import java.io.Serializable;


public class GigInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private String venue;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String artist;
	private String cost;
	private String amountDue;
	private String startTime; 
	private String endTime;
	private String production;
	private String additionalTerms;
	private String openArtist;
	private String openStart;
	private String openEnd;
	private String loadInTime;
	private String email;
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoadInTime() {
		return loadInTime;
	}
	public void setLoadInTime(String loadInTime) {
		this.loadInTime = loadInTime;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public String getAdditionalTerms() {
		return additionalTerms;
	}
	public void setAdditionalTerms(String additionalTerms) {
		this.additionalTerms = additionalTerms;
	}
	public String getOpenArtist() {
		return openArtist;
	}
	public void setOpenArtist(String openArtist) {
		this.openArtist = openArtist;
	}
	public String getOpenStart() {
		return openStart;
	}
	public void setOpenStart(String openStart) {
		this.openStart = openStart;
	}
	public String getOpenEnd() {
		return openEnd;
	}
	public void setOpenEnd(String openEnd) {
		this.openEnd = openEnd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}	
	

