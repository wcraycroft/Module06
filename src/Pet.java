import java.io.Serializable;
import java.util.Objects;

public class Pet implements Serializable {

    private String mName;
    private int mAge;
    private double mWeight;

    // Parameterized constructor - stores name, age and weight
    public Pet(String name, int age, double weight) {
        setName(name);
        setAge(age);
        setWeight(weight);
    }

    // Default constructor - default values for name, age, weight
    public Pet() {
        this("no name", 0, 0.0);
    }

    // Getters and Setters

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        // Check if string is empty or only blanks
        if (name.replaceAll(" ", "").equals("")) {
            System.err.println("Error: Pet must have a valid name.");
            System.exit(0);
        }
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        // Check if age is valid
        if (age < 0) {
            System.err.println("Error: Pet age cannot be negative.");
            System.exit(0);
        }
        mAge = age;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double weight) {
        mWeight = weight;
    }

    // equals method
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pet pet = (Pet) o;
        return mAge == pet.mAge &&
                Double.compare(pet.mWeight, mWeight) == 0 &&
                Objects.equals(mName, pet.mName);
    }

    // toString method - e.g. "Pet{Name='Odie', Age=9, Weight=35.0}"
    public String toString() {
        return "Pet{" +
                "Name='" + mName + '\'' +
                ", Age=" + mAge +
                ", Weight=" + mWeight +
                '}';
    }
}