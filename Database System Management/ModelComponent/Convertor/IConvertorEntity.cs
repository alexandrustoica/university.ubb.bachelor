namespace ModelComponent.Convertor
{
    /// <summary>
    /// Interface designed to support convert operations for a database entity.
    /// </summary>
    /// <typeparam name="TD">Domain Object</typeparam>
    /// <typeparam name="TE">Entity Object</typeparam>
    public interface IConvertorEntity<TD, TE>
    {
        TE ConvertToDatabaseEntity(TD element);
        TD ConvertFromDatabaseEntity(TE element);
    }
}