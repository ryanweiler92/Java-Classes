import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Optional;

@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        this.adventureRepository = adventureRepo;
    }

    // Add controller methods below:
    @GetMapping()
    public Iterable<Adventure>getAdventures(){
        Iterable<Adventure> adventures = adventureRepository.findAll();
        return adventures;
    }

    @GetMapping(path="/bycountry/{country}")
    public Iterable<Adventure>getAdventureByCountry(@PathVariable String country){
        Iterable<Adventure> adventures = adventureRepository.findByCountry(country);
        return adventures;
    }

    @GetMapping(path="/bystate")
    public Iterable<Adventure>getAdventureByState(@RequestParam(name="state") String state){
        return this.adventureRepository.findByState(state);
    }

    @PostMapping()
    public String createNewAdventure(@RequestBody Adventure adventure){
        adventureRepository.save(adventure);
        return "Adventure successfully added!";
    }

    @PutMapping(path="/{id}")
    public Adventure updateAdventure(@PathVariable("id") Integer id, @RequestBody Adventure adventure){
        Optional<Adventure> adventureOptional = this.adventureRepository.findById(id);
        if(!adventureOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Adventure adventureToUpdate = adventureOptional.get();
        if(!adventure.getBlogCompleted()) {
            adventureToUpdate.setBlogCompleted(true);
        } else {
            adventureToUpdate.setBlogCompleted(false);
        }

        Adventure updatedAdventure = this.adventureRepository.save    (adventureToUpdate);
        return updatedAdventure;
    }

    @DeleteMapping(path="/{id}")
    public void deleteAdventure(@PathVariable("id") Integer id){
        Optional<Adventure> adventureOptional = this.adventureRepository.findById(id);
        if(adventureOptional.isPresent()){
            Adventure adventureToDelete = adventureOptional.get();
            this.adventureRepository.delete(adventureToDelete);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}