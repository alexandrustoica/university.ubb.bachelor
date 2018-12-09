
import Foundation


/// Special events that describes the current context of the system.
enum EventType {

    /// The game's score just changed in the game model.
    case ScoreChange

    /// The game's level just changed in the game model.
    case LevelChange

    /// The game's status just changed in the game model.
    case StatusChange

    /// The game's speed just changed in the game model.
    case SpeedChange

    /// The current position of the food just changed.
    case FoodChange

    /// The current UI Color Theme just changed.
    case ThemeChange

}


/// Element that describes what just happened in the system.
struct Event {

    /// The description of the current context.
    private (set) var type: EventType

    /// - parameter type: The event's type [describes the context]
    init(havingType type: EventType) {
        self.type = type
    }

}
