package biblioteka.service;

import biblioteka.model.Knjiga;
import biblioteka.model.Korisnik;
import biblioteka.model.Pozajmica;
import biblioteka.repository.KnjigaRepository;
import biblioteka.repository.KorisnikRepository;
import biblioteka.repository.PozajmicaRepository;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.List;

public class BibliotekaService {

    KorisnikRepository korisnikRepo = new KorisnikRepository();
    KnjigaRepository knjigaRepo = new KnjigaRepository();
    PozajmicaRepository pozajmicaRepo = new PozajmicaRepository();

    public int izdajKnjigu(int korisnikId, int knjigaId) throws SQLException {
        if (!korisnikRepo.postojiKorisnik(korisnikId)) {
            System.out.println("Korisnik sa id-em " + korisnikId + " ne postoji.");
            return -1;
        } else if (!knjigaRepo.postojiKnjiga(knjigaId)) {
            System.out.println("Knjiga sa id-em " + knjigaId + " ne postoji.");
            return -1;
        } else if (!pozajmicaRepo.jeKnjigaDostupna(knjigaId)) {
            System.out.println("Knjiga sa id-em " + knjigaId + " nije dostupna.");
            return -1;
        }
        return pozajmicaRepo.dodajPozajmicu(korisnikId, knjigaId);
    }

    public void vratiKnjigu(int korisnikId, int... knjigaIds) throws SQLException {
        List<Pozajmica> aktivnePozajmice = pozajmicaRepo.pronadjiAktivnePozajmicePoKorisnikIdu(korisnikId);
        for(int knjId : knjigaIds){
            for (Pozajmica p : aktivnePozajmice) {
                if(p.getKnjigaId() == knjId){
                    pozajmicaRepo.zatvoriPozajmicu(p.getId());
                    break;
                }
            }
        }
    }
}