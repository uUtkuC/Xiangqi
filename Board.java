
public class Board extends AbstractBoard{

	String[][] Board = new String[19][9];
	@SuppressWarnings("NonAsciiCharacters")
	public void clearboard(){ // in the copied board thjis is used to clear all indexes.
		for(int n=0; n<Board.length ; n++){
			for(int m=0;m<Board[n].length ;m++){
				Board[n][m]= "-";
			} // creating objects;
		}
	}
	public Board(){

		for(int n=0; n<Board.length ; n++){
			for(int m=0;m<Board[n].length ;m++){
			Board[n][m]= "-";
			} // creating objects;
		}
		items = new Item[32]; // S = kırmızı, B = siyah 9 is at index of 8
		Sah Ssah = new Sah("Ş","j5");
		Top Stop1 = new Top("T","h2");
		Top Stop2 = new Top("T","h8");
		Vezir Svez1 = new Vezir("V","j4");
		Vezir Svez2 = new Vezir("V","j6");
		Fil Sfil1 = new Fil("F","j3");
		Fil Sfil2 = new Fil("F","j7");
		Kale Skale1 = new Kale("K","j1");
		Kale Skale2 = new Kale("K","j9");
		Piyon Spiyon1 = new Piyon("P","g1");
		Piyon Spiyon2 = new Piyon("P","g3");
		Piyon Spiyon3 = new Piyon("P","g5");
		Piyon Spiyon4 = new Piyon("P","g7");
		Piyon Spiyon5 = new Piyon("P","g9");
		Sah Bsah = new Sah("ş","a5");
		Top Btop1 = new Top("t","c2");
		Top Btop2 = new Top("t","c8");
		Vezir Bvez1 = new Vezir("v","a4");
		Vezir Bvez2 = new Vezir("v","a6");
		Fil Bfil1= new Fil("f","a3");
		Fil Bfil2 = new Fil("f","a7");
		Kale Bkale1 = new Kale("k","a1");
		Kale Bkale2 = new Kale("k","a9");
		Piyon Bpiyon1 = new Piyon("p","d1");
		Piyon Bpiyon2 = new Piyon("p","d3");
		Piyon Bpiyon3 = new Piyon("p","d5");
		Piyon Bpiyon4 = new Piyon("p","d7");
		Piyon Bpiyon5 = new Piyon("p","d9");
		At Sat1 = new At("A","j2");
		At Sat2 = new At("A","j8");
		At Bat2 = new At("a","a2");
		At Bat1 = new At("a","a8");

		items[0]= Bfil1;
		items[1]= Bfil2;
		items[2] = Bkale1;
		items[3]= Bkale2;
		items[4]= Bpiyon1;
		items[5]= Bpiyon2;
		items[6]= Bpiyon3;
		items[7]= Bpiyon4;
		items[8] = Bpiyon5;
		items[9] = Btop1;
		items[10] = Btop2;
		items[11] = Bvez1;
		items[12] = Bvez2;
		items[13] = Bsah;
		items[14]= Sfil1;
		items[15]= Sfil2;
		items[16] = Skale1;
		items[17]= Skale2;
		items[18]= Spiyon1;
		items[19]= Spiyon2;
		items[20]= Spiyon3;
		items[21]= Spiyon4;
		items[22] = Spiyon5;
		items[23] = Stop1;
		items[24] = Stop2;
		items[25] = Svez1;
		items[26] = Svez2;
		items[27] = Ssah;
		items[28] = Sat2;
		items[29] = Sat1;
		items[30] = Bat1;
		items[31] = Bat2;
		// for the following for loop. to have it faster. Purpose is to place Items to the Board.
		int xAxis ;
		int yAxis =0 ;
		char k;
		for(int n=0; n<items.length ; n++){
			xAxis = Integer.parseInt(items[n].getPosition().substring(1));
			 k= items[n].getPosition().charAt(0);// row
			switch (k){
				case 'a':
				yAxis = Board.length-1;
				break;
				case 'b':
					yAxis= Board.length-3;
					break;
				case 'c':
					yAxis= Board.length-5;
					break;
				case 'd':
					yAxis= Board.length-7;
					break;
				case 'e':
					yAxis= Board.length-9;
					break;
				case 'f':
					yAxis= Board.length-11;
					break;
				case 'g':
					yAxis= Board.length-13;
					break;
				case 'h':
					yAxis= Board.length-15;
					break;
				case 'i':
					yAxis= Board.length-17;
					break;
				case 'j':
					yAxis= Board.length-19;
					break;
			}
			//System.out.println("Suan bunu ekledim Y:"+ yAxis +" X:" +xAxis );
			Board[yAxis][xAxis-1] = items[n].getName();

		}
	}
	public Item[] getItems(){
		return items;
	}
	public void tasYe(String yiyenTas, String yenenTas){ // call clrear index after tasYe
	//	System.out.println("yiyenTas: "+ yiyenTas +" yenenTas: "+ yenenTas);
		char k = yenenTas.charAt(0);
		int yAxis = 0;
		int xAxis = Integer.parseInt(yenenTas.substring(1));
		switch (k){
			case 'a':
				yAxis = Board.length-1;
				break;
			case 'b':
				yAxis= Board.length-3;
				break;
			case 'c':
				yAxis= Board.length-5;
				break;
			case 'd':
				yAxis= Board.length-7;
				break;
			case 'e':
				yAxis= Board.length-9;
				break;
			case 'f':
				yAxis= Board.length-11;
				break;
			case 'g':
				yAxis= Board.length-13;
				break;
			case 'h':
				yAxis= Board.length-15;
				break;
			case 'i':
				yAxis= Board.length-17;
				break;
			case 'j':
				yAxis= Board.length-19;
				break;
		}
		int itemIndex = 0;
		for(int n=0;n<items.length ;n++){
			if(items[n].getPosition().equals(yiyenTas)) {itemIndex = n; break;} //  to make loop faster
		}
		//System.out.println("Bu yedi: "+ yiyenTas);
		removePiece(yenenTas);

		Board[yAxis][xAxis-1] = items[itemIndex].getName();

		//System.out.println("Ben calıstim yAxis:"+ yAxis+" xAxis-1 = "+ (xAxis-1));
		placeItems();
		//System.out.println(Board[yAxis][xAxis-1]);
	}
	private void removePiece(String item){ // Do not deine a new array. Leads to a bug duuno why ask. initial code:
		/*int itemIndex = 0;
		for(int n=0;n<items.length ;n++){
			if(items[n].getPosition().equals(item)) {itemIndex = n; break;} //  to make loop faster
		}
		Item[] buffer = new Item[ items.length-1];
		int currIndex =0;
		for(int n=0 ;n<items.length ;n++ ){
			if(n== itemIndex){
				items[n].setPosition("OffGame");
				System.out.println("Bu tas: "+ items[n].getName());
			} // do nothing
			else{buffer[currIndex] = items[n]; currIndex++; }
		}
		items = buffer; // lead to bugs why?
		*/
		// Adds point of the removed piece player!!!!
		int itemIndex = 0;
		for(int n=0;n<items.length ;n++){
			if(items[n].getPosition().equals(item)) {itemIndex = n; break;} //  to make loop faster
		}
		Item[] buffer = new Item[ items.length-1];
		int currIndex =0;
		for(int n=0 ;n<items.length ;n++ ){
			if(n== itemIndex){
				items[n].setPosition("xx");
			//	System.out.println("Bu tas: "+ items[n].getName());
			} // do nothing
			else{buffer[currIndex] = items[n]; currIndex++; }
		}

	}
	public void placeItems(){ // places changed items
		int xAxis ;
		int yAxis =0 ;
		char k;
		for(int n=0; n<items.length ; n++){
			if(items[n].getPosition().equals("xx") == false){
			//	System.out.println("Bu tas: "+items[n].getName() +" yeri:"+items[n].getPosition());
				//System.out.println("Position budur: "+ items[n].getPosition() +" ad " +items[n].getName());
				//System.out.println(items[n].getPosition().substring(1));
				xAxis = Integer.parseInt(items[n].getPosition().substring(1));
			k= items[n].getPosition().charAt(0);// row
			switch (k){
				case 'a':
					yAxis = Board.length-1;
					break;
				case 'b':
					yAxis= Board.length-3;
					break;
				case 'c':
					yAxis= Board.length-5;
					break;
				case 'd':
					yAxis= Board.length-7;
					break;
				case 'e':
					yAxis= Board.length-9;
					break;
				case 'f':
					yAxis= Board.length-11;
					break;
				case 'g':
					yAxis= Board.length-13;
					break;
				case 'h':
					yAxis= Board.length-15;
					break;
				case 'i':
					yAxis= Board.length-17;
					break;
				case 'j':
					yAxis= Board.length-19;
					break;
			}
			//System.out.println("Suan bunu ekledim Y:"+ yAxis +" X:" +xAxis );
			Board[yAxis][xAxis-1] = items[n].getName();

		}
		}
	}

