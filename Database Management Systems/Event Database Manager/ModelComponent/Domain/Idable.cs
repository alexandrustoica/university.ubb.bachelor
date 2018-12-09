using System;

namespace ModelComponent.Domain
{
    /// <summary>
    /// Abstract class for ID support.
    /// The class is designed to add ID 
    /// support to all our domain objects.
    /// </summary>
    /// <typeparam name="T">The id's data type</typeparam>
    [Serializable]
    public abstract class Idable<T>
    {
        private readonly T _id;

        protected Idable(T id)
        {
            _id = id;
        }

        /// <summary>
        /// Returns the object's id.
        /// </summary>
        /// <returns>The object's id</returns>
        public T GetId()
        {
            return _id;
        }
    }
}