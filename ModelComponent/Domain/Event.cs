using System;

namespace ModelComponent.Domain
{
    /// <summary>
    /// The representation of an event in our system.
    /// Designed to support distance [m] & style for our event.
    /// </summary>
    [Serializable]
    public class Event: Idable<int>
    {
        /// <summary>
        /// The event's distance [m].
        /// </summary>
        public int Distance { get; }

        /// <summary>
        /// The event's style aka type (the event's category).
        /// </summary>
        public EventStyle Style { get; }

        /// <summary>
        /// Creates an event with a valid id.
        /// </summary>
        /// <param name="id">The event's id</param>
        /// <param name="distance">The event's distance [m]</param>
        /// <param name="style">The event's style [category]</param>
        public Event(int id, int distance, EventStyle style): base(id)
        {
            Distance = distance;
            Style = style;
        }

        /// <summary>
        /// Creates an event with a invalid/empty id = 0.
        /// </summary>
        /// <param name="distance">The event's distance [m]</param>
        /// <param name="style">The event's style [category]</param>
        public Event(int distance, EventStyle style) : this(0, distance, style) { }

        public override string ToString()
        {
            return Distance + " " + Style;
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
            var other = (Event)obj;
            return GetId().Equals(other.GetId()) &&
                   Distance.Equals(other.Distance) &&
                   Style.Equals(other.Style);
        }

        protected bool Equals(Event other)
        {
            return Distance.Equals(other.Distance) &&
                Equals(Style, other.Style);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return (Distance * 397) ^ (Style?.GetHashCode() ?? 0);
            }
        }
    }
}