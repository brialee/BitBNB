JCC = javac
JFLAGS = -g

default: Main.class Apartment.class City.class CityManager.class House.class Listing.class Reservation.class ReservationManager.class Review.class Room.class StackBlock.class 

Main.class: Main.java
	$(JCC) $(JFLAGS) Main.java

Apartment.class: Apartment.java
	$(JCC) $(JFLAGS) Apartment.java

City.class: City.java
	$(JCC) $(JFLAGS) City.java

CityManager.class: CityManager.java
	$(JCC) $(JFLAGS) CityManager.java

House.class: House.java
	$(JCC) $(JFLAGS) House.java

Listing.class: Listing.java
	$(JCC) $(JFLAGS) Listing.java

Reservation.class: Reservation.java
	$(JCC) $(JFLAGS) Reservation.java

ReservationManager.class: ReservationManager.java
	$(JCC) $(JFLAGS) ReservationManager.java

Review.class: Review.java
	$(JCC) $(JFLAGS) Review.java

Room.class: Room.java
	$(JCC) $(JFLAGS) Room.java

StackBlock.class: StackBlock.java
	$(JCC) $(JFLAGS) StackBlock.java

clean:
	$(RM) *.class
