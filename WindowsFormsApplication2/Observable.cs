using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApplication2 {
	public class Observable
	{
		private List<Observer> observers;

		public Observable()
		{
			this.observers = new List<Observer>();
		}

		public void addObserver(Observer observer)
		{
			observers.Add(observer);
		}

		public void notifyObservers(NotificationType type)
		{
			foreach (var observer in observers)
			{
				observer.notifyMe(type);
			}
		}
	}
}
