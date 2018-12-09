using System.Collections.Generic;
using ModelComponent.Domain;
using ModelComponent.Entity;

namespace ModelComponent.Repository
{
    public interface IRepositoryRelation<T, TD, E, ED, TE>
        where ED : IIdableEntity, new()
        where TD : IIdableEntity, new()
        where T : Idable<int>, new()
        where E : Idable<int>, new()
    {
        void Add(T element, E item);
        void Delete(T element, E item);
        List<T> GetAllT(E item);
        List<E> GetAllE(T element);
    }
}