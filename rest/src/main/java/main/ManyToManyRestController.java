package main;

import domain.Idable;
import domain.RelationEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import service.ServiceManyToMany;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/remote/")
public class ManyToManyRestController<TransferT extends Idable<Id>, TransferU extends Idable<Id>,
        EntityT extends Idable<Id>, EntityU extends Idable<Id>,
        Relation extends RelationEntity<EntityT, EntityU> & Idable<Id>, Id extends Serializable>{

    private ServiceManyToMany<TransferT, TransferU, EntityT, EntityU, Relation, Id> model;

    @SuppressWarnings("unchecked")
    public ManyToManyRestController(ServiceManyToMany model) {
        this.model = model;
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public TransferT delete(@PathVariable("id") Id id) throws RemoteException {
        TransferT element = model.getElementById(id);
        return model.delete(element);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public TransferT get(@PathVariable("id") Id id) throws RemoteException {
        return model.getElementById(id);
    }

    @RequestMapping("/all")
    public List<TransferT> all() throws RemoteException {
        return model.all();
    }

    @RequestMapping(value = "/all-from/{id}", method = RequestMethod.GET)
    public List<TransferT> allFrom(@PathVariable("id") Id id) throws RemoteException {
        return model.allFrom(model.receiveElementById(id));
    }

    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public TransferU remove(@PathVariable("id") Id id) throws RemoteException {
        TransferU element = model.receiveElementById(id);
        return model.remove(element);
    }

    @RequestMapping(value = "/receive/{id}", method = RequestMethod.GET)
    public TransferU receive(@PathVariable("id") Id id) throws RemoteException {
        return model.receiveElementById(id);
    }

    @RequestMapping("/every")
    public List<TransferU> every() throws RemoteException {
        return model.every();
    }

    @RequestMapping(value = "/every-from/{id}", method = RequestMethod.GET)
    public List<TransferU> everyFrom(@PathVariable("id") Id id) throws RemoteException {
        return model.everyFrom(model.getElementById(id));
    }
}
