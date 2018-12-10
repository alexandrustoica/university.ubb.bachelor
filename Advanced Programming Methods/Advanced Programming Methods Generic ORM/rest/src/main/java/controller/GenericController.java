package controller;

import com.sun.istack.internal.NotNull;
import domain.Idable;
import context.UpdateContext;
import model.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import translator.GenericTranslator;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/generic/")
public class GenericController<T extends Idable<Id>, Entity extends Idable<Id>, Id extends Serializable> {

    private final Model<Entity, Id> model;
    protected GenericTranslator<Entity, T> translator;
    private final Supplier<RemoteException> thrower;

    public GenericController(@NotNull Model<Entity, Id> model) {
        this.model = model;
        this.thrower = () -> new RemoteException("unable to find element");
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<T> insert(@RequestBody T element) {
        return new ResponseEntity<>(translator.translate(
                model.insert(translator.transform(element))), HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<T> update(@RequestBody UpdateContext<T> context) {
        return new ResponseEntity<>(translator.translate(
                model.update(translator.transform(context.getElement()),
                        translator.transform(context.getWith()))), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public T delete(@PathVariable("id") Id id) throws RemoteException {
        return translator.translate(model.delete(model.getElementById(id)
                .orElseThrow(thrower)).orElseThrow(thrower));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public T get(@PathVariable("id") Id id) throws RemoteException {
        return translator.translate(model.getElementById(id)
                .orElseThrow(thrower));
    }

    @RequestMapping("/all")
    public List<T> all() throws RemoteException {
        return model.all().stream().map(translator::translate).collect(Collectors.toList());
    }

}
