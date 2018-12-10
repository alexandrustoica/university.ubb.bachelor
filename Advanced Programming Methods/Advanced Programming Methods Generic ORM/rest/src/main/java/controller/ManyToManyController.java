package controller;

import com.sun.istack.internal.NotNull;
import context.RelationalInsertContext;
import context.UpdateContext;
import domain.Idable;
import domain.RelationEntity;
import javafx.util.Pair;
import model.ModelManyToMany;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import translator.GenericTranslator;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/many/")
public class ManyToManyController<T extends Idable<Id>, U extends Idable<Id>,
        EntityT extends Idable<Id>, EntityU extends Idable<Id>,
        Relation extends RelationEntity<EntityT, EntityU> & Idable<Id>, Id extends Serializable> {

    private final ModelManyToMany<EntityT, EntityU, Relation, Id> model;
    protected GenericTranslator<EntityT, T> translatorT;
    protected GenericTranslator<EntityU, U> translatorU;

    private final Supplier<RemoteException> thrower;

    public ManyToManyController(@NotNull ModelManyToMany<EntityT, EntityU, Relation, Id> model) {
        this.model = model;
        this.thrower = () -> new RemoteException("Unable to find element ...");
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<T> insert(@RequestBody T element) {
        return new ResponseEntity<>(translatorT.translate(
                model.insert(translatorT.transform(element))), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<T> update(@RequestBody UpdateContext<T> context) {
        return new ResponseEntity<>(translatorT.translate(
                model.update(translatorT.transform(context.getElement()),
                        translatorT.transform(context.getWith()))), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public T delete(@PathVariable("id") Id id) throws RemoteException {
        return translatorT.translate(model.delete(model.getElementById(id)
                .orElseThrow(thrower)).orElseThrow(thrower));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public T get(@PathVariable("id") Id id) throws RemoteException {
        return translatorT.translate(model.getElementById(id)
                .orElseThrow(thrower));
    }

    @RequestMapping("/all")
    public List<T> all() throws RemoteException {
        return model.all().stream().map(translatorT::translate).collect(Collectors.toList());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<U> add(@RequestBody U element) {
        return new ResponseEntity<>(translatorU.translate(
                model.add(translatorU.transform(element))), HttpStatus.OK);
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<U> refresh(@RequestBody UpdateContext<U> context) {
        return new ResponseEntity<>(translatorU.translate(
                model.refresh(translatorU.transform(context.getElement()),
                        translatorU.transform(context.getWith()))), HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public U remove(@PathVariable("id") Id id) throws RemoteException {
        return translatorU.translate(model.remove(model.receiveElementById(id)
                .orElseThrow(thrower)).orElseThrow(thrower));
    }

    @RequestMapping(value = "/receive/{id}", method = RequestMethod.GET)
    public U receive(@PathVariable("id") Id id) throws RemoteException {
        return translatorU.translate(model.receiveElementById(id)
                .orElseThrow(thrower));
    }

    @RequestMapping("/every")
    public List<U> every() throws RemoteException {
        return model.every().stream().map(translatorU::translate).collect(Collectors.toList());
    }

    @RequestMapping("/every-from")
    public List<U> everyFrom(@RequestBody T element) throws RemoteException {
        return model.everyFrom(translatorT.transform(element)).stream()
                .map(translatorU::translate).collect(Collectors.toList());
    }

    @RequestMapping("/all-from")
    public List<T> allFrom(@RequestBody U element) throws RemoteException {
        return model.allFrom(translatorU.transform(element)).stream()
                .map(translatorT::translate).collect(Collectors.toList());
    }

    @RequestMapping(value = "/insert-into", method = RequestMethod.POST)
    public ResponseEntity<Pair<T, U>> refresh(@RequestBody RelationalInsertContext<T, U> context) {
        Pair<Optional<EntityT>, Optional<EntityU>> result =
                model.insert(translatorT.transform(context.getElement()), translatorU.transform(context.getItem()));
        return new ResponseEntity<>(new Pair<>(
                translatorT.translate(result.getKey().get()),
                translatorU.translate(result.getValue().get())), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-from", method = RequestMethod.POST)
    public ResponseEntity<Pair<T, U>> delete(@RequestBody RelationalInsertContext<T, U> context) {
        Pair<Optional<EntityT>, Optional<EntityU>> result =
                model.delete(translatorT.transform(context.getElement()), translatorU.transform(context.getItem()));
        return new ResponseEntity<>(new Pair<>(
                translatorT.translate(result.getKey().get()),
                translatorU.translate(result.getValue().get())), HttpStatus.OK);
    }
}
