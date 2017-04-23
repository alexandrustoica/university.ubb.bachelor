using System;

namespace ModelComponent.Domain
{
    /// <summary>
    /// The user's representation in our current system.
    /// Designed to support a name and a password.
    /// </summary>
    [Serializable]
    public class User: Idable<int>
    {
        /// <summary>
        /// The user's name. [unique in our system]
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The user's password.
        /// </summary>
        public string Password { get; }

        /// <summary>
        /// Creates a user with an empty/invalid id = 0.
        /// </summary>
        /// <param name="name">The user's name [unique]</param>
        /// <param name="password">The user's password</param>
        public User(string name, string password): this(0, name, password) { }

        /// <summary>
        /// Creates a user with a valid id.
        /// </summary>
        /// <param name="id">The user's id</param>
        /// <param name="name">The user's name [unique]</param>
        /// <param name="password">The user's password</param>
        public User(int id, string name, string password) : base(id)
        {
            Name = name;
            Password = password;
        }
  
        public override bool Equals(object o)
        {
            if (o == null)
            {
                return false;
            }
            if (o == this)
            {
                return true;
            }
            var other = (User)o;
            return GetId().Equals(other.GetId()) &&
                   Name.Equals(other.Name) &&
                   Password.Equals(other.Password);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((Name?.GetHashCode() ?? 0) * 397) ^
                       (Password?.GetHashCode() ?? 0);
            }
        }

        protected bool Equals(User other)
        {
            return string.Equals(Name, other.Name) &&
                string.Equals(Password, other.Password);
        }
    }
}