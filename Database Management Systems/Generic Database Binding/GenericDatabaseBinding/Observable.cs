using System.Collections.Generic;


namespace WindowsFormsApplication2 {

    public class Observable
	{
		private readonly List<Observer> _observers;

		public Observable()
		{
            _observers = new List<Observer>();
		}

		public void AddObserver(Observer observer)
		{
			_observers.Add(observer);
		}

		public void NotifyObservers(NotificationType type)
		{
			foreach (var observer in _observers)
			{
				observer.notifyMe(type);
			}
		}
	}
}
