using System.Collections.Generic;
using ModelComponent.Convertor;
using ModelComponent.Database;
using ModelComponent.Domain;
using ModelComponent.Entity;

namespace ModelComponent.Repository
{
    public class RepositoryRelation<T, TD, E, ED, TE> :
        DatabaseConnectionManager,
        IRepositoryRelation<T, TD, E, ED, TE>
        where TD : IIdableEntity, new()
        where ED : IIdableEntity, new()
        where T : Idable<int>, new()
        where E : Idable<int>, new()
        where TE : new()
    {

    private readonly IConvertorEntity<T, TD> _convertorEntityT;
    private readonly IConvertorEntity<E, ED> _convertorEntityE;
    private readonly IConvertorRelation<T, TD, E, ED, TE> _convertorRelation;

    public RepositoryRelation(
        string database,
        IConvertorEntity<T, TD> convertorEntityT,
        IConvertorEntity<E, ED> convertorEntityE,
        IConvertorRelation<T, TD, E, ED, TE> convertorRelation) : base(database)
    {
        _convertorEntityT = convertorEntityT;
        _convertorEntityE = convertorEntityE;
        _convertorRelation = convertorRelation;
    }

    public void Add(T element, E item)
    {
        MakeConnection();
        Connection.Insert(_convertorRelation.ConvertToDatabaseRelation(element, item));
        CloseConnection();
    }

    public void Delete(T element, E item)
    {
        MakeConnection();

        var relation = Connection.Table<TE>();
        foreach (var relationship in relation)
        {
            if (_convertorRelation.IsIdEqual(element, relationship) &&
                _convertorRelation.IsIdEqual(item, relationship))
            {
                Connection.Delete(relationship);
                break;
            }
        }
        CloseConnection();
    }

    public List<T> GetAllT(E element)
    {
        MakeConnection();
        var result = new List<T>();
        var data = Connection.Table<TE>();
        var teTable = new List<TE>();
        foreach (var item in data)
        {
            if (_convertorRelation.IsIdEqual(element, item))
            {
                teTable.Add(item);
            }
        }
        var elements = Connection.Table<TD>();
        var edTable = new List<TD>();
        foreach (var item in elements)
        {
            if (IsInside(teTable, item))
            {
                edTable.Add(item);
            }
        }
        edTable.ForEach(item => result.Add(_convertorEntityT.ConvertFromDatabaseEntity(item)));
        CloseConnection();
        return result;
    }

    private bool IsInside(List<TE> elements, ED element)
    {
        foreach (var item in elements)
        {
            if (_convertorRelation.IsIdEqual(element, item))
            {
                return true;
            }
        }
        return false;
    }

    private bool IsInside(List<TE> elements, TD element)
    {
        foreach (var item in elements)
        {
            if (_convertorRelation.IsIdEqual(element, item))
            {
                return true;
            }
        }
        return false;
    }

    public List<E> GetAllE(T element)
    {
        MakeConnection();
        var result = new List<E>();
        var data = Connection.Table<TE>();
        var teTable = new List<TE>();
        foreach (var item in data)
        {
            if (_convertorRelation.IsIdEqual(element, item))
            {
                teTable.Add(item);
            }
        }
        var elements = Connection.Table<ED>();
        var edTable = new List<ED>();
        foreach (var item in elements)
        {
            if (IsInside(teTable, item))
            {
                edTable.Add(item);
            }
        }
        edTable.ForEach(item => result.Add(_convertorEntityE.ConvertFromDatabaseEntity(item)));
        CloseConnection();
        return result;
    }
    }
}