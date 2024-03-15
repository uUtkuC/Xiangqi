
public class Player {
	
	public float puan; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.
	public String isim;
	public Player(String s){
		setPuan(0);
		this.isim = s;
	}
	public void add(float k){
		setPuan(getPuan()+ k);

	}
	public void setPuan(float k){
		this.puan = k;
	}
	public float getPuan(){
		return this.puan;
	}
	public String getIsim(){
		return  isim;
	}
	public void  setIsim(String k){
		isim = k;
	}
}
