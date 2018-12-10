using System.Collections.Generic;
using ModelComponent.Entity;

namespace ModelComponent.Repository
{
    public interface IRepositoryEntity<T, TE> where TE : IIdableEntity, new()
    {
        int Add(T element);
        void Update(T element, T with);
        void Delete(T element);
        List<T> GetAll();
        T FindElementById(int id);
    }
}