	public void emptyGivenIndex(String str, String to){
		char k = str.charAt(0);
		int yAxis = 0;
		int xAxis = Integer.parseInt(str.substring(1));
		switch (k){
			case 'a':
				yAxis = Board.length-1;
				break;
			case 'b':
				yAxis= Board.length-3;
				break;
			case 'c':
				yAxis= Board.length-5;
				break;
			case 'd':
				yAxis= Board.length-7;
				break;
			case 'e':
				yAxis= Board.length-9;
				break;
			case 'f':
				yAxis= Board.length-11;
				break;
			case 'g':
				yAxis= Board.length-13;
				break;
			case 'h':
				yAxis= Board.length-15;
				break;
			case 'i':
				yAxis= Board.length-17;
				break;
			case 'j':
				yAxis= Board.length-19;
				break;
		}
		Board[yAxis][xAxis-1] = "-";
		for(int n=0; n<items.length ; n++){ //
			if(items[n].getPosition().equals(str)){
				items[n].setPosition(to);
			}
		}
	}
	@Override
	public void print(){
		placeItems();
		boolean once = true; // to print each a b c ... once. to not enter swich case statement twice.
		for(int n=0; n<Board.length ; n++){
			for(int m=0;m<Board[n].length ;m++){
				if(n%2==0 ){
					if( once == true){
					switch (n+1){
					    case 1:
						    System.out.print("j\t");
							once = false;
							break;
						case 3:
							System.out.print("i\t");
							once = false;
							break;
						case 5:
							System.out.print("h\t");
							once = false;
							break;
						case 7:
							System.out.print("g\t");
							once = false;
							break;
						case 9:
							System.out.print("f\t");
							once = false;
							break;
						case 11:
							System.out.print("e\t");
							once = false;
							break;
						case 13:
							System.out.print("d\t");
							once = false;
							break;
						case 15:
							System.out.print("c\t");
							once = false;
							break;
						case 17:
							System.out.print("b\t");
							once = false;
							break;
						case 19:
							System.out.print("a\t");
							once = false;
							break;
				}}
					if(m!=0)
					System.out.print("--");
					System.out.print(Board[n][m]);

				}
				else if (n%2 == 1 && n!=9){
					if(m==0) System.out.print("  ");

					if((n==3 && m==5)) System.out.print(" \\");
					else if((n==1 && m== 5)) System.out.print(" /");
					else if((n==17 && m==5)) System.out.print(" /");
					else if((n==15 && m==5)) System.out.print(" \\");
					else if(((n==1 && m== 4)||(n==3 && m==4)||(n==15 && m== 4)||(n==17 && m== 4)) == false) System.out.print("  ");
					else System.out.print(" ");
					System.out.print("|");
					if((n==1 && m== 3)) System.out.print("\\");
					else if((n==3 && m==3)) System.out.print("/");
					else if((n==15 && m== 3)) System.out.print("/");
					else if((n==17 && m== 3)) System.out.print("\\");}
				else if(n==9){
					if(m==0) System.out.print("\t|");
					else if(m==Board[n].length-1) System.out.print("  |");
					else System.out.print("   ");
				}
			} once = true;
			System.out.println();
		}
		System.out.println();
		System.out.println("\t1--2--3--4--5--6--7--8--9");
	}


}
