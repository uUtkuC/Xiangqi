
public abstract class AbstractItem implements ItemInterface {
		// kendim yazıyorum. if position == OffGame degil xx oldu, puanı player'a ver. unutma
	private String position;  // tahtadaki konumu gösterir. Örneğin, a1 ORIGINAL
	public String getPosition(){
		return position;
	} // abstract olsun demiş mücahit hoca da kalsın nolcak ki
	public void setPosition(String s){
		position = s;
	}


	
}
