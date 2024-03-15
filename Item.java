
public class Item extends AbstractItem{
	String name;
	public String toString(){
		return name+ getPosition() ;
	}
	@Override
	public void move(String destination) { // ORIGINAL
		// TODO Auto-generated method stub
		
	}
	public String getName(){
		return name;
	}

	public Item cloneMe(){
		Item itemo = new Item();
		itemo.name = name;
		itemo.setPosition(getPosition());
		return itemo;
	}
	int puan = 0;
}
