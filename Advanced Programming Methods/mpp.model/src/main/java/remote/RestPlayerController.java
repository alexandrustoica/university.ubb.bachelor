package remote;

import domain.Player;
import error.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/remote/players")
public class RestPlayerController {

    @Autowired
    private RestModel model;

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Player add(@RequestParam(value="name") String name,
                      @RequestParam(value="age") int age) {
        Integer id = model.add(new Player(name, age));
        return model.getElementById(id);
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Player update(@RequestParam(value="id") int id,
                         @RequestParam(value="name") String name,
                         @RequestParam(value="age") int age) {
        model.update(model.getElementById(id), new Player(name, age));
        return model.getElementById(id);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public Player delete(@PathVariable("id") int id) {
        Player wanted = model.getElementById(id);
        model.delete(wanted);
        return wanted;
    }

    @RequestMapping("/get")
    public Player get(@RequestParam(value="id") Integer id) {
        model.add(new Player("tes", 245));
        return model.getElementById(id);
    }

    @RequestMapping("/all")
    public List<Player> getAll() {
        return model.getAll();
    }

    @ExceptionHandler(Errors.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String ArtistError(Errors errors) {
        return errors.getMessage();
    }
    
}
