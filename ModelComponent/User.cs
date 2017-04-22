using System;

namespace ModelComponent
{
    [Serializable]
    public class User
    {
        private readonly int _id;
        private readonly string _name;
        private readonly string _password;

        public User(string name, string password): this(0, name, password) { }

        public User(int id, string name, string password)
        {
            _id = id;
            _name = name;
            _password = password;
        }

        public int GetId()
        {
            return _id;
        }

        public string GetName()
        {
            return _name;
        }

        public string GetPassword()
        {
            return _password;
        }
    }
}