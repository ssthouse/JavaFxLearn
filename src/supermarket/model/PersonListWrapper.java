package supermarket.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by ssthouse on 17/11/2016.
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> personList;

    @XmlElement(name = "person")
    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
