import java.util.*;

enum AnimalType {
	AQUATIC(0),LAND(1),AMPHIBIOUS(2);
	private int value;
	AnimalType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
enum EnclosureType {
	CAGE(0),POND(1),CAGE_AND_POND(2);
	private int value;
	EnclosureType(int value) {
		this.value = value;
	}
	public int getValue() {
		return this.value;
	}
}
abstract class Animal {
	protected AnimalType animalType;
	protected EnclosureType enclosureType;
	protected String name;
	Animal() {
		
	}
	public EnclosureType getEnclosureType() {
		return enclosureType;
	}
	public AnimalType getType() {
		return animalType;
	}
	public String toString() {
		return name;
	}
}
class LandAnimal extends Animal {
	LandAnimal(String name) {
		this.name  = name;
		animalType = AnimalType.LAND;
		enclosureType = EnclosureType.CAGE;
	}
}
class AquaticAnimal extends Animal {
	AquaticAnimal(String name) {
		this.name  = name;
		animalType = AnimalType.AQUATIC;
		enclosureType = EnclosureType.POND;
	}
}

class AmphibiousAnimal extends Animal {
	AmphibiousAnimal(String name) {
		this.name  = name;
		animalType = AnimalType.AMPHIBIOUS;
		enclosureType = EnclosureType.CAGE_AND_POND;
	}
}
class Enclosure {
	protected int capacity;
	List<Animal> animalList;
	protected AnimalType animalType;
	protected EnclosureType enclosureType;
	Enclosure(int capacity,EnclosureType enclosureType) {
		animalList         = new ArrayList<>();
		this.enclosureType = enclosureType;
		this.capacity      = capacity;
	}
	public boolean isFree() {
		return ( animalList == null ? true :animalList.size() < capacity);
	}
	public EnclosureType getEnclosureType() {
		return this.enclosureType;
	}
	protected void addAnimal(Animal animal) throws Exception{
		if (animal.getEnclosureType () != enclosureType) {
			throw new Exception("EnclosureType is different !");
		}
		if (animalList.size() == capacity) {
			throw new Exception("Capacity is full !");
		}
		animalList.add(animal);
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Animal animal : animalList) {
			sb = sb.append(animal.toString());
		}
		return sb.toString();
	}
	public boolean isEmpty() {
		return (animalList.size() == 0);
	}
}
class Zoo {
	private boolean feesPaid = false;
	List<List<Enclosure>> enclosureList;	
	Zoo() {
		enclosureList = new ArrayList<>();
		List<Enclosure> aqua, land, amphi;
		aqua = new ArrayList<>();
		land = new ArrayList<>();
		amphi= new ArrayList<>();
		enclosureList.add(aqua);
		enclosureList.add(land);
		enclosureList.add(amphi);
	}
	public void payEntranceFees() {
		feesPaid = true;
	}
	public boolean isFeesPaid() {
		return feesPaid;
	}
	public void leave() {
		feesPaid = false;
	}
	private Enclosure findFreeEnclosure(List<Enclosure> list, int type) {
		Enclosure e = null;
		for(Enclosure enc : list) {
			if(enc.isFree() && (enc.getEnclosureType().getValue() == type))
				return enc;
		}
		return null;
	}
	private int getPredefinedSizeForEnclosure(EnclosureType enclosureType) {
		int ret=0;
		switch(enclosureType) {
			case CAGE:
				ret = 10;
				break;
			case POND:
				ret = 50;
				break;
			case CAGE_AND_POND:
				ret = 20;
		}
		return ret;
	} 
	public void addAnimal(Animal animal) {
		int type = animal.getType().getValue();
		//find first free enclosure, if not found, create a new one (based on the animal type)
		List<Enclosure> list;
		if (enclosureList.size() <= type) {
			list = new ArrayList<>();
			enclosureList.add(type,list);
		} else {
			list = enclosureList.get(type);
		}
		Enclosure e = findFreeEnclosure(list,animal.getType().getValue());
		if(e == null) {
			//none free,create a new one, according to the animal's requirements
			int size = getPredefinedSizeForEnclosure(animal.getEnclosureType());
			e= new Enclosure(size,animal.getEnclosureType());
			list.add(e);
		} 
		try {
			e.addAnimal(animal); // add animal to enclosure
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}	

	}
	public boolean removeAnimal(Animal animal) {
		int type = animal.getType().getValue();
		//
		return false;
	}
	public String displayAnimals() {
		// if (!feesPaid) {
		// 	return null;
		// }
		StringBuilder sb = new StringBuilder();
		for(List<Enclosure> eList:enclosureList) {
			for(Enclosure e:eList) {
				if (e.isEmpty()) {
					continue;
				}
				sb = sb.append(e.getEnclosureType().getValue()).append("==>").append(e.toString());
				sb = sb.append("\n").append("***");				
			}
			sb = sb.append("\n").append("-----------").append("\n");
		}
		return sb.toString();
	}
}
class ZooPractice {
	public static void main(String[] args) {
		Zoo zoo = new Zoo();
		Animal[] landAnimals = new LandAnimal[3];
		String[] names = new String[]{"Lion","Tiger","Wolf"};
		int ctr = 0;
		for(String  name:names) {
			landAnimals[ctr] = new LandAnimal(name);
			zoo.addAnimal(landAnimals[ctr]);
			ctr++;

		}
		Animal[] aquaAnimals = new AquaticAnimal[3];
		names= new String[]{"Shark","Ray","Whale"};
		ctr = 0;
		for(String  name:names) {
			aquaAnimals[ctr] = new AquaticAnimal(name);
			zoo.addAnimal(aquaAnimals[ctr]);
			ctr++;

		}

		// Animal lion  = new LandAnimal("Lion");	
		// Animal tiger = new LandAnimal("Tiger");	
		// Animal fish  = new AquaticAnimal("Shark");
		// Animal frog  = new AmphibiousAnimal("Frog");
		// zoo.addAnimal(tiger);
		// zoo.addAnimal(fish);
		// zoo.addAnimal(frog);
		zoo.payEntranceFees();
		System.out.println(zoo.displayAnimals());
	}

}