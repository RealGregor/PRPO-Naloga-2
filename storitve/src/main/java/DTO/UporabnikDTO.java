package DTO;

import Entitete.Lastnistvo;
import Entitete.Najem;
import Entitete.Rezervacija;

import java.util.List;

public class UporabnikDTO {
    private Integer id;
    private String ime;
    private String priimek;
    private String email;
    private String uporabniskoIme;
    private List<Najem> najemi;
    private List<Lastnistvo> lastnistva;
    private List<Rezervacija> rezervacije;
}
