namespace ModelComponent.Entity
{
    /// <summary>
    /// Interface designed to add ID support to our entity objects.
    /// </summary>
    /// <typeparam name="T">The id's type</typeparam>
    public interface IIdableEntity<T>
    {
        T Id { get; set; }        
    }
}