package restclient.service.implementation;


import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Service
public class PopulateCountries {

    @ModelAttribute(name = "countriesList")
    public List<String> populateCountries() {

        List<String> countries = new ArrayList<>();
        countries.add("India");
        countries.add("USA");
        countries.add("Japan");
        countries.add("Australia");
        countries.add("Canada");

        return countries;
    }
}
