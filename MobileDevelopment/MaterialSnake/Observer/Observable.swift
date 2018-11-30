
import Foundation


class Observable {

    /// List of objects that are observing the changes of the observable object.
    private var observers: [Observer]

    init() {
        self.observers = [Observer]()
    }

    deinit {
        self.observers.removeAll()
    }

    func append(observer: Observer) {
        self.observers.append(observer)
    }

    func notifyObservers(with event: Event) {
        for observer in observers {
            observer.notify(with: event)
        }
    }

}
