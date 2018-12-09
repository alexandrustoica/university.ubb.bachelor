using System.Collections.Generic;
using ModelComponent.Convertor;
using ModelComponent.Database;
using ModelComponent.Entity;


namespace ModelComponent.Repository
{
    public class RepositoryEntity<T, TE> :
            DatabaseConnectionManager,
            IRepositoryEntity<T, TE> where TE : IIdableEntity, new()
        {

        private readonly IConvertorEntity<T, TE> _convertorEntity;

        public RepositoryEntity(string database, IConvertorEntity<T, TE> convertorEntity): 
            base(database)
        {
            _convertorEntity = convertorEntity;
        }

        public int Add(T element)
        {
            MakeConnection();
            var id = Connection.Insert(_convertorEntity.ConvertToDatabaseEntity(element));
            CloseConnection();
            return id;
        }

        public void Update(T element, T with)
        {
            MakeConnection();
            Connection.Update(_convertorEntity.ConvertToDatabaseEntity(with));
            CloseConnection();
        }

        public void Delete(T element)
        {
            MakeConnection();
            Connection.Delete(_convertorEntity.ConvertToDatabaseEntity(element));
            CloseConnection();
        }

        public List<T> GetAll()
        {
            MakeConnection();
            var data = Connection.Table<TE>();
            var result = new List<T>();
            foreach (var item in data)
            {
                result.Add(_convertorEntity.ConvertFromDatabaseEntity(item));
            }
            CloseConnection();
            return result;
        }

        public T FindElementById(int id)
        {
            MakeConnection();
            var results = Connection.Table<TE>();
            T result = default(T);
            foreach (var element in results)
            {
                if (element.Id.Equals(id))
                {
                    result = _convertorEntity.ConvertFromDatabaseEntity(element);
                }

            }
            CloseConnection();
            return result;
        }
        
    }
}