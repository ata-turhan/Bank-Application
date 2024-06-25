package client;

import java.io.Serializable;
import java.util.Objects;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public CustomerDTO() {
        // Default constructor for serialization/deserialization
    }

    public CustomerDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("CustomerDTO{name='%s'}", name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerDTO)) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
