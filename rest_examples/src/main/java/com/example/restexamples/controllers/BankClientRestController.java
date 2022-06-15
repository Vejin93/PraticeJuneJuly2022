package com.example.restexamples.controllers;

import com.example.restexamples.entities.BankClientBean;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@RestController
@RequestMapping("/bankclients")
public class BankClientRestController {

    // 1. Zadatak - Ispisivanje svih klijenata
    @RequestMapping(method = RequestMethod.GET)
    public List<BankClientBean> getAll(){
        List<BankClientBean> clients = new ArrayList<BankClientBean>();
        clients.add(new BankClientBean(1, "Petar","Petrovic","petar.petrovic@gmail.com"));
        clients.add(new BankClientBean(2, "Milos","Milosevic","milos.milosevic@gmail.com"));
        return clients;
    }

    // 2. Zadatak - Kreiranje baze klijenata
    protected List<BankClientBean> getDB(){
        List<BankClientBean> clients = new ArrayList<BankClientBean>();
        // Klijenti pre nego sto smo dodali polja datum rodjenja i bonitet

        /*
        clients.add(new BankClientBean(1,"Petar","Petrovic","petar.petrovic@gmail.com"));
        clients.add(new BankClientBean(2,"Milos","Milosevic","milos.milosevic@gmail.com"));
        clients.add(new BankClientBean(3,"Predrag","Preradovic","predrag.preradovic@gmail.com"));
        */

        // Klijenti NAKON sto smo dodali polja datum rodjenja i bonitet
        clients.add(new BankClientBean(1,"Petar","Petrovic","petar.petrovic@gmail.com", LocalDate.of(1980, 9, 04),null, "Apatin"));
        clients.add(new BankClientBean(2,"Milos","Milosevic","milos.milosevic@gmail.com",LocalDate.of(1975, 9, 04),null, "Sombor"));
        clients.add(new BankClientBean(3,"Predrag","Preradovic","predrag.preradovic@gmail.com",LocalDate.of(1970, 9, 04),null,"Novi Sad"));
        clients.add(new BankClientBean(4,null,"Jovanovic","jovan.jovanovic@gmail.com",LocalDate.of(1982, 9, 04),null, "Apatin"));
        return clients;
    }

    // 3. Zadatak - Ispisivanje klijenta sa zadatim ID-jem
    @RequestMapping(method = RequestMethod.GET, value = "/{clientId}")
    public BankClientBean getById(@PathVariable String clientId){

        /* STARI KOD PRE IMPLEMENTACIJE DBa
        if(clientId.equals("1"))
            return new BankClientBean(1, "Petar", "Petrovic","petar.petrovic@gmail.com");
        else
            return new BankClientBean();
        */

        // Sa klijentima iz baze
        for(BankClientBean bcb:getDB())
            if(bcb.getId().equals(Integer.parseInt(clientId)))
                return bcb;
        return new BankClientBean();
    }

    // 4. Zadatak - Postavljanje novog klijenta
    @RequestMapping(method = RequestMethod.POST)
    public String add(@RequestBody BankClientBean client){
        System.out.println(client.getName().concat("").concat(client.getSurname()));
        return "New client added";
    }

    // 5. Zadatak - Azuriranje novog klijenta
    @RequestMapping(method = RequestMethod.PUT, value="/{clientId}")
    public BankClientBean modify(@PathVariable String clientId, @RequestBody BankClientBean client){

        BankClientBean bcb = new BankClientBean(1, "Milan","Milanovic","milan.milanovic@gmail.com");
        if(clientId.equals("1")){
            bcb.setName(client.getName());
            return bcb;
        }
        else
            return null;
    }

    // 6. Zadatak - Brisanje klijenta po ID-ju
    @RequestMapping(method = RequestMethod.DELETE, value="/{clientId}")
    public BankClientBean delete(@PathVariable String clientId){
        for(BankClientBean bcb:getDB())
            if(bcb.getId().equals(Integer.parseInt(clientId))){
                getDB().remove(bcb);
                return bcb;
            }
        return new BankClientBean();
    }

    // 7. Zadatak - Pretraga klijenta po imenu i prezimenu
    @RequestMapping(method = RequestMethod.GET, value="/client")
    public BankClientBean getByNameSurname(@RequestParam("name") String name, @RequestParam ("surname") String surname){
        if(name.equals("Petar") && surname.equals("Petrovic"))
            return new BankClientBean(1,"Petar","Petrovic","petar.petrovic@gmail.com");
        else
            return new BankClientBean();
    }

    // 8. Zadatak - Ispis svih email-ova iz baze podataka
    @RequestMapping(method = RequestMethod.GET, value="/emails")
    public List<String> getEmails(){
        List<String> emails = new ArrayList<>();
        for(BankClientBean bcb : getDB())
            emails.add(bcb.getEmail());
        return emails;
    }

    // 9. Zadatak - Vracanje liste imena klijenata cije ime pocinje na prosledjeno slovo kao parametar
    @RequestMapping(method = RequestMethod.GET, value="/clients/{firstLetter}")
    public List<String> nameClientsWithFirstLetter(@PathVariable String firstLetter){
        List<String> names = new ArrayList<>();
        for(BankClientBean bcb : getDB())
            if(bcb.getName().startsWith(firstLetter))
                names.add(bcb.getName());
        return names;
    }

