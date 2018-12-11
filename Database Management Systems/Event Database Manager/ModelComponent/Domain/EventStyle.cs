using System;

namespace ModelComponent.Domain
{
    /// <summary>
    /// Defines the event's type.
    /// All events in our system are based on those categories.
    /// </summary>
    [Serializable]
    public enum EventType
    {
        Free,
        Back,
        Butterfly,
        Mix
    }

    /// <summary>
    /// Wrapper designed for EventType.
    /// Allows a the enum EventType to have methods.
    /// The event's type and style are the by definition the same.
    /// The style just provides a layer of methods to the type.
    /// </summary>
    [Serializable]
    public class EventStyle
    {
        /// <summary>
        /// The event's type.
        /// </summary>
        public EventType Type { get; }

        /// <summary>
        /// Creates a style based on a type.
        /// </summary>
        /// <param name="type">The event's type.</param>
        public EventStyle(EventType type)
        {
            Type = type;
        }

        public override bool Equals(object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (obj == this)
            {
                return true;
            }
            var other = (EventStyle)obj;
            return Type.Equals(other.Type);
        }

        protected bool Equals(EventStyle other)
        {
            return Type.Equals(other.Type);
        }

        public override int GetHashCode()
        {
            return (int)Type;
        }

        /// <summary>
        /// Used for database extraction. 
        /// </summary>
        /// <param name="data">The data from database</param>
        /// <returns></returns>
        public static EventStyle FromString(string data)
        {
            switch (data)
            {
                case "Free":
                    return new EventStyle(EventType.Free);
                case "Back":
                    return new EventStyle(EventType.Back);
                case "Butterfly":
                    return new EventStyle(EventType.Butterfly);
                case "Mix":
                    return new EventStyle(EventType.Mix);
                default:
                    return new EventStyle(EventType.Back);
            }
        }
        
        /// <summary>
        /// Used for database insertion.
        /// </summary>
        /// <returns>The data to add in database</returns>
        public override string ToString()
        {
            return Type.ToString();
        }
    }
}