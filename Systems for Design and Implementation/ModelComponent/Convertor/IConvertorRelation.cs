namespace ModelComponent.Convertor
{
    public interface IConvertorRelation<in T, in TE, in TD, in TDe, TR>
    {
        TR ConvertToDatabaseRelation(T element, TD item);
        bool IsIdEqual(TDe element, TR item);
        bool IsIdEqual(TE element, TR item);
        bool IsIdEqual(T element, TR item);
        bool IsIdEqual(TD element, TR item);
    }
}