    // 10. Zadatak - Vracanje imena i prezimena cije ime i prezime pocinje na slovo prosledjeno kao parametar
    @RequestMapping(method = RequestMethod.GET, value="/clients/firstLetters")
    public List<String> getFirstLettersNameSurname(@RequestParam String slovo){
        List<String> namesSurnames = new ArrayList<>();
        for(BankClientBean bcb : getDB())
            if(bcb.getName().startsWith(slovo) && bcb.getSurname().startsWith(slovo)) {
                namesSurnames.add(bcb.getName());
                namesSurnames.add(bcb.getSurname());
            }
        return namesSurnames;
    }

    // 11. Zadatak - Vracanje liste imena klijenata koja su sortirana u redosledu koji je prosledjen kao parametar
    @RequestMapping(method = RequestMethod.GET, value="/clients/sort/{order}")
    public List<String> orderNames(@PathVariable String order){
        List<String> names = new ArrayList<>();
        for(BankClientBean bcb : getDB())
            names.add(bcb.getName());
        if(order.equals("asc"))
            Collections.sort(names);
        else if(order.equals("desc"))
            Collections.sort(names, Collections.reverseOrder());
        return names;
    }

    // 12. Zadatak - Svakom klijentu postavlja polje bonitet na "P" (pozitivan) ako je mladji od 65 godina ili na "N" ako je stariji od 65 godina
    @RequestMapping(method = RequestMethod.PUT, value="/clients/bonitet")
    public List<BankClientBean> updateBonitet(){
        List<BankClientBean> bcb0=new ArrayList<BankClientBean>();
        for(BankClientBean bcb : getDB()) {
            bcb0.add(bcb);
            int years = Period.between(bcb.getDateOfBirth(), LocalDate.now()).getYears();
            if (years >= 65)
                bcb.setBonitet("N");
            else
                bcb.setBonitet("P");
        }
        return bcb0;
    }

    // 13. Zadatak - Brise klijenta iz liste klijenata ukoliko klijent nema jednu vrednost: ime, prezime, email
    @RequestMapping(method = RequestMethod.GET, value="/clients/delete")
    public BankClientBean deleteNullNameSurnameEmail(){
        BankClientBean bcb0 = new BankClientBean();
        for(BankClientBean bcb : getDB()){
            bcb0=bcb;
            if(bcb.getName()==null || bcb.getSurname()==null || bcb.getEmail()==null)
                getDB().remove(bcb);
        }
        return bcb0;
    }

    //14. Zadatak - Koji vraca ukupan broj klijenata u listi klijenata koji imaju manje od broja godina koje je prosledjeno kao parametar
    @RequestMapping(method = RequestMethod.GET, value="/clients/countLess/{years}")
    public int countLess(@PathVariable int years){
        int brojac=0;
        for(BankClientBean bcb : getDB()){
            if((LocalDate.now().getYear()-bcb.getDateOfBirth().getYear())<years)
                brojac++;
        }
        return brojac;
    }

    //15. Zadatak - Koji vraca prosecan broj godina klijenata iz liste klijenata
    @RequestMapping(method = RequestMethod.GET, value="/clients/averageYears")
    public double averageYears(){
        double pomocna = 0;
        int brojac=0;
        for(BankClientBean bcb : getDB()){
            brojac++;
            pomocna += LocalDate.now().getYear()-bcb.getDateOfBirth().getYear();
        }
        return pomocna/brojac;
    }

    //16. Zadatak - Koji omogucava izmenu mesta stanovanja klijenta
    @RequestMapping(method = RequestMethod.PUT, value="/clients/changelocation/{clientId}")
    public BankClientBean changeLocation(@PathVariable Integer clientId, @RequestParam String city){
        for(BankClientBean bcb : getDB())
            if(bcb.getId()==clientId){
                bcb.setCity(city);
                return bcb;
            }
        return new BankClientBean();
    }

    //17. Zadatak - Vraca klijente banke koji ziva u gradu koji je prosledjen kao parametar
    @RequestMapping(method = RequestMethod.GET, value="/clients/from/{city}")
    public BankClientBean fromCity(@PathVariable String city){
        for(BankClientBean bcb : getDB())
            if(bcb.getCity()==city)
                return bcb;
        return new BankClientBean();
    }


    //18. Zadatak - Koji vraca klijente banke koji zive u gradu koji je prosledjen kao parametar i ciji je broj godina ispod broja prosledjenog kao drugi parametar
    @RequestMapping(method = RequestMethod.GET, value="/clients/findByCityAndAge")
    public List<BankClientBean> findByCityAndAge(@RequestParam String city, @RequestParam int years){
        List<BankClientBean> clients = new ArrayList<>();
        for(BankClientBean bcb : getDB())
            if (bcb.getCity().equals(city) && (LocalDate.now().getYear()-bcb.getDateOfBirth().getYear())<years)
                clients.add(bcb);
        return clients;
    }
}
