import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		while(true){
			Scanner s = new Scanner(System.in);
			System.out.println("How many places do you want to visit?\n");
			int N = s.nextInt();	// How many place do you want to visit
			if(N>100){
				System.out.println("A maximum of 100 locations can be assessed. Try again!\n");
			}
			else{
				findingVisitPlaces(N);	// Places to visit will be calculated
				break;
			}
			
		}
	}

	public static void findingVisitPlaces (int N){
		ArrayList<Place> placeList = new ArrayList<Place>();
		Random newRand = new Random();
		Place newPlace = null;
		int aliceX = (newRand.nextInt(2000)-1000);					// Generate random X coordinate of Alice
		int aliceY = (newRand.nextInt(2000)-1000);					// Generate random Y coordinate of Alice
		
		for(int i=0; i<100; i++){
			newPlace = new Place();									// Creating new Place's object
			newPlace.setX((newRand.nextInt(2000)-1000));			// Generate random X coordinate
			newPlace.setY((newRand.nextInt(2000)-1000));			// Generate random Y coordinate
			newPlace.setFee(newRand.nextInt(60));					// Generate random fee
			newPlace.setDistance( ((int)Math.sqrt( (Math.pow((aliceX-newPlace.getX()), 2)) + (Math.pow((aliceY-newPlace.getY()), 2)) ) ) );
																	// Calculating booster distance
			newPlace.setnThCreatingPlace(i);
			if( placeList.size() < N ){		 						// We have added elements until the Array list Full		
				placeList.add(newPlace);		
			}
			else{		
				if(newPlace.getDistance() <= 200){
					int maxDistanceIndex = 0;
					for(int j=0; j<N; j++){
						if(placeList.get(j).getDistance() > 200){	//Find max distance element in ArrayList
							if(placeList.get(j).getDistance() > placeList.get(maxDistanceIndex).getDistance()){
								maxDistanceIndex  = j;
							}
						}	
					}
					if(placeList.get(maxDistanceIndex).getDistance() > 200){ //Find max distance element in ArrayList deleting
						placeList.remove(maxDistanceIndex);
						placeList.add(newPlace);					// Adding new Place
					}
					else{ 											//not find greater 200 distance in ArrayList
						
						int maxFee = newPlace.getFee();
						int maxFeeIndex = -1;
						
						for(int j=0; j<placeList.size(); j++){		// Finding element of max fee in ArrayList
							if(placeList.get(j).getFee() > maxFee){
								maxFee = placeList.get(j).getFee();
								maxFeeIndex = j;
							}		
						}
						if(maxFeeIndex != -1){						//If find max Fee index, delete this in ArrayList
							placeList.remove(maxFeeIndex);
							placeList.add(newPlace);
						}				
					}
				}
				else{ 												//if distance is greater 200
					int maxDistanceIndex = 0;
					for(int j=0; j<N; j++){			
						if(placeList.get(j).getDistance() > 200){	// Finding max Distance element location in ArrayList
							if(placeList.get(j).getDistance() > placeList.get(maxDistanceIndex).getDistance()){
								maxDistanceIndex  = j;
							}
						}			
					}	
					if(placeList.get(maxDistanceIndex).getDistance() > 200){	// if include smallest distance Element , deleting Max Distance elemen in ArrayList							
						if(placeList.get(maxDistanceIndex).getDistance() > newPlace.getDistance()){						
							placeList.remove(maxDistanceIndex);
							placeList.add(newPlace);						
						}				
					}			
				}		
			}		
		}		
		printNthPlace(placeList, N);								// Printing visit place that Nth
	}
	
	public static void printNthPlace(ArrayList<Place> placeList,int N){
		for(int i=0 ; i<N ; i++){
			for(int j=0 ; j<N ;j++){								// Sorted by Distance ArrayList
				if(placeList.get(i).getDistance() < placeList.get(j).getDistance()){
					Collections.swap(placeList, j,i);
				}				
			}	
		}
		for(int i = 0 ; i<N ; i++){									// if distance smaller 200 , so sorted by Fee of Place
			if(placeList.get(i).getDistance()<=200){
				for(int j =i+1 ; j<N ; j++){
					if(placeList.get(j).getDistance()<=200){
						if(placeList.get(i).getFee() > placeList.get(j).getFee()){
							Collections.swap(placeList, i, j);
						}
					}
				}
			}
		}	
		System.out.println("The booster distance are found!");		//Printing Places According to Nth
		for(int i=0 ; i<N ; i++){
			System.out.println((i+1) + "th nearst distance calculated with the " + placeList.get(i).getnThCreatingPlace()
					+ ". generate coordinate is " + placeList.get(i).getDistance() + "\n" + 
					"Coordinates of touristic place is (" + placeList.get(i).getX() + "," +
					placeList.get(i).getY() + "),location fee is " + placeList.get(i).getFee() + "\n");
		}
	}
}
