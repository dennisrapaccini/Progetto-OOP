package com.project.MetaStats.model;

/**Questa classe rappresenta i luoghi in cui si trova l'utente nei suoi post
 * @author Cheikh
 * @author Dennis
 */
public class Location extends SuperPost{
	
	/**
	 * Attributi relativi alle location
	 */
	private String city;
	private String province;
	private String region;
	
	/**Costruttore dell'oggetto location
	 * @param city
	 * @param province
	 * @param region
	 */
	public Location(String city, String province, String region) {
		super();
		this.city = city;
		this.province = province;
		this.region = region;
	}
	
	/**Metodo che restituisce il nome della città in cui si trova(getter)
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**Metodo che setta il nome della città(setter)
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**Metodo che restituisce la provincia della città(getter)
	 * @return province
	 */
	public String getProvince() {
		return province;
	}
	
	/**Metodo che setta la provincia della città
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	
	/**Metodo che restituisce la regione della città
	 * @return region
	 */
	public String getRegion() {
		return region;
	}
	
	/**Metodo che setta la regione della città
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
	/**Override del metodo toString che restituisce l'oggetto Location in stringa
	 */
	@Override
	public String toString() {
		return "City: "+city+"/nProvince: "+province+"/nRegion: "+region;
	}
	
}
