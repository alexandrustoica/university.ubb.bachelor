using System.Collections.Generic;
using System.IO;
using ModelComponent.Database;
using ModelComponent.Entity;
using ModelComponent.Repository;

namespace ModelComponent.Model
{
    public class Model<T, E> : IModel<T>
        where E : IIdableEntity, new()
        {

        public string PreferredDatabaseSource { get; set; }

        protected IRepositoryEntity<T, E> Repository;

        public Model(string database)
        {
            var defaultDatabase = Path.Combine(Directory.GetCurrentDirectory(), "database.db");
            PreferredDatabaseSource = database ?? defaultDatabase;
            var validator = new DatabaseTableValidator(PreferredDatabaseSource);
            validator.CheckDatabase();
        }

        public Model() : this(null) {}
    
        public int Add(T element)
        {
            return Repository.Add(element);
        }

        public void Delete(T element)
        {
            Repository.Delete(element);
        }

        public void Update(T element, T with)
        {
            Repository.Update(element, with);
        }

        public List<T> GetAll()
        {
            return Repository.GetAll();
        }

        public T FindElementById(int id)
        {
            return Repository.FindElementById(id);
        }
    }
}