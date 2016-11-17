package supermarket.model;

import com.sun.jmx.remote.internal.Unmarshal;
import supermarket.util.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by ssthouse on 17/11/2016.
 */
public class PersonDataManager {


    public static List<Person> loadPersonsFromFile(File personFile) {
        PersonListWrapper personListWrapper = null;
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            //reading xml data from file
            personListWrapper = (PersonListWrapper) um.unmarshal(personFile);
        } catch (JAXBException e) {
            e.printStackTrace();
            Log.println("something is wrong");
        }
        return personListWrapper.getPersonList();
    }

    public static void savePersonsDataToFile(File file, List<Person> personList) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            PersonListWrapper personListWrapper = new PersonListWrapper();
            personListWrapper.setPersonList(personList);
            marshaller.marshal(personListWrapper, file);
        } catch (JAXBException e) {
            e.printStackTrace();
            Log.println("something is wrong");
        }
    }
}
