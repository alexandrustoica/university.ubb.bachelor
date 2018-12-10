using System.Collections.Generic;

namespace ModelComponent.Model
{
    public interface IModel<T>
    {
        int Add(T element);
        void Delete(T element);
        void Update(T element, T with);
        List<T> GetAll();
        T FindElementById(int id);
    }
}