package com.proyectoJuegoCalabozos.Proyecto.Model;
import com.proyectoJuegoCalabozos.Proyecto.Repository.ItemRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.DecorativesRepository;
import com.proyectoJuegoCalabozos.Proyecto.Repository.MonstersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DatabaseInit implements ApplicationRunner{

    @Autowired
    MonstersRepository monsterRepository;
    
    @Autowired 
    DecorativesRepository decorativesRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        monsterRepository.save(new MonstersEsp("Molanisk","2021-09-02",40,45,1,52,"molanisks","A strange mole-like being.","https://oldschool.runescape.wiki/w/wMolanisk"));
        monsterRepository.save(new MonstersEsp("Samy","2021-09-02",40,45,1,52,"Ingeniero","SaamYyy NoOooO","https://oldschool.runescape.wiki/w/wMolanisk"));
        decorativesRepository.save(new Decoratives("Cuadro"));
        itemRepository.save(new Items("Arma","2021-09-02", 15, 25,"ARMA","wiki.com"));
    }
    
